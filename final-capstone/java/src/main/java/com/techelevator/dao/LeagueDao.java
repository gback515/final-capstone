package com.techelevator.dao;

import com.techelevator.model.League;

import java.util.List;

public interface LeagueDao {

    List<League> findAll();

    List<League> findLeaguesByUser(long userId);

    League getLeagueById(Long leagueId);

    League findByLeagueName(String leagueName);

    int findIdByLeagueName(String leagueName);

    League create(String leagueName, Long leagueAdmin, Long courseName, String dayOfWeek);

    boolean addUser(Long userId, Long leagueId);

    League getLeagueIdByName(String leagueName);
}
