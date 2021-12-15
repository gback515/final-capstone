package com.techelevator.controller;


import com.techelevator.dao.ScoreDao;
import com.techelevator.model.Score;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ScoreController {

    private ScoreDao scoreDao;

    public ScoreController(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/score", method = RequestMethod.GET)
    public List<Score> getAllScores() {
        return scoreDao.findAllScores();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/score/{userId}", method = RequestMethod.GET)
    public List<Score> getScoresByUser(long userId) {
        return scoreDao.findScoresByUserId(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/score/{leagueId}", method = RequestMethod.GET)
    public List<Score> getScoresByLeague(long leagueId) {
        return scoreDao.findScoresByLeagueId(leagueId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/score/{teeTimeId}", method = RequestMethod.GET)
    public List<Score> getScoresByTeeTime(long teeTimeId) {
        return scoreDao.findScoresByTeeTimeId(teeTimeId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/teetimes/${teeTimeId}/add-score", method = RequestMethod.POST)
    public void addScoreToUser(@RequestBody Long userId, @PathVariable("teeTimeId") Long teeTimeId) {
        scoreDao.addScore(userId, teeTimeId);
    }





















}
