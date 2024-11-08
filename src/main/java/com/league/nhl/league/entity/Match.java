package com.league.nhl.league.entity;

import com.league.nhl.conf.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MATCH")
public class Match extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "HOME_TEAM_ID", nullable = false)
	private Long homeTeamId;

	@Column(name = "AWAY_TEAM_ID", nullable = false)
	private Long awayTeamId;

	@Column(name = "SEASON_ID", nullable = false)
	private Long seasonId;

	@Column(name = "HOME_TEAM_SCORE", nullable = false)
	private int homeTeamScore;

	@Column(name = "AWAY_TEAM_SCORE", nullable = false)
	private int awayTeamScore;

	@Column(name = "OVERTIME", nullable = false)
	private boolean overtime;

	@Column(name = "SIMULATED", nullable = false)
	private boolean simulated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHomeTeamId() {
		return homeTeamId;
	}

	public void setHomeTeamId(Long homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public Long getAwayTeamId() {
		return awayTeamId;
	}

	public void setAwayTeamId(Long awayTeamId) {
		this.awayTeamId = awayTeamId;
	}

	public int getHomeTeamScore() {
		return homeTeamScore;
	}

	public void setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	public int getAwayTeamScore() {
		return awayTeamScore;
	}

	public void setAwayTeamScore(int awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}

	public boolean isOvertime() {
		return overtime;
	}

	public void setOvertime(boolean overtime) {
		this.overtime = overtime;
	}

	public boolean isSimulated() {
		return simulated;
	}

	public void setSimulated(boolean simulated) {
		this.simulated = simulated;
	}

	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}

}
