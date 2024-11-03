package com.league.nhl.league.entity;

import com.league.nhl.league.enums.Conference;
import com.league.nhl.league.enums.Division;
import com.league.nhl.league.enums.Owner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TEAM")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "SHORT_NAME", length = 4)
	private String shortName;

	@Column(name = "STRENGTH")
	private Integer strength;

	@Enumerated(EnumType.STRING)
	@Column(name = "OWNER", length = 100)
	private Owner owner;

	@Enumerated(EnumType.STRING)
	@Column(name = "DIVISION", nullable = false)
	private Division division;

	@Enumerated(EnumType.STRING)
	@Column(name = "CONFERENCE", nullable = false)
	private Conference conference;

	@Column(name = "GOALIE_STRENGTH")
	private Integer goalieStrength;

	@Column(name = "DEFENSE_STRENGTH")
	private Integer defenseStrength;

	@Column(name = "OFFENSE_STRENGTH")
	private Integer offenseStrength;

	public Team() {
	}

	public Team(Long id, String name, String shortName, Integer strength, Owner owner, Division division,
			Conference conference, String logoUrl) {
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.strength = strength;
		this.owner = owner;
		this.division = division;
		this.conference = conference;
	}

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

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
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

}