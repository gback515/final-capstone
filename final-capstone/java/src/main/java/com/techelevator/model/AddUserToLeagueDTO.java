package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddUserToLeagueDTO {

    @JsonProperty("league_id")
    private long LeagueId;
    @JsonProperty("userName")
    private String userName;

    public long getLeagueId() {
        return LeagueId;
    }

    public void setLeagueId(long leagueId) {
        LeagueId = leagueId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
