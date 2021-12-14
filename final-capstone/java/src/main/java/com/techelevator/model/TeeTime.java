package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeeTime {
    @JsonProperty("tee_time_id")
    private Long teeTimeId;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("league_id")
    private Long leagueId;
    @JsonProperty("tee_time_date")
    private String teeTimeDate;
    @JsonProperty("tee_time")
    private String time;

    public TeeTime(Long teeTimeId, Long userId, Long leagueId, String teeTimeDate, String time) {
        this.teeTimeId = teeTimeId;
        this.userId = userId;
        this.leagueId = this.leagueId;
        this.teeTimeDate = teeTimeDate;
        this.time = time;
    }

    public TeeTime(Long userId, Long leagueId, String teeTimeDate, String time) {
        this.userId = userId;
        this.leagueId = leagueId;
        this.teeTimeDate = teeTimeDate;
        this.time = time;
    }

    public TeeTime(){}

    public Long getTeeTimeId() {
        return teeTimeId;
    }

    public void setTeeTimeId(Long teeTimeId) {
        this.teeTimeId = teeTimeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public String getTeeTimeDate() {
        return teeTimeDate;
    }

    public void setTeeTimeDate(String teeTimeDate) {
        this.teeTimeDate = teeTimeDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
