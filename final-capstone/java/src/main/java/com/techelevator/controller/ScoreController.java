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
    @RequestMapping(value = "/scores", method = RequestMethod.GET)
    public List<Score> getAllScores() {
        return scoreDao.findAllScores();
    }




}
