package com.league.nhl.team.resources.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.league.nhl.team.resources.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

	public Optional<Resource> findByTeamId(Long teamId);
}