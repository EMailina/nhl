package com.league.nhl.league.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.league.nhl.league.entity.HistoryPositionTeam;

@Repository
public interface HistoryPositionRepository extends JpaRepository<HistoryPositionTeam, Long> {

	@Query("SELECT h FROM HistoryPositionTeam h WHERE h.dateSaved = (SELECT MAX(h2.dateSaved) FROM HistoryPositionTeam h2 WHERE h2.teamId = h.teamId) AND h.teamId = :teamId")
	Optional<HistoryPositionTeam> findLastTeamData(Long teamId);

}
