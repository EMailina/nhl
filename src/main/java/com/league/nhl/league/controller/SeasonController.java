package com.league.nhl.league.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.league.nhl.league.dto.CreateSeasonDto;
import com.league.nhl.league.dto.SeasonDto;
import com.league.nhl.league.service.SeasonDataService;
import com.league.nhl.league.service.SeasonService;

@RestController
@RequestMapping("/season")
public class SeasonController {

	@Autowired
	private SeasonService seasonService;

	@Autowired
	private SeasonDataService seasonDataService;

	@PostMapping("/create")
	public ResponseEntity<SeasonDto> createSeason(@RequestBody SeasonDto seasonDto) {
		SeasonDto createdSeason = seasonService.createSeason(seasonDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdSeason);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<SeasonDto>> getAllSeasons() {
		List<SeasonDto> seasons = seasonService.getAllSeasons();
		return ResponseEntity.ok(seasons);
	}

	@PostMapping("/initialize")
	public ResponseEntity<String> initializeSeasonData(@RequestBody CreateSeasonDto dto) {
		seasonDataService.initializeSeasonDataForTeams(dto.getSeasonId(), dto.getTeamIds());
		return ResponseEntity.ok("Season data initialized for all teams in season " + dto.getSeasonId());
	}
}