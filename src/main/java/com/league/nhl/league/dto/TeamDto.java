package com.league.nhl.league.dto;

import com.league.nhl.league.enums.Conference;
import com.league.nhl.league.enums.Division;

public class TeamDto {
	private Long id;
	private String name;
	private String shortName;
	private Integer strength;
	private String owner;
	private Division division;
	private Conference conference;

	private Integer goalieStrength;
	private Integer defenseStrength;
	private Integer offenseStrength;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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

	public Integer getDefenseStrength() {
		return defenseStrength;
	}

	public void setDefenseStrength(Integer defenseStrength) {
		this.defenseStrength = defenseStrength;
	}

	public Integer getGoalieStrength() {
		return goalieStrength;
	}

	public void setGoalieStrength(Integer goalieStrength) {
		this.goalieStrength = goalieStrength;
	}

	public Integer getOffenseStrength() {
		return offenseStrength;
	}

	public void setOffenseStrength(Integer offenseStrength) {
		this.offenseStrength = offenseStrength;
	}

}
