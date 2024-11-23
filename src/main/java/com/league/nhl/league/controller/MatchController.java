package com.league.nhl.league.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.league.nhl.league.dto.MatchDto;
import com.league.nhl.league.dto.MatchInfoDto;
import com.league.nhl.league.dto.MatchViewDto;
import com.league.nhl.league.service.MatchService;

@RestController
@RequestMapping("/api/match")
public class MatchController {

	@Autowired
	MatchService matchService;

	@PostMapping("/create")
	public ResponseEntity<MatchDto> createMatch(@RequestBody MatchDto matchDto) {
		MatchViewDto match = matchService.createMatch(matchDto);

		return ResponseEntity.ok(match);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> createMatch(@PathVariable Long id) {
		matchService.deleteMatch(id);
		return ResponseEntity.ok("Match was successfully deleted.");
	}

	@GetMapping("/getMatchesForSeason/{seasonId}")
	public ResponseEntity<List<MatchViewDto>> getAllMatchesForSeason(@PathVariable Long seasonId) {
		List<MatchViewDto> matches = matchService.getAllMatchesForSeason(seasonId);
		return ResponseEntity.ok(matches);
	}

	@GetMapping("/getMatchesForSeason/{seasonId}/{teamId}")
	public ResponseEntity<MatchInfoDto> getMatchInfoForSeasonAndTeam(@PathVariable Long seasonId,
			@PathVariable Long teamId) {
		MatchInfoDto info = matchService.getMatchTeamInfo(seasonId, teamId);
		return ResponseEntity.ok(info);
	}

	@GetMapping("/getMatchesForSeasonAndTeam/{seasonId}/{teamId}")
	public ResponseEntity<List<MatchViewDto>> getAllMatchesForSeason(@PathVariable Long seasonId,
			@PathVariable Long teamId) {
		List<MatchViewDto> matches = matchService.getAllMatchesForSeasonAndTeam(seasonId, teamId);
		return ResponseEntity.ok(matches);
	}

	@GetMapping("/getMatchesForSeasonAndTwoTeams/{seasonId}/{teamId1}/{teamId2}")
	public ResponseEntity<List<MatchViewDto>> getAllMatchesForSeasonAndTwoTeams(@PathVariable Long seasonId,
			@PathVariable Long teamId1, @PathVariable Long teamId2) {
		List<MatchViewDto> matches = matchService.getAllMatchesForSeasonAnd2Teams(seasonId, teamId1, teamId2);
		return ResponseEntity.ok(matches);
	}

}