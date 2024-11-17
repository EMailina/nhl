package com.league.nhl.league.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.league.nhl.league.dto.MatchDto;
import com.league.nhl.league.dto.MatchInfoDto;
import com.league.nhl.league.dto.MatchViewDto;
import com.league.nhl.league.entity.Match;
import com.league.nhl.league.entity.SeasonData;
import com.league.nhl.league.entity.Team;
import com.league.nhl.league.mapper.MatchMapper;
import com.league.nhl.league.repository.MatchRepository;
import com.league.nhl.league.repository.SeasonDataRepository;
import com.league.nhl.league.repository.TeamRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class MatchService {

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private SeasonDataRepository teamDataRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Transactional
	public MatchViewDto createMatch(MatchDto matchDto) {
		Match match = MatchMapper.INSTANCE.toEntity(matchDto);

		SeasonData homeTeamData = teamDataRepository.findByTeamIdAndSeasonId(match.getHomeTeamId(),
				matchDto.getSeasonId());
		SeasonData awayTeamData = teamDataRepository.findByTeamIdAndSeasonId(match.getAwayTeamId(),
				matchDto.getSeasonId());

		updateTeamStatistics(homeTeamData, match.getHomeTeamScore(), match.getAwayTeamScore(), match.isOvertime());
		updateTeamStatistics(awayTeamData, match.getAwayTeamScore(), match.getHomeTeamScore(), match.isOvertime());

		teamDataRepository.save(homeTeamData);
		teamDataRepository.save(awayTeamData);
		match = matchRepository.save(match);

		Optional<Team> homeTeam = teamRepository.findById(match.getHomeTeamId());
		Optional<Team> awayTeam = teamRepository.findById(match.getAwayTeamId());

		MatchViewDto dto = new MatchViewDto();
		dto.setSeasonId(match.getSeasonId());
		dto.setId(match.getId());
		dto.setHomeTeamId(match.getHomeTeamId());
		dto.setAwayTeamId(match.getAwayTeamId());
		dto.setHomeTeamScore(match.getHomeTeamScore());
		dto.setAwayTeamScore(match.getAwayTeamScore());
		dto.setOvertime(match.isOvertime());
		dto.setSimulated(match.isSimulated());
		dto.setCreatedAt(match.getCreatedAt());
		dto.setHomeTeamName(homeTeam.get().getName());
		dto.setAwayTeamName(awayTeam.get().getName());
		dto.setHomeTeamShortName(homeTeam.get().getShortName());
		dto.setAwayTeamShortName(awayTeam.get().getShortName());
		return dto;
	}

	private void updateTeamStatistics(SeasonData seasonData, int teamScore, int opponentScore, boolean isOvertime) {
		seasonData.setGoalsScored(seasonData.getGoalsScored() + teamScore);
		seasonData.setGoalsAgainst(seasonData.getGoalsAgainst() + opponentScore);
		seasonData.setPointsBeforeRound(seasonData.getPoints());
		seasonData.setPoints(seasonData.getPoints() + calculatePoints(teamScore, opponentScore, isOvertime));
		if (isOvertime) {
			seasonData.setWinsOt(seasonData.getWinsOt() + (teamScore > opponentScore ? 1 : 0));
			seasonData.setLossesOt(seasonData.getLossesOt() + (teamScore < opponentScore ? 1 : 0));
		} else {
			seasonData.setWins(seasonData.getWins() + (teamScore > opponentScore ? 1 : 0));
			seasonData.setLosses(seasonData.getLosses() + (teamScore < opponentScore ? 1 : 0));
		}

	}

	@Transactional
	public void deleteMatch(Long matchId) {
		Match match = matchRepository.findById(matchId)
				.orElseThrow(() -> new EntityNotFoundException("Match not found with ID: " + matchId));

		SeasonData homeTeamData = teamDataRepository.findByTeamIdAndSeasonId(match.getHomeTeamId(),
				match.getSeasonId());
		SeasonData awayTeamData = teamDataRepository.findByTeamIdAndSeasonId(match.getAwayTeamId(),
				match.getSeasonId());

		revertTeamStatistics(homeTeamData, match.getHomeTeamScore(), match.getAwayTeamScore(), match.isOvertime());
		revertTeamStatistics(awayTeamData, match.getAwayTeamScore(), match.getHomeTeamScore(), match.isOvertime());

		teamDataRepository.save(homeTeamData);
		teamDataRepository.save(awayTeamData);

		matchRepository.delete(match);
	}

	private void revertTeamStatistics(SeasonData seasonData, int teamScore, int opponentScore, boolean isOvertime) {
		seasonData.setGoalsScored(seasonData.getGoalsScored() - teamScore);
		seasonData.setGoalsAgainst(seasonData.getGoalsAgainst() - opponentScore);

		seasonData.setPoints(seasonData.getPoints() - calculatePoints(teamScore, opponentScore, isOvertime));

		if (isOvertime) {
			seasonData.setWinsOt(seasonData.getWinsOt() - (teamScore > opponentScore ? 1 : 0));
			seasonData.setLossesOt(seasonData.getLossesOt() - (teamScore < opponentScore ? 1 : 0));
		} else {
			seasonData.setWins(seasonData.getWins() - (teamScore > opponentScore ? 1 : 0));
			seasonData.setLosses(seasonData.getLosses() - (teamScore < opponentScore ? 1 : 0));
		}
	}

	private int calculatePoints(int teamScore, int opponentScore, boolean overtime) {
		if (teamScore > opponentScore) {
			return 2; // Win
		} else if (overtime) {
			return 1; // lose overtime
		} else {
			return 0; // Loss
		}
	}

	public List<MatchViewDto> getAllMatchesForSeason(Long seasonId) {
		List<Match> matches = matchRepository.findBySeasonId(seasonId, Sort.by(Sort.Direction.DESC, "createdAt"));

		Map<Long, Team> teams = teamRepository.findAll().stream().collect(Collectors.toMap(Team::getId, team -> team));

		return matches.stream().map(match -> {
			MatchViewDto dto = new MatchViewDto();
			dto.setSeasonId(match.getSeasonId());
			dto.setId(match.getId());
			dto.setHomeTeamId(match.getHomeTeamId());
			dto.setAwayTeamId(match.getAwayTeamId());
			dto.setHomeTeamScore(match.getHomeTeamScore());
			dto.setAwayTeamScore(match.getAwayTeamScore());
			dto.setOvertime(match.isOvertime());
			dto.setSimulated(match.isSimulated());
			dto.setCreatedAt(match.getCreatedAt());
			dto.setHomeTeamName(teams.get(dto.getHomeTeamId()).getName());
			dto.setAwayTeamName(teams.get(dto.getAwayTeamId()).getName());
			dto.setHomeTeamShortName(teams.get(dto.getHomeTeamId()).getShortName());
			dto.setAwayTeamShortName(teams.get(dto.getAwayTeamId()).getShortName());
			return dto;
		}).collect(Collectors.toList());

	}

	public List<MatchViewDto> getAllMatchesForSeasonAndTeam(Long seasonId, Long teamId) {
		List<Match> matches = matchRepository.findBySeasonIdAndTeamId(seasonId, teamId);

		Map<Long, Team> teams = teamRepository.findAll().stream().collect(Collectors.toMap(Team::getId, team -> team));

		return matches.stream().map(match -> {
			MatchViewDto dto = new MatchViewDto();
			dto.setSeasonId(match.getSeasonId());
			dto.setId(match.getId());
			dto.setHomeTeamId(match.getHomeTeamId());
			dto.setAwayTeamId(match.getAwayTeamId());
			dto.setHomeTeamScore(match.getHomeTeamScore());
			dto.setAwayTeamScore(match.getAwayTeamScore());
			dto.setOvertime(match.isOvertime());
			dto.setSimulated(match.isSimulated());
			dto.setCreatedAt(match.getCreatedAt());
			dto.setHomeTeamName(teams.get(dto.getHomeTeamId()).getName());
			dto.setAwayTeamName(teams.get(dto.getAwayTeamId()).getName());
			dto.setHomeTeamShortName(teams.get(dto.getHomeTeamId()).getShortName());
			dto.setAwayTeamShortName(teams.get(dto.getAwayTeamId()).getShortName());
			return dto;
		}).collect(Collectors.toList());
	}

	private MatchViewDto mapToMatchViewDto(Match match, Optional<Team> homeTeam, Optional<Team> awayTeam) {
		MatchViewDto dto = new MatchViewDto();
		dto.setSeasonId(match.getSeasonId());
		dto.setId(match.getId());
		dto.setHomeTeamId(match.getHomeTeamId());
		dto.setAwayTeamId(match.getAwayTeamId());
		dto.setHomeTeamScore(match.getHomeTeamScore());
		dto.setAwayTeamScore(match.getAwayTeamScore());
		dto.setOvertime(match.isOvertime());
		dto.setSimulated(match.isSimulated());
		dto.setCreatedAt(match.getCreatedAt());

		// Set names and short names based on Optional
		if (homeTeam.isPresent()) {
			dto.setHomeTeamName(homeTeam.get().getName());
			dto.setHomeTeamShortName(homeTeam.get().getShortName());
		}

		if (awayTeam.isPresent()) {
			dto.setAwayTeamName(awayTeam.get().getName());
			dto.setAwayTeamShortName(awayTeam.get().getShortName());
		}

		return dto;
	}

	public MatchInfoDto getMatchTeamInfo(Long seasonId, Long teamId) {
		List<Match> matches = matchRepository.findBySeasonIdAndTeamId(seasonId, teamId);

		MatchInfoDto dto = new MatchInfoDto();
		dto.setId(teamId);

		matches.forEach(match -> {
			boolean isHomeTeam = match.getHomeTeamId().equals(teamId);
			boolean isSimulated = match.isSimulated();
			boolean isOvertime = match.isOvertime();

			dto.setPlayedGames(dto.getPlayedGames() + 1);

			if (isSimulated) {
				dto.setPlayedSimGames(dto.getPlayedSimGames() + 1);
			}

			int teamScore = isHomeTeam ? match.getHomeTeamScore() : match.getAwayTeamScore();
			int opponentScore = isHomeTeam ? match.getAwayTeamScore() : match.getHomeTeamScore();

			// Wins and losses
			if (teamScore > opponentScore) {
				if (isSimulated) {
					if (isOvertime) {
						dto.setSimWinsOt(dto.getSimWinsOt() + 1);
					} else {
						dto.setSimWins(dto.getSimWins() + 1);
					}
				} else {
					if (isOvertime) {
						dto.setWinsOt(dto.getWinsOt() + 1);
					} else {
						dto.setWins(dto.getWins() + 1);
					}
				}
			} else {
				if (isSimulated) {
					if (isOvertime) {
						dto.setSimLossesOt(dto.getSimLossesOt() + 1);
					} else {
						dto.setSimLosses(dto.getSimLosses() + 1);
					}
				} else {
					if (isOvertime) {
						dto.setLossesOt(dto.getLossesOt() + 1);
					} else {
						dto.setLosses(dto.getLosses() + 1);
					}
				}
			}
		});

		return dto;

	}

}