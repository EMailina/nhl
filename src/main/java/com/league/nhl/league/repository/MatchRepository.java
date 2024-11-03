package com.league.nhl.league.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.league.nhl.league.entity.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

	List<Match> findBySeasonId(Long seasonId, Sort sort);

	long countBySeasonId(Long seasonId);
}