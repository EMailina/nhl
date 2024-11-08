package com.league.nhl.league.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HistoryPositionTeam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long teamId;
	private Integer position;
	private Integer positionConference;
	private Integer positionDivision;
	private LocalDate dateSaved;

	// Getters and setters
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public LocalDate getDateSaved() {
		return dateSaved;
	}

	public void setDateSaved(LocalDate dateSaved) {
		this.dateSaved = dateSaved;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPositionConference() {
		return positionConference;
	}

	public void setPositionConference(int positionConference) {
		this.positionConference = positionConference;
	}

	public Integer getPositionDivision() {
		return positionDivision;
	}

	public void setPositionDivision(Integer positionDivision) {
		this.positionDivision = positionDivision;
	}

}
