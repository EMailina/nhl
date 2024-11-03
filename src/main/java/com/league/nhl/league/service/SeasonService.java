package com.league.nhl.league.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.league.nhl.league.dto.SeasonDto;
import com.league.nhl.league.entity.Season;
import com.league.nhl.league.mapper.SeasonMapper;
import com.league.nhl.league.repository.MatchRepository;
import com.league.nhl.league.repository.SeasonRepository;

@Service
public class SeasonService {

	@Autowired
	private SeasonRepository seasonRepository;

	@Autowired
	private MatchRepository matchRepository;
	
	public SeasonDto createSeason(SeasonDto seasonDto) {
		Season season = SeasonMapper.INSTANCE.toEntity(seasonDto);
		if (season.getYearOrigin() == null) {
			season.setYearOrigin("2023/2024");
		}
		if (season.getYearPlayed() == null) {
			season.setYearPlayed("2024/2025");
		}
		season = seasonRepository.save(season);
		return SeasonMapper.INSTANCE.toDto(season);
	}

	public List<SeasonDto> getAllSeasons() {
		return seasonRepository.findAll().stream().map(SeasonMapper.INSTANCE::toDto).collect(Collectors.toList());
	}

	public SeasonDto getSeason(Long seasonId) {
		SeasonDto dto = SeasonMapper.INSTANCE.toDto(seasonRepository.findById(seasonId).get());
		long matchCount = matchRepository.countBySeasonId(seasonId);
		dto.setCountOfPlayedMatches((int)matchCount);
		return dto;
	}
}
