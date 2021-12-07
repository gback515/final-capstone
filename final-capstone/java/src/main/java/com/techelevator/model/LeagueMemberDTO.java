package com.techelevator.model;

public class LeagueMemberDTO {
    private Long userId;

    public LeagueMemberDTO() {}

    public LeagueMemberDTO(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
