package com.league.nhl.league.entity;

import com.league.nhl.league.enums.Owner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "SEASON_DATA")
public class SeasonData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "ID")
	private Long id;

	@Column(name = "SEASON_ID", nullable = false)
	private Long seasonId;

	@Column(name = "TEAM_ID", nullable = false)
	private Long teamId;

	@Enumerated(EnumType.STRING)
	@Column(name = "OWNER", nullable = false)
	private Owner owner;

	@Column(name = "POINTS", nullable = false)
	private int points;

	@Column(name = "WINS", nullable = false)
	private int wins;

	@Column(name = "LOSSES", nullable = false)
	private int losses;

	@Column(name = "WIN_OT", nullable = false)
	private int winsOt;

	@Column(name = "lossesOt", nullable = false)
	private int lossesOt;

	@Column(name = "goalsScored", nullable = false)
	private int goalsScored;

	@Column(name = "goalsAgainst", nullable = false)
	private int goalsAgainst;

	@Column(name = "pointsBeforeRound", nullable = false)
	private int pointsBeforeRound;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Long seasonId) {
		this.seasonId = seasonId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getWinsOt() {
		return winsOt;
	}

	public void setWinsOt(int winsOt) {
		this.winsOt = winsOt;
	}

	public int getLossesOt() {
		return lossesOt;
	}

	public void setLossesOt(int lossesOt) {
		this.lossesOt = lossesOt;
	}

	public int getGoalsScored() {
		return goalsScored;
	}

	public void setGoalsScored(int goalsScored) {
		this.goalsScored = goalsScored;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public int getPointsBeforeRound() {
		return pointsBeforeRound;
	}

	public void setPointsBeforeRound(int pointsBeforeRound) {
		this.pointsBeforeRound = pointsBeforeRound;
	}

}
