package com.league.nhl.league.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.league.nhl.league.dto.PlayoffPairDto;
import com.league.nhl.league.service.PlayoffService;

@RestController
@RequestMapping("/api/playoffs")
public class PlayoffController {

    @Autowired
    private PlayoffService playoffService;

    @GetMapping("/expected/{seasonId}")
    public ResponseEntity<List<PlayoffPairDto>> getExpectedPlayoffs(@PathVariable Long seasonId) {
        List<PlayoffPairDto> pairs = playoffService.getExpectedPlayoffs(seasonId);
        return ResponseEntity.ok(pairs);
    }
}
