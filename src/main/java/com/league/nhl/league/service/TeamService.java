package com.league.nhl.league.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.league.nhl.league.dto.TeamDto;
import com.league.nhl.league.dto.TeamTableDto;
import com.league.nhl.league.entity.Team;
import com.league.nhl.league.mapper.TeamMapper;
import com.league.nhl.league.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	SeasonDataService seasonDataService;

	public List<TeamDto> getAllTeams() {
		return teamRepository.findAll().stream().map(TeamMapper.INSTANCE::toDTO).collect(Collectors.toList());
	}

	public TeamTableDto getTeamById(Long seasonId, Long id) {
		return seasonDataService.getTeamSeasonDataForSeasonAndTeam(seasonId, List.of(id)).get(0);
	}

	public TeamDto createTeam(TeamDto teamDTO) {
		Team team = TeamMapper.INSTANCE.toEntity(teamDTO);
		Team savedTeam = teamRepository.save(team);
		return TeamMapper.INSTANCE.toDTO(savedTeam);
	}

	public void deleteTeam(Long id) {
		teamRepository.deleteById(id);
	}

	public Optional<TeamDto> getTeamById(Long id) {
		return teamRepository.findById(id).map(TeamMapper.INSTANCE::toDTO);
	}

	public List<TeamTableDto> getExtendedTeams(Long seasonId, Long teamId1, Long teamId2) {
		return seasonDataService.getTeamSeasonDataForSeasonAndTeam(seasonId, List.of(teamId1, teamId2));

	}
}
