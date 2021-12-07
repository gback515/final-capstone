package com.techelevator.dao;

import com.techelevator.model.User;

public interface LeagueDao {

    List<League> findAll();

    League getLeagueById(Long leagueId);

    League findByLeagueName(String leagueName);

    int findIdByLeagueName(String leagueName);

    League create(String leagueName, String leagueCourse);
}
