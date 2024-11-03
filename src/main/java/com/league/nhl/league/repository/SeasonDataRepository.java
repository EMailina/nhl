package com.league.nhl.league.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.league.nhl.league.entity.SeasonData;

@Repository
public interface SeasonDataRepository extends JpaRepository<SeasonData, Long> {
	
	List<SeasonData> findBySeasonId(Long seasonId);

	List<SeasonData> findByTeamId(Long teamId);

	SeasonData findByTeamIdAndSeasonId(Long teamId, int seasonId);
}