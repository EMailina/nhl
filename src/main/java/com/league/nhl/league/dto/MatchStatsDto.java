package com.league.nhl.league.dto;

public class MatchStatsDto {
	private Long simulatedTrueCount;
	private Long simulatedFalseCount;

	public MatchStatsDto(Long simulatedTrueCount, Long simulatedFalseCount) {
		this.simulatedTrueCount = simulatedTrueCount;
		this.simulatedFalseCount = simulatedFalseCount;
	}

	public Long getSimulatedTrueCount() {
		return simulatedTrueCount;
	}

	public void setSimulatedTrueCount(Long simulatedTrueCount) {
		this.simulatedTrueCount = simulatedTrueCount;
	}

	public Long getSimulatedFalseCount() {
		return simulatedFalseCount;
	}

	public void setSimulatedFalseCount(Long simulatedFalseCount) {
		this.simulatedFalseCount = simulatedFalseCount;
	}
}
