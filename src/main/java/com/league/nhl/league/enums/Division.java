package com.league.nhl.league.enums;

public enum Division {
	ATLANTIC(Conference.EASTERN), METROPOLITAN(Conference.EASTERN), CENTRAL(Conference.WESTERN),
	PACIFIC(Conference.WESTERN);

	private Conference conference;

	Division(Conference conference) {
		this.conference = conference;
	}

	public Conference getConference() {
		return conference;
	}

}