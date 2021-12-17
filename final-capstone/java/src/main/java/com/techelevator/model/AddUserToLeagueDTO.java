package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddUserToLeagueDTO {

    @JsonProperty("league_id")
    private long LeagueId;
    @JsonProperty("user_id")
    private long userId;

    public long getLeagueId() {
        return LeagueId;
    }

    public void setLeagueId(long leagueId) {
        LeagueId = leagueId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
