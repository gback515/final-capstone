package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeeTime {
    @JsonProperty("tee_time_id")
    private Long teeTimeId;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("course_id")
    private Long courseId;
    @JsonProperty("tee_time_date")
    private String teeTimeDate;
    @JsonProperty("tee_time")
    private String time;

    public TeeTime(Long teeTimeId, Long userId, Long courseId, String teeTimeDate, String time) {
        this.teeTimeId = teeTimeId;
        this.userId = userId;
        this.courseId = courseId;
        this.teeTimeDate = teeTimeDate;
        this.time = time;
    }

    public TeeTime(Long userId, Long courseId, String teeTimeDate, String time) {
        this.userId = userId;
        this.courseId = courseId;
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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
