package com.league.nhl.league.dto;

public class MatchInfoDto {

	private long id;

	private int playedGames;
	private int playedSimGames;

	private int simWins;
	private int simLosses;
	private int simWinsOt;
	private int simLossesOt;

	private int wins;
	private int losses;
	private int winsOt;
	private int lossesOt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPlayedGames() {
		return playedGames;
	}

	public void setPlayedGames(int playedGames) {
		this.playedGames = playedGames;
	}

	public int getPlayedSimGames() {
		return playedSimGames;
	}

	public void setPlayedSimGames(int playedSimGames) {
		this.playedSimGames = playedSimGames;
	}

	public int getSimWins() {
		return simWins;
	}

	public void setSimWins(int simWins) {
		this.simWins = simWins;
	}

	public int getSimLosses() {
		return simLosses;
	}

	public void setSimLosses(int simLosses) {
		this.simLosses = simLosses;
	}

	public int getSimWinsOt() {
		return simWinsOt;
	}

	public void setSimWinsOt(int simWinsOt) {
		this.simWinsOt = simWinsOt;
	}

	public int getSimLossesOt() {
		return simLossesOt;
	}

	public void setSimLossesOt(int simLossesOt) {
		this.simLossesOt = simLossesOt;
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

}
