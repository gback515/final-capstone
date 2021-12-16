package com.techelevator.dao;

import com.techelevator.model.League;
import com.techelevator.model.TeeTime;

import java.util.List;

public interface TeeTimeDao {
    List<TeeTime> findAll();

    List<TeeTime> findTeeTimeByUser (long userId);

    TeeTime create(String teeTimeDate, String time, long leagueId);

    List<TeeTime> findTeeTimeByLeagueId(Long leagueId);

    TeeTime findTeeTimeByTeeTimeId(Long teeTimeId);

    boolean addScoreToTeeTime(Long teeTimeId, Long score);

}
