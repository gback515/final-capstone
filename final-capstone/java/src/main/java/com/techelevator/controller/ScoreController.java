package com.techelevator.controller;


import com.techelevator.dao.ScoreDao;
import com.techelevator.model.Score;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<Score> getScores() {
        return scoreDao.findAllScores();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/score/user/{userId}", method = RequestMethod.GET)
    public List<Score> getScoresByUser(long userId) {
        return scoreDao.findScoresByUserId(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/score/league/{leagueId}", method = RequestMethod.GET)
    public List<Score> getScoresByLeague(long leagueId) {
        return scoreDao.findScoresByLeagueId(leagueId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/score/{teeTimeId}", method = RequestMethod.GET)
    public List<Score> getScoresByTeeTime(long teeTimeId) {
        return scoreDao.findScoresByTeeTimeId(teeTimeId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/add-score/user/{userId}", method = RequestMethod.POST)
    public void addScoreToUser(@RequestBody Score newScore, @PathVariable Long teeTimeId) {
        scoreDao.addScore(newScore.getUserId(), teeTimeId, newScore.getScore());
    }
}

