package com.techelevator.model;

public class LeagueInviteDTO {
    private Long status;
    private Long memberId;
    private Long leagueId;

    public LeagueInviteDTO() {}

    public LeagueInviteDTO(Long memberId, Long leagueId) {
        this.memberId = memberId;
        this.leagueId = leagueId;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
