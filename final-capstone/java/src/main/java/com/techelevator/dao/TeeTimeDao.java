package com.techelevator.dao;

import com.techelevator.model.League;
import com.techelevator.model.TeeTime;

import java.util.List;

public interface TeeTimeDao {
    List<TeeTime> findAll();

    List<TeeTime> findTeeTimeByUser (long usserId);

    TeeTime create(long userId, long courseId, String teeTimeDate, String time);



}