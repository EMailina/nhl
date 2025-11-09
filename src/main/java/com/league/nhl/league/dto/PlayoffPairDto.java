package com.league.nhl.league.dto;

import com.league.nhl.league.enums.Conference;
import com.league.nhl.league.enums.Division;

public class PlayoffPairDto {
    private Long seasonId;
    private Conference conference;
    private Division division;

    private Long teamHomeId;
    private String teamHomeName;
    private int teamHomeSeed;
    private boolean teamHomeIsWildcard;

    private Long teamAwayId;
    private String teamAwayName;
    private int teamAwaySeed;
    private boolean teamAwayIsWildcard;

    private String round;

    public PlayoffPairDto() {
    }

    public PlayoffPairDto(Long seasonId, Conference conference, Division division, Long teamHomeId, String teamHomeName,
            int teamHomeSeed, boolean teamHomeIsWildcard, Long teamAwayId, String teamAwayName, int teamAwaySeed,
            boolean teamAwayIsWildcard, String round) {
        this.seasonId = seasonId;
        this.conference = conference;
        this.division = division;
        this.teamHomeId = teamHomeId;
        this.teamHomeName = teamHomeName;
        this.teamHomeSeed = teamHomeSeed;
        this.teamHomeIsWildcard = teamHomeIsWildcard;
        this.teamAwayId = teamAwayId;
        this.teamAwayName = teamAwayName;
        this.teamAwaySeed = teamAwaySeed;
        this.teamAwayIsWildcard = teamAwayIsWildcard;
        this.round = round;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Long getTeamHomeId() {
        return teamHomeId;
    }

    public void setTeamHomeId(Long teamHomeId) {
        this.teamHomeId = teamHomeId;
    }

    public String getTeamHomeName() {
        return teamHomeName;
    }

    public void setTeamHomeName(String teamHomeName) {
        this.teamHomeName = teamHomeName;
    }

    public int getTeamHomeSeed() {
        return teamHomeSeed;
    }

    public void setTeamHomeSeed(int teamHomeSeed) {
        this.teamHomeSeed = teamHomeSeed;
    }

    public boolean isTeamHomeIsWildcard() {
        return teamHomeIsWildcard;
    }

    public void setTeamHomeIsWildcard(boolean teamHomeIsWildcard) {
        this.teamHomeIsWildcard = teamHomeIsWildcard;
    }

    public Long getTeamAwayId() {
        return teamAwayId;
    }

    public void setTeamAwayId(Long teamAwayId) {
        this.teamAwayId = teamAwayId;
    }

    public String getTeamAwayName() {
        return teamAwayName;
    }

    public void setTeamAwayName(String teamAwayName) {
        this.teamAwayName = teamAwayName;
    }

    public int getTeamAwaySeed() {
        return teamAwaySeed;
    }

    public void setTeamAwaySeed(int teamAwaySeed) {
        this.teamAwaySeed = teamAwaySeed;
    }

    public boolean isTeamAwayIsWildcard() {
        return teamAwayIsWildcard;
    }

    public void setTeamAwayIsWildcard(boolean teamAwayIsWildcard) {
        this.teamAwayIsWildcard = teamAwayIsWildcard;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }
}
