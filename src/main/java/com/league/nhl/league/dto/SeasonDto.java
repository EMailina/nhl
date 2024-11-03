package com.league.nhl.league.dto;

import com.league.nhl.league.enums.GameType;

public class SeasonDto {
	private Long id;

	private String name;
	private GameType gameType;
	private String yearPlayed;
	private String yearOrigin;

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

	public String getYearPlayed() {
		return yearPlayed;
	}

	public void setYearPlayed(String yearPlayed) {
		this.yearPlayed = yearPlayed;
	}

	public String getYearOrigin() {
		return yearOrigin;
	}

	public void setYearOrigin(String yearOrigin) {
		this.yearOrigin = yearOrigin;
	}

}