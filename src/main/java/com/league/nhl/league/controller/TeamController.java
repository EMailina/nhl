package com.league.nhl.league.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.league.nhl.league.dto.OwnerPositionDto;
import com.league.nhl.league.dto.TeamDto;
import com.league.nhl.league.dto.TeamTableDto;
import com.league.nhl.league.enums.Conference;
import com.league.nhl.league.enums.Division;
import com.league.nhl.league.service.SeasonDataService;
import com.league.nhl.league.service.TeamService;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

	@Autowired
	private TeamService teamService;

	@Autowired
	SeasonDataService seasonDataService;

	@GetMapping("/getAll")
	public ResponseEntity<List<TeamDto>> getAllTeams() {
		List<TeamDto> teams = teamService.getAllTeams();
		return ResponseEntity.ok(teams);
	}

	// Get a team by ID
	@GetMapping("/get/{id}")
	public ResponseEntity<TeamDto> getTeamById(@PathVariable Long id) {
		return teamService.getTeamById(id).map(TeamDto -> ResponseEntity.ok(TeamDto))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	// Create a new team
	@PostMapping("/create")
	public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto TeamDto) {
		TeamDto createdTeam = teamService.createTeam(TeamDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
	}

	// Delete a team by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
		teamService.deleteTeam(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/teams-data/{seasonId}")
	public ResponseEntity<List<TeamTableDto>> getTeamSeasonDataForSeason(@PathVariable Long seasonId) {
		List<TeamTableDto> teamSeasonDataList = seasonDataService.getTeamSeasonDataForSeason(seasonId);
		return ResponseEntity.ok(teamSeasonDataList);
	}
	
	@GetMapping("/teams-data/{seasonId}/{conference}")
	public ResponseEntity<List<TeamTableDto>> getTeamSeasonDataForConference(@PathVariable Long seasonId, @PathVariable Conference conference) {
		List<TeamTableDto> teamSeasonDataList = seasonDataService.getTeamConferenceDataForSeason(seasonId, conference);
		return ResponseEntity.ok(teamSeasonDataList);
	}
	
	@GetMapping("/teams-data-division/{seasonId}/{division}")
	public ResponseEntity<List<TeamTableDto>> getTeamSeasonDataForDivision(@PathVariable Long seasonId, @PathVariable Division division) {
		List<TeamTableDto> teamSeasonDataList = seasonDataService.getTeamDivisionDataForSeason(seasonId, division);
		return ResponseEntity.ok(teamSeasonDataList);
	}
	
	@GetMapping("/owner-table/{seasonId}")
	public ResponseEntity<List<OwnerPositionDto>> getOwnerTable(@PathVariable Long seasonId) {
		List<OwnerPositionDto> dtos = seasonDataService.getOwnerPositions(seasonId);
		return ResponseEntity.ok(dtos);
	}
}