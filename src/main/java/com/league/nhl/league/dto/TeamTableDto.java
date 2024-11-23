package com.league.nhl.league.dto;

import com.league.nhl.league.enums.Conference;
import com.league.nhl.league.enums.Division;
import com.league.nhl.league.enums.Owner;

public class TeamTableDto {
	private Long teamId;
	private String teamName;
	private String shortName;
	private Owner owner;
	private int points;
	private int playedGames;
	private int position;
	private int wins;
	private int losses;
	private int winsOt;
	private int lossesOt;
	private int goalsScored;
	private int goalsAgainst;
	private int pointsBeforeRound;
	private Division division;
	private Conference conference;
	private boolean playOff;
	private boolean wildCard;
	private int positionBeforeRound;
	private int positionConferenceBeforeRound;
	private int positionDivisionBeforeRound;
	private Integer goalieStrength;
	private Integer defenseStrength;
	private Integer offenseStrength;
	
	
	public Integer getGoalieStrength() {
		return goalieStrength;
	}

	public void setGoalieStrength(Integer goalieStrength) {
		this.goalieStrength = goalieStrength;
	}

	public Integer getDefenseStrength() {
		return defenseStrength;
	}

	public void setDefenseStrength(Integer defenseStrength) {
		this.defenseStrength = defenseStrength;
	}

	public Integer getOffenseStrength() {
		return offenseStrength;
	}

	public void setOffenseStrength(Integer offenseStrength) {
		this.offenseStrength = offenseStrength;
	}

	public int getPositionBeforeRound() {
		return positionBeforeRound;
	}

	public void setPositionBeforeRound(int positionBeforeRound) {
		this.positionBeforeRound = positionBeforeRound;
	}

	public boolean isPlayOff() {
		return playOff;
	}

	public void setPlayOff(boolean playOff) {
		this.playOff = playOff;
	}

	public boolean isWildCard() {
		return wildCard;
	}

	public void setWildCard(boolean wildCard) {
		this.wildCard = wildCard;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	public int getPlayedGames() {
		return playedGames;
	}

	public void setPlayedGames(int playedGames) {
		this.playedGames = playedGames;
	}

	public int getPosition() {
		return position;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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

	public int getPositionConferenceBeforeRound() {
		return positionConferenceBeforeRound;
	}

	public void setPositionConferenceBeforeRound(int positionConferenceBeforeRound) {
		this.positionConferenceBeforeRound = positionConferenceBeforeRound;
	}

	public int getPositionDivisionBeforeRound() {
		return positionDivisionBeforeRound;
	}

	public void setPositionDivisionBeforeRound(int positionDivisionBeforeRound) {
		this.positionDivisionBeforeRound = positionDivisionBeforeRound;
	}
	

}
