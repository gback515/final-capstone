package com.techelevator.model;


// For use with Vue to package data
public class LeaderboardDTO {
    private Long userId;
    private String user;
    private Long leagueId;
    private String leagueName;
    private Long matchId;
    private String matchDate;
    private int score;

    public LeaderboardDTO() {}

    public LeaderboardDTO(Long userId, Long leagueId, Long matchId, int score) {
        this.userId = userId;
        this.leagueId = leagueId;
        this.matchId = matchId;
        this.score = score;
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

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
