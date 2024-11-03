package com.league.nhl.league.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.league.nhl.league.dto.MatchDto;
import com.league.nhl.league.dto.MatchViewDto;
import com.league.nhl.league.entity.Match;
import com.league.nhl.league.mapper.MatchMapper;
import com.league.nhl.league.service.MatchService;

@RestController
@RequestMapping("/api/match")
public class MatchController {

	@Autowired
	MatchService matchService;

	@PostMapping("/create")
	public MatchDto createMatch(@RequestBody MatchDto matchDto) {
		Match createdMatch = matchService.createMatch(matchDto);
		MatchDto createdMatchDTO = MatchMapper.INSTANCE.toDto(createdMatch);
		return createdMatchDTO;
	}

	@GetMapping("/getMatchesForSeason/{seasonId}")
	public ResponseEntity<List<MatchViewDto>> getAllMatchesForSeason(@PathVariable Long seasonId) {
		List<MatchViewDto> matches = matchService.getAllMatchesForSeason(seasonId);
		return ResponseEntity.ok(matches);
	}
}