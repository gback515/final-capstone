package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class League {

    private Long id;
    @JsonProperty("league_name")
    private String leagueName;
    @JsonProperty("league_course")
    private Long leagueCourse;
    @JsonProperty("league_admin")
    private Long leagueAdmin;
    @JsonProperty("day_of_week")
    private String dayOfWeek;
    private List<LeagueMemberDTO> leagueMembers;
    private Boolean active;


    public League() {}
    public League(String leagueName, Long leagueAdmin, Long leagueCourse, String dayOfWeek) {
        this.leagueName = leagueName;
        this.leagueCourse = leagueCourse;
        this.leagueAdmin = leagueAdmin;
        this.dayOfWeek = dayOfWeek;
        this.active = true;
    }
    public League(Long id, String leagueName, Long leagueAdmin, Long leagueCourse, String dayOfWeek) {
        this.id = id;
        this.leagueName = leagueName;
        this.leagueCourse = leagueCourse;
        this.leagueAdmin = leagueAdmin;
        this.dayOfWeek = dayOfWeek;
        this.active = true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Long getLeagueCourse() {
        return leagueCourse;
    }

    public void setLeagueCourse(Long leagueCourse) {
        this.leagueCourse = leagueCourse;
    }

    public Long getLeagueAdmin() {
        return leagueAdmin;
    }

    public void setLeagueAdmin(Long leagueAdmin) {
        this.leagueAdmin = leagueAdmin;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<LeagueMemberDTO> getLeagueMembers() {
        return leagueMembers;
    }

    public Boolean getActive() {
        return active;
    }

    public void isActive(boolean active) {
        this.active = active;
    }

    public void setLeagueMembers(List<LeagueMemberDTO> leagueMembers) {
        this.leagueMembers = leagueMembers;
    }

    public void setLeagueMembers(String leagueMembers) {
        String[] members = leagueMembers.split(",");
        for(String member : members)
            this.leagueMembers.add(new LeagueMemberDTO(Long.parseLong(member)));
        }
    }


