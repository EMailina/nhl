package com.league.nhl.league.entity;

import com.league.nhl.league.enums.GameType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SEASON")
public class Season {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "GAME_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private GameType gameType = GameType.NHL_24;

	@Column(name = "YEAR_ORIGIN")
	private String yearOrigin = "2023/2024";

	@Column(name = "YEAR_PLAYED")
	private String yearPlayed = "2024/2025";

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

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public String getYearOrigin() {
		return yearOrigin;
	}

	public void setYearOrigin(String yearOrigin) {
		this.yearOrigin = yearOrigin;
	}

	public String getYearPlayed() {
		return yearPlayed;
	}

	public void setYearPlayed(String yearPlayed) {
		this.yearPlayed = yearPlayed;
	}

}
