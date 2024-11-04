package com.league.nhl.league.dto;

import java.time.LocalDateTime;

public class MatchViewDto extends MatchDto {

	private String homeTeamName;
	private String awayTeamName;
	private String homeTeamShortName;
	private String awayTeamShortName;
	private LocalDateTime createdAt;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public String getHomeTeamShortName() {
		return homeTeamShortName;
	}

	public void setHomeTeamShortName(String homeTeamShortName) {
		this.homeTeamShortName = homeTeamShortName;
	}

	public String getAwayTeamShortName() {
		return awayTeamShortName;
	}

	public void setAwayTeamShortName(String awayTeamShortName) {
		this.awayTeamShortName = awayTeamShortName;
	}

}
