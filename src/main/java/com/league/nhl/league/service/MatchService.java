package com.league.nhl.league.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.league.nhl.league.dto.MatchDto;
import com.league.nhl.league.dto.MatchViewDto;
import com.league.nhl.league.entity.Match;
import com.league.nhl.league.entity.SeasonData;
import com.league.nhl.league.entity.Team;
import com.league.nhl.league.mapper.MatchMapper;
import com.league.nhl.league.repository.MatchRepository;
import com.league.nhl.league.repository.SeasonDataRepository;
import com.league.nhl.league.repository.TeamRepository;

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
	public Match createMatch(MatchDto matchDto) {
		Match match = MatchMapper.INSTANCE.toEntity(matchDto);

		SeasonData homeTeamData = teamDataRepository.findByTeamIdAndSeasonId(match.getHomeTeamId(), 1);
		SeasonData awayTeamData = teamDataRepository.findByTeamIdAndSeasonId(match.getAwayTeamId(), 1);

		updateTeamStatistics(homeTeamData, match.getHomeTeamScore(), match.getAwayTeamScore(), match.isOvertime());
		updateTeamStatistics(awayTeamData, match.getAwayTeamScore(), match.getHomeTeamScore(), match.isOvertime());

		teamDataRepository.save(homeTeamData);
		teamDataRepository.save(awayTeamData);

		return matchRepository.save(match);
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
			dto.setHomeTeamName(teams.get(dto.getHomeTeamId()).getName());
			dto.setAwayTeamName(teams.get(dto.getAwayTeamId()).getName());
			dto.setHomeTeamShortName(teams.get(dto.getHomeTeamId()).getShortName());
			dto.setAwayTeamShortName(teams.get(dto.getAwayTeamId()).getShortName());
			return dto;
		}).collect(Collectors.toList());

	}

}