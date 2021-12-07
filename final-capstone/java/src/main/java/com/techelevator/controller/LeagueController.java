package com.techelevator.controller;

import com.techelevator.dao.LeagueDao;
import com.techelevator.model.League;
import com.techelevator.model.LeagueMemberDTO;
import com.techelevator.model.LeagueNameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class LeagueController {

    private LeagueDao leagueDao;

    public LeagueController(LeagueDao leagueDao) {
        this.leagueDao = leagueDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/leagues", method = RequestMethod.GET)
    public List<League> getAllLeagues() {
        return leagueDao.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/leagues/user/{userId}", method = RequestMethod.GET)
    public List<League> getMyLeagues(@PathVariable("userId") long userId) {
        return leagueDao.findLeaguesByUser(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create-league", method = RequestMethod.POST)
    public void createLeague(@Valid @RequestBody League newLeague) {
        try {
            League league = leagueDao.create(newLeague.getLeagueName(), newLeague.getLeagueAdmin(), newLeague.getLeagueCourse(), newLeague.getDayOfWeek());
            throw new LeagueNameAlreadyExistsException();
        } catch (LeagueNameAlreadyExistsException e) {
            leagueDao.create(newLeague.getLeagueName(), newLeague.getLeagueAdmin(), newLeague.getLeagueCourse(), newLeague.getDayOfWeek());
        }
    }
}
