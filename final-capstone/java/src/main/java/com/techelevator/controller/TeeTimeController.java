package com.techelevator.controller;

import com.techelevator.dao.TeeTimeDao;
import com.techelevator.model.TeeTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class TeeTimeController {
    private TeeTimeDao teeTimeDao;

    public TeeTimeController(TeeTimeDao teeTimeDao) {
        this.teeTimeDao = teeTimeDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/tee-time", method = RequestMethod.GET)
    public List<TeeTime> getAllTeeTimes() {
        return teeTimeDao.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/tee-time/user/{userId}", method = RequestMethod.GET)
    public List<TeeTime> getMyTeeTimes(@PathVariable("userId") long userId) {
        return teeTimeDao.findTeeTimeByUser(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/tee-time/league/{leagueId}", method = RequestMethod.GET)
    public List<TeeTime> getTeeTimesByLeagueId(@PathVariable("leagueId") Long leagueId) {
        return teeTimeDao.findTeeTimeByLeagueId(leagueId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create-tee-time/{leagueId}/{userId}", method = RequestMethod.POST)
    public long createTeeTime(@Valid @RequestBody TeeTime newTeeTime, @PathVariable long leagueId, @PathVariable long userId) {
        TeeTime teeTime = teeTimeDao.create(newTeeTime.getTeeTimeDate(), newTeeTime.getTime(), leagueId, userId);
        return teeTime.getTeeTimeId();
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/tee-time/{teeTimeId}/score/{score}", method = RequestMethod.POST)
    public void addScoreToTeeTime(@Valid @PathVariable("teeTimeId") long teeTimeId, @PathVariable("add-score") long score) {
        teeTimeDao.addScoreToTeeTime(teeTimeId, score);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/tee-time/id/{teeTimeId}", method = RequestMethod.GET)
    public TeeTime getTeeTimesById(@PathVariable("teeTimeId") long teeTimeId) {
        return teeTimeDao.findTeeTimeByTeeTimeId(teeTimeId);
    }
}
