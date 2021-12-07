package com.techelevator.model;

import java.util.List;
import java.util.Set;

public class League {

    private Long id;
    private String leagueName;
    private String courseName;
    private String leagueAdmin;
    private String dayOfWeek;
    private List<LeagueMemberDTO> leagueMembers;
    private Boolean active;


    public League() {}
    public League(String leagueName, String courseName, String leagueAdmin, String dayOfWeek) {
        this.leagueName = leagueName;
        this.courseName = courseName;
        this.leagueAdmin = leagueAdmin;
        this.dayOfWeek = dayOfWeek;
        this.active = true;
    }
    public League(Long id, String leagueName, String courseName, String leagueAdmin, String dayOfWeek) {
        this.id = id;
        this.leagueName = leagueName;
        this.courseName = courseName;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLeagueAdmin() {
        return leagueAdmin;
    }

    public void setLeagueAdmin(String leagueAdmin) {
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

    public void setLeagueMembers(List<LeagueMemberDTO> leagueMembers) {
        this.leagueMembers = leagueMembers;
    }

    public void setLeagueMembers(String leagueMembers) {
        String[] members = leagueMembers.split(",");
        for(String member : members)
            this.leagueMembers.add(new LeagueMemberDTO(member));
        }
    }


