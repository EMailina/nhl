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
import com.league.nhl.league.enums.Conference;
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
		Map<Conference, List<TeamTableDto>> conferencePositions = Map.of(Conference.WESTERN,
				seasonDataService.getTeamConferenceDataForSeason(1L, Conference.WESTERN), Conference.EASTERN,
				seasonDataService.getTeamConferenceDataForSeason(1L, Conference.EASTERN));
		Map<Division, List<TeamTableDto>> divisionPositions = Map.of(Division.ATLANTIC,
				seasonDataService.getTeamDivisionDataForSeason(1L, Division.ATLANTIC), Division.METROPOLITAN,
				seasonDataService.getTeamDivisionDataForSeason(1L, Division.METROPOLITAN), Division.CENTRAL,
				seasonDataService.getTeamDivisionDataForSeason(1L, Division.CENTRAL), Division.PACIFIC,
				seasonDataService.getTeamDivisionDataForSeason(1L, Division.PACIFIC));

		LocalDate currentDate = LocalDate.now().minusDays(1);

		List<HistoryPositionTeam> historyEntries = currentTeamPositions.stream().map(teamData -> {
			HistoryPositionTeam historyEntry = new HistoryPositionTeam();
			historyEntry.setTeamId(teamData.getTeamId());
			historyEntry.setPosition(teamData.getPosition());
			historyEntry.setDateSaved(currentDate);

			historyEntry.setPositionConference(conferencePositions.getOrDefault(teamData.getConference(), List.of())
					.stream().filter(t -> t.getTeamId().equals(teamData.getTeamId())).findFirst()
					.map(TeamTableDto::getPosition).orElse(-1) // Default value if no position found
			);

			historyEntry.setPositionDivision(divisionPositions.getOrDefault(teamData.getDivision(), List.of()).stream()
					.filter(t -> t.getTeamId().equals(teamData.getTeamId())).findFirst().map(TeamTableDto::getPosition)
					.orElse(-1));

			return historyEntry;
		}).collect(Collectors.toList());

		historyPositionRepository.saveAll(historyEntries);
	}
}
