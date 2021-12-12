package com.techelevator.dao;

import com.techelevator.model.League;
import com.techelevator.model.LeagueMemberDTO;

import java.util.List;

public interface LeagueDao {

    List<League> findAll();

    List<League> findAllActive();

    List<League> findAllInactive();

    List<League> findLeaguesByUser(long userId);

    League getLeagueById(Long leagueId);

    League findByLeagueName(String leagueName);

    int findIdByLeagueName(String leagueName);

    League create(String leagueName, Long leagueAdmin, Long courseName, String dayOfWeek, List<LeagueMemberDTO> leagueMembers);

    boolean addUser(Long userId, Long leagueId);

}
