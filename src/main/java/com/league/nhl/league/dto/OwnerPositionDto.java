package com.league.nhl.league.dto;

import com.league.nhl.league.enums.Owner;

public class OwnerPositionDto {
	private Owner owner;
	private int points = 0;
	private int position;
	private int wins = 0;
	private int losses = 0;
	private int winsOt = 0;
	private int lossesOt = 0;
	private int goalsScored = 0;
	private int goalsAgainst = 0;

	public OwnerPositionDto(Owner owner) {
		this.owner = owner;
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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
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

}
