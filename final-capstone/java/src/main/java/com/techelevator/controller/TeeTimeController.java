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
    @RequestMapping(value = "/tee-time/{userId}", method = RequestMethod.GET)
    public List<TeeTime> getMyTeeTimes(@PathVariable("userId") long userId) {
        return teeTimeDao.findTeeTimeByUser(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create-tee-time/{teeTimeId}", method = RequestMethod.POST)
    public long createTeeTime(@Valid @RequestBody TeeTime newTeeTime) {
        TeeTime teeTime = teeTimeDao.create(newTeeTime.getTeeTimeDate(), newTeeTime.getTime());
        return teeTime.getTeeTimeId();
    }
}
