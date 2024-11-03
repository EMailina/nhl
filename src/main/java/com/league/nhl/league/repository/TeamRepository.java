package com.league.nhl.league.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.league.nhl.league.entity.Team;
import com.league.nhl.league.enums.Conference;
import com.league.nhl.league.enums.Division;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

	List<Team> findAllByConference(Conference conference);

	List<Team> findAllByDivision(Division division);
}