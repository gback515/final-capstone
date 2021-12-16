package com.techelevator.dao;

import com.techelevator.model.League;
import com.techelevator.model.Score;

import java.util.List;

public interface ScoreDao {

    List<Score> findAllScores();

    List<Score> findScoresByUserId(Long userId);

    List<Score> findScoresByLeagueId(Long leagueId);

    List<Score> findScoresByTeeTimeId(Long teeTimeId);

    boolean create(Long userId, Long teeTimeId, Long Score);

    boolean addScore(Long userId, Long teeTimeId, Long score);

}
