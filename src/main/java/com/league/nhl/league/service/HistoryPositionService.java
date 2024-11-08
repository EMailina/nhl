package com.league.nhl.league.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.league.nhl.league.dto.TeamTableDto;
import com.league.nhl.league.entity.HistoryPositionTeam;
import com.league.nhl.league.enums.Division;
import com.league.nhl.league.repository.HistoryPositionRepository;

@Service
public class HistoryPositionService {

	@Autowired
	private SeasonDataService seasonDataService;

	@Autowired
	private HistoryPositionRepository historyPositionRepository;

	@Scheduled(cron = "0 0 4 * * *")
	public void saveTeamPositionsToHistoryCron() {
		List<TeamTableDto> currentTeamPositions = seasonDataService.getTeamSeasonDataForSeason(1l);

		LocalDate currentDate = LocalDate.now().minusDays(1);

		List<HistoryPositionTeam> historyEntries = currentTeamPositions.stream().map(teamData -> {
			HistoryPositionTeam historyEntry = new HistoryPositionTeam();
			historyEntry.setTeamId(teamData.getTeamId());
			historyEntry.setPosition(teamData.getPosition());
			historyEntry.setDateSaved(currentDate);
			return historyEntry;
		}).collect(Collectors.toList());

		historyPositionRepository.saveAll(historyEntries);
	}
}
