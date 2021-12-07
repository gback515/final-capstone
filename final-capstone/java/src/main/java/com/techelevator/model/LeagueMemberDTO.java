package com.techelevator.model;

public class LeagueMemberDTO {
    private String username;

    public LeagueMemberDTO() {}

    public LeagueMemberDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
