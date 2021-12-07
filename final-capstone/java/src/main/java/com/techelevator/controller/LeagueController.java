package com.techelevator.controller;

import com.techelevator.dao.LeagueDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class LeagueController {

    private LeagueDao leagueDao;

    public LeagueController(LeagueDao leagueDao) {
        this.leagueDao = leagueDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/league/create", method = RequestMethod.POST)
    public void createLeague(@Valid @RequestBody League newLeague) {
        try {
            League league = leagueDao.findByLeagueName(newLeague.getLeagueName());
            throw new LeagueAlreadyExistsException();
        } catch (LeagueNameNotFoundException e) {
            leagueDao.create(newLeague.getLeagueName(), newLeague.getLeagueCourse());
        }
    }
}
