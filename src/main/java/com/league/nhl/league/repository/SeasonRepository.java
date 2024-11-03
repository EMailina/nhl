package com.league.nhl.league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.league.nhl.league.entity.Season;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
	// You can add custom query methods if needed
}