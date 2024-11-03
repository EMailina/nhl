package com.league.nhl.league.dto;

import java.util.List;

public class CreateSeasonDto {

	Long seasonId;

	List<Long> teamIds;

	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}

	public List<Long> getTeamIds() {
		return teamIds;
	}

	public void setTeamIds(List<Long> teamIds) {
		this.teamIds = teamIds;
	}

}
