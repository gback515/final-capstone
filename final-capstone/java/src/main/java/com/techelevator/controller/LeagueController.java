package com.techelevator.controller;

import com.techelevator.dao.LeagueDao;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.techelevator.dao.UserDao;
import com.techelevator.model.AddUserToLeagueDTO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class LeagueController {

    private LeagueDao leagueDao;
    private UserDao userDao;

    public LeagueController(LeagueDao leagueDao, UserDao userDao) {
        this.leagueDao = leagueDao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/leagues", method = RequestMethod.GET)
    public List<League> getAllLeagues() {
        return leagueDao.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/leagues/active", method = RequestMethod.GET)
    public List<League> getAllActiveLeagues() {
        return leagueDao.findAllActive();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/leagues/inactive", method = RequestMethod.GET)
    public List<League> getAllInactiveLeagues() {
        return leagueDao.findAllInactive();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/leagues/{userId}", method = RequestMethod.GET)
    public List<League> getMyLeagues(@PathVariable("userId") long userId) {
        return leagueDao.findLeaguesByUser(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/league/{leagueId}", method = RequestMethod.GET)
    public League getLeagueFromLeagueId(@PathVariable("leagueId") long leagueId) {
        return leagueDao.getLeagueById(leagueId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create-league", method = RequestMethod.POST)
    public long createLeague(@Valid @RequestBody League newLeague) {
        League league = leagueDao.create(newLeague.getLeagueName(), newLeague.getLeagueAdmin(), newLeague.getLeagueCourse(), newLeague.getDayOfWeek());
        return league.getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/league/addUser", method = RequestMethod.POST)
    public long addUserToLeague(@Valid @RequestBody AddUserToLeagueDTO userLeague) {
        return leagueDao.addUser(userLeague.getUserName(), userLeague.getLeagueId());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/league/{leagueId}/get-members", method = RequestMethod.GET)
    public List<User> getMembersByLeague(@Valid @PathVariable("leagueId") long leagueId) {
        return userDao.getMembersByLeagueId(leagueId);
    }
}
