package com.league.nhl.league.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.league.nhl.league.dto.PlayoffPairDto;
import com.league.nhl.league.dto.TeamTableDto;
import com.league.nhl.league.enums.Conference;
import com.league.nhl.league.enums.Division;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PlayoffService {

    @Autowired
    private SeasonDataService seasonDataService;

    public List<PlayoffPairDto> getExpectedPlayoffs(Long seasonId) {
        List<TeamTableDto> all = seasonDataService.getTeamSeasonDataForSeason(seasonId);
        List<PlayoffPairDto> result = new ArrayList<>();

        for (Conference conf : Conference.values()) {
            List<TeamTableDto> confTeams = all.stream().filter(t -> t.getConference() == conf)
                    .collect(Collectors.toList());

            // group by division and sort teams inside division by our ranking
            Map<Division, List<TeamTableDto>> divisionTeams = confTeams.stream()
                    .collect(Collectors.groupingBy(TeamTableDto::getDivision));

            // sort teams inside each division by ranking
            for (Map.Entry<Division, List<TeamTableDto>> e : divisionTeams.entrySet()) {
                e.getValue().sort(this::compareByRanking);
            }

            // collect division winners and sort them by ranking
            List<Division> divisions = new ArrayList<>(divisionTeams.keySet());
            divisions.sort((d1, d2) -> {
                List<TeamTableDto> list1 = divisionTeams.get(d1);
                List<TeamTableDto> list2 = divisionTeams.get(d2);
                TeamTableDto w1 = list1 != null && !list1.isEmpty() ? list1.get(0) : null;
                TeamTableDto w2 = list2 != null && !list2.isEmpty() ? list2.get(0) : null;
                if (w1 == null && w2 == null) return 0;
                if (w1 == null) return 1;
                if (w2 == null) return -1;
                return compareByRanking(w1, w2);
            });

            // wildcards sorted by ranking (best first)
            List<TeamTableDto> wildcards = confTeams.stream().filter(TeamTableDto::isWildCard)
                    .sorted(this::compareByRanking).collect(Collectors.toList());

            // assign wildcards to divisions: best division winner should face the lower-ranked wildcard
            Map<Division, TeamTableDto> wildcardAssigned = new HashMap<>();
            for (int i = 0; i < Math.min(wildcards.size(), divisions.size()); i++) {
                // assign in reverse order so best division gets the worst available wildcard
                wildcardAssigned.put(divisions.get(i), wildcards.get(wildcards.size() - 1 - i));
            }

            // build matchups for each division
            for (Division div : divisions) {
                List<TeamTableDto> teams = divisionTeams.get(div);
                if (teams == null || teams.isEmpty()) continue;

                TeamTableDto seed1 = teams.size() > 0 ? teams.get(0) : null;
                TeamTableDto seed2 = teams.size() > 1 ? teams.get(1) : null;
                TeamTableDto seed3 = teams.size() > 2 ? teams.get(2) : null;
                TeamTableDto seed4 = teams.size() > 3 ? teams.get(3) : null;

                TeamTableDto assignedWildcard = wildcardAssigned.get(div);

                // First matchup: seed1 vs wildcard (if assigned) else seed1 vs seed4 (if exists) else seed1 vs seed3
                if (seed1 != null) {
                    TeamTableDto opponent = assignedWildcard != null ? assignedWildcard : (seed4 != null ? seed4 : seed3);
                    if (opponent != null) {
                        PlayoffPairDto p = new PlayoffPairDto(seasonId, conf, div, seed1.getTeamId(), seed1.getTeamName(),
                                1, seed1.isWildCard(), opponent.getTeamId(), opponent.getTeamName(), opponent.getPosition(),
                                opponent.isWildCard(), "First Round");
                        result.add(p);
                    }
                }

                // Second matchup: seed2 vs seed3
                if (seed2 != null && seed3 != null) {
                    PlayoffPairDto p2 = new PlayoffPairDto(seasonId, conf, div, seed2.getTeamId(), seed2.getTeamName(),
                            2, seed2.isWildCard(), seed3.getTeamId(), seed3.getTeamName(), 3, seed3.isWildCard(),
                            "First Round");
                    result.add(p2);
                }
            }
        }

        return result;
    }

    private int compareByRanking(TeamTableDto a, TeamTableDto b) {
        if (a == null && b == null) return 0;
        if (a == null) return 1;
        if (b == null) return -1;

        long scoreA = rankingScore(a);
        long scoreB = rankingScore(b);
        return Long.compare(scoreB, scoreA); // descending
    }

    private long rankingScore(TeamTableDto dto) {
        long score = dto.getPoints() * 100000L;
        score = score - dto.getPlayedGames() * 1000L;
        score += (dto.getWins() + dto.getWinsOt()) * 100L;
        score += (dto.getGoalsScored() - dto.getGoalsAgainst()) * 10L;
        return score;
    }
}
