package com.league.nhl.league.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.league.nhl.league.service.HistoryPositionService;

@RestController
@RequestMapping("/api/historyPositions")
public class HistoryTeamController {

	@Autowired
	HistoryPositionService historyPositionService;

	@PostMapping("/savePositions")
	public ResponseEntity<String> savePositions() {
		historyPositionService.saveTeamPositionsToHistoryCron();

		return ResponseEntity.ok("Saved successfully");
	}
}
