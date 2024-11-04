package com.league.nhl.league.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.league.nhl.league.dto.OwnerPositionDto;
import com.league.nhl.league.dto.TeamTableDto;
import com.league.nhl.league.entity.Match;
import com.league.nhl.league.entity.Season;
import com.league.nhl.league.entity.SeasonData;
import com.league.nhl.league.entity.Team;
import com.league.nhl.league.enums.Conference;
import com.league.nhl.league.enums.Division;
import com.league.nhl.league.enums.Owner;
import com.league.nhl.league.mapper.SeasonDataMapper;
import com.league.nhl.league.repository.MatchRepository;
import com.league.nhl.league.repository.SeasonDataRepository;
import com.league.nhl.league.repository.SeasonRepository;
import com.league.nhl.league.repository.TeamRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SeasonDataService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private SeasonRepository seasonRepository;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private SeasonDataRepository seasonDataRepository;

	public void initializeSeasonDataForTeams(Long seasonId, List<Long> teamIds) {
		Season season = seasonRepository.findById(seasonId)
				.orElseThrow(() -> new RuntimeException("Season not found with ID: " + seasonId));

		List<Team> teams = teamRepository.findAllById(teamIds);

		for (Team team : teams) {
			SeasonData seasonData = new SeasonData();
			seasonData.setSeasonId(season.getId());
			seasonData.setTeamId(team.getId());
			seasonData.setPoints(0);
			seasonData.setWins(0);
			seasonData.setLosses(0);
			seasonData.setWinsOt(0);
			seasonData.setLossesOt(0);
			seasonData.setGoalsScored(0);
			seasonData.setOwner(team.getOwner());
			seasonData.setGoalsAgainst(0);
			seasonData.setPointsBeforeRound(0);

			seasonDataRepository.save(seasonData);
		}
	}

	public List<TeamTableDto> getTeamSeasonDataForSeason(Long seasonId) {
		List<SeasonData> seasonDataList = seasonDataRepository.findBySeasonId(seasonId);

		List<Long> teamIds = seasonDataList.stream().map(seasonData -> seasonData.getTeamId())
				.collect(Collectors.toList());

		Map<Long, Team> teamMap = teamRepository.findAllById(teamIds).stream()
				.collect(Collectors.toMap(Team::getId, team -> team));

		List<TeamTableDto> sortedTeamTableDtos = seasonDataList.stream().map(seasonData -> {
			TeamTableDto dto = SeasonDataMapper.INSTANCE.toTeamTableDto(seasonData);
			Team team = teamMap.get(seasonData.getTeamId());
			dto.setTeamName(team.getName());
			dto.setShortName(team.getShortName());
			dto.setPlayedGames(calculatePlayedGames(seasonData));
			dto.setDivision(team.getDivision());
			dto.setConference(team.getConference());
			return dto;
		}).sorted(Comparator.comparingLong(this::calculateRankingScore).reversed() // Primary sort by points descending

		).collect(Collectors.toList());

		int position = 1;
		for (TeamTableDto dto : sortedTeamTableDtos) {
			dto.setPosition(position++);
		}

		Map<Conference, List<TeamTableDto>> remainingTeamsByConference = new HashMap<>();

		Map<Division, List<TeamTableDto>> teamsByDivision = sortedTeamTableDtos.stream()
				.collect(Collectors.groupingBy(TeamTableDto::getDivision));
		List<TeamTableDto> remainingTeams = new ArrayList<>();

		for (Map.Entry<Division, List<TeamTableDto>> entry : teamsByDivision.entrySet()) {
			List<TeamTableDto> divisionTeams = entry.getValue();

			for (int i = 0; i < divisionTeams.size(); i++) {
				TeamTableDto dto = divisionTeams.get(i);
				dto.setPlayOff(i < 3);
				if (i > 3 && i < 6) {
					remainingTeamsByConference.computeIfAbsent(dto.getConference(), k -> new ArrayList<>()).add(dto);
				}
			}
		}

		for (Map.Entry<Conference, List<TeamTableDto>> entry : remainingTeamsByConference.entrySet()) {
			List<TeamTableDto> wildcardCandidates = entry.getValue();

			wildcardCandidates.sort(Comparator.comparingLong(this::calculateRankingScore).reversed());

			int wildcardSpots = 2;
			for (int i = 0; i < Math.min(wildcardSpots, wildcardCandidates.size()); i++) {
				TeamTableDto wildcardTeam = wildcardCandidates.get(i);
				wildcardTeam.setWildCard(true);
			}
		}

		return sortedTeamTableDtos;
	}

	public List<TeamTableDto> getTeamConferenceDataForSeason(Long seasonId, Conference conference) {

		List<Team> teamList = teamRepository.findAllByConference(conference);

		Map<Long, SeasonData> seasonDataMap = seasonDataRepository.findBySeasonId(seasonId).stream()
				.collect(Collectors.toMap(SeasonData::getTeamId, data -> data));

		List<TeamTableDto> sortedTeamTableDtos = teamList.stream().map(team -> {
			SeasonData seasonData = seasonDataMap.get(team.getId());
			TeamTableDto dto = SeasonDataMapper.INSTANCE.toTeamTableDto(seasonData);
			dto.setTeamName(team.getName());
			dto.setShortName(team.getShortName());
			dto.setPlayedGames(calculatePlayedGames(seasonData));
			return dto;
		}).sorted(Comparator.comparingLong(this::calculateRankingScore).reversed() // Primary sort by points descending
		).collect(Collectors.toList());

		int position = 1;
		for (TeamTableDto dto : sortedTeamTableDtos) {
			dto.setPosition(position++);
		}

		return sortedTeamTableDtos;
	}

	private int calculatePlayedGames(SeasonData seasonData) {
		return seasonData.getWins() + seasonData.getLosses() + seasonData.getWinsOt() + seasonData.getLossesOt();
	}

	private long calculateRankingScore(TeamTableDto dto) {
		long score = dto.getPoints() * 100000; // Arbitrary multiplier to give more weight to points
		score = score - dto.getPlayedGames() * 1000;
		// Add total wins (including overtime wins)
		score += (dto.getWins() + dto.getWinsOt()) * 100;

		// Add goal differential
		score += (dto.getGoalsScored() - dto.getGoalsAgainst()) * 10;

		return score;
	}

	public List<TeamTableDto> getTeamDivisionDataForSeason(Long seasonId, Division division) {

		List<Team> teamList = teamRepository.findAllByDivision(division);

		Map<Long, SeasonData> seasonDataMap = seasonDataRepository.findBySeasonId(seasonId).stream()
				.collect(Collectors.toMap(SeasonData::getTeamId, data -> data));

		List<TeamTableDto> sortedTeamTableDtos = teamList.stream().map(team -> {
			SeasonData seasonData = seasonDataMap.get(team.getId());
			TeamTableDto dto = SeasonDataMapper.INSTANCE.toTeamTableDto(seasonData);
			dto.setTeamName(team.getName());
			dto.setShortName(team.getShortName());
			dto.setPlayedGames(calculatePlayedGames(seasonData));
			return dto;
		}).sorted(Comparator.comparingLong(this::calculateRankingScore).reversed() // Primary sort by points descending
		).collect(Collectors.toList());

		int position = 1;
		for (TeamTableDto dto : sortedTeamTableDtos) {
			dto.setPosition(position++);
		}

		return sortedTeamTableDtos;
	}

	public List<OwnerPositionDto> getOwnerPositions(Long seasonId) {
		List<Match> matches = matchRepository.findBySeasonIdAndSimulatedFalse(seasonId);
		Map<Long, Team> teamList = teamRepository.findAll().stream()
				.collect(Collectors.toMap(Team::getId, data -> data));

		Map<Owner, OwnerPositionDto> ownerStatsMap = new HashMap<>();

		for (Match match : matches) {
			long homeTeamId = match.getHomeTeamId();
			long awayTeamId = match.getAwayTeamId();

			Owner homeOwner = teamList.get(homeTeamId).getOwner();
			Owner awayOwner = teamList.get(awayTeamId).getOwner();

			// Extract match details
			boolean isOvertime = match.isOvertime();
			int homeGoals = match.getHomeTeamScore();
			int awayGoals = match.getAwayTeamScore();

			if (homeGoals > awayGoals) {
				// Home team wins, away team loses
				updateOwnerStats(ownerStatsMap, homeOwner, true, isOvertime, homeGoals, awayGoals);
				updateOwnerStats(ownerStatsMap, awayOwner, false, isOvertime, awayGoals, homeGoals);
			} else if (awayGoals > homeGoals) {
				// Away team wins, home team loses
				updateOwnerStats(ownerStatsMap, awayOwner, true, isOvertime, awayGoals, homeGoals);
				updateOwnerStats(ownerStatsMap, homeOwner, false, isOvertime, homeGoals, awayGoals);
			}
		}

		List<OwnerPositionDto> ownerPositions = new ArrayList<>(ownerStatsMap.values());

		for (OwnerPositionDto dto : ownerPositions) {
			dto.setPoints(dto.getWins() * 2 + dto.getWinsOt() * 2 + dto.getLossesOt()); // Adjust according to your
																						// scoring system
		}

		// Sort owners by points in descending order
		ownerPositions.sort(Comparator.comparingInt(OwnerPositionDto::getPoints).reversed());

		// Assign positions based on sorted order
		int position = 1;
		for (OwnerPositionDto dto : ownerPositions) {
			dto.setPosition(position++);
		}

		return ownerPositions;
	}

	private void updateOwnerStats(Map<Owner, OwnerPositionDto> ownerStatsMap, Owner owner, boolean isWin,
			boolean isOvertime, int goalsScored, int goalsAgainst) {
		// Get or create OwnerPositionDto for the owner
		OwnerPositionDto dto = ownerStatsMap.computeIfAbsent(owner, o -> new OwnerPositionDto(o));

		// Update wins and losses based on the result and whether it was in overtime
		if (isWin) {
			if (isOvertime) {
				dto.setWinsOt(dto.getWinsOt() + 1);
			} else {
				dto.setWins(dto.getWins() + 1);
			}
		} else {
			if (isOvertime) {
				dto.setLossesOt(dto.getLossesOt() + 1);
			} else {
				dto.setLosses(dto.getLosses() + 1);
			}
		}

		// Update goals scored and goals against
		dto.setGoalsScored(dto.getGoalsScored() + goalsScored);
		dto.setGoalsAgainst(dto.getGoalsAgainst() + goalsAgainst);
	}
}