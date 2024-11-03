package com.league.nhl.league.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.league.nhl.league.dto.TeamTableDto;
import com.league.nhl.league.entity.Season;
import com.league.nhl.league.entity.SeasonData;
import com.league.nhl.league.entity.Team;
import com.league.nhl.league.enums.Conference;
import com.league.nhl.league.enums.Division;
import com.league.nhl.league.mapper.SeasonDataMapper;
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
			return dto;
		}).sorted(Comparator.comparingLong(this::calculateRankingScore).reversed() // Primary sort by points descending
		// .thenComparingInt(TeamTableDto::getPlayedGames) // Secondary sort by played
		// games ascending
		// .thenComparingInt(TeamTableDto::getWins).reversed() // Tertiary sort by wins
		// descending
		// .thenComparingInt(dto -> dto.getGoalsScored() -
		// dto.getGoalsAgainst()).reversed() // Fourth by goal
		// .thenComparingInt(TeamTableDto::getGoalsScored).reversed() // Goals For
		).collect(Collectors.toList());

		int position = 1;
		for (TeamTableDto dto : sortedTeamTableDtos) {
			dto.setPosition(position++);
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
}