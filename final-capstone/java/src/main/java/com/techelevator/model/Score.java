package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Score {
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("tee_time_id")
    private Long teeTimeId;
    @JsonProperty("score")
    private Long score;


    public Score() {}

    public Score(Long userId, Long teeTimeId, Long score) {
        this.userId = userId;
        this.teeTimeId = teeTimeId;
        this.score = score;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTeeTimeId() {
        return teeTimeId;
    }

    public void setTeeTimeId(Long teeTimeId) {
        this.teeTimeId = teeTimeId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
