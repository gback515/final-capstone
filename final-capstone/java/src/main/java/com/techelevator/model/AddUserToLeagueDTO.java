package com.techelevator.model;

public class AddUserToLeagueDTO {

    private long LeagueId;
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
