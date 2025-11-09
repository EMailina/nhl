package com.league.nhl.league.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.league.nhl.league.entity.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

	List<Match> findBySeasonId(Long seasonId, Sort sort);

	long countBySeasonId(Long seasonId);

	List<Match> findBySeasonId(Long seasonId);

	List<Match> findBySeasonIdAndSimulatedFalse(Long seasonId);

	@Query("SELECT m FROM Match m WHERE m.seasonId = :seasonId AND (m.homeTeamId = :teamId OR m.awayTeamId = :teamId) order by m.createdAt DESC")
	List<Match> findBySeasonIdAndTeamId(@Param("seasonId") Long seasonId, @Param("teamId") Long teamId);

	@Query("SELECT m FROM Match m WHERE m.seasonId = :seasonId AND "
			+ "((m.homeTeamId = :teamId1 AND m.awayTeamId = :teamId2) OR "
			+ "(m.homeTeamId = :teamId2 AND m.awayTeamId = :teamId1))")
	List<Match> findBySeasonIdAndTwoTeams(@Param("seasonId") Long seasonId, @Param("teamId1") Long teamId1,
			@Param("teamId2") Long teamId2);
	
    Long countBySeasonIdAndSimulated(Long seasonId, boolean simulated);

}