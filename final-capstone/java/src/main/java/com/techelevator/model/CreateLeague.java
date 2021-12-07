package com.techelevator.model;

public class CreateLeague {

    private Long id;
    private String username;
    private String password;
    private String courseName;
    private Long numberOfPlayers;
    private Boolean created;


    public CreateLeague() {}
    public CreateLeague(Long id, String username, String password, String courseName, Long numberOfPlayers) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.courseName = courseName;
        this.numberOfPlayers = numberOfPlayers;
        this.created = true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Long numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Boolean getCreated() {
        return created;
    }

}
