package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeeTime {
    @JsonProperty("tee_time_id")
    private Long teeTimeId;
    @JsonProperty("tee_time_date")
    private String teeTimeDate;
    @JsonProperty("tee_time")
    private String time;

    public TeeTime(Long teeTimeId, String teeTimeDate, String time) {
        this.teeTimeId = teeTimeId;
        this.teeTimeDate = teeTimeDate;
        this.time = time;
    }

    public TeeTime(String teeTimeDate, String time) {
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
