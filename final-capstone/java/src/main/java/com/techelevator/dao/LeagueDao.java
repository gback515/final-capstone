package com.techelevator.dao;

import com.techelevator.model.League;
import com.techelevator.model.LeagueMemberDTO;
import com.techelevator.model.User;

import java.sql.SQLException;
import java.util.List;

public interface LeagueDao {

    List<League> findAll();

    List<League> findAllActive();

    List<League> findAllInactive();

    List<League> findLeaguesByUser(long userId);

    League getLeagueById(Long leagueId);

    League findByLeagueName(String leagueName);

    int findIdByLeagueName(String leagueName);

    League create(String leagueName, Long leagueAdmin, Long courseName, String dayOfWeek);

    boolean addUser(String userName, Long leagueId);

}
