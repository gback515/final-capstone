package com.techelevator.dao;

import com.techelevator.model.TeeTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class JdbcTeeTimeDao implements TeeTimeDao {

    private JdbcTemplate jdbcTemplate;
    public JdbcTeeTimeDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TeeTime> findAll() {
        List<TeeTime> teeTimes = new ArrayList<>();
        String sql = "SELECT tee_time_id, user_id, league_id, tee_time_date, tee_time FROM tee_times;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            TeeTime teeTime = mapRowToTeeTime(results);
            teeTimes.add(teeTime);
        }
        return teeTimes;
    }

    @Override
    public List<TeeTime> findTeeTimeByUser(long userId) {
        List<TeeTime> teeTimes = new ArrayList<>();
        String sql = "SELECT tee_time_id, user_id, league_id, tee_time_date, tee_time FROM tee_times WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);
        while (results.next()) {
            TeeTime teeTime = mapRowToTeeTime(results);
            teeTimes.add(teeTime);
        }
        return teeTimes;
    }

    @Override
    public TeeTime create(long userId, long courseId, String teeTimeDate, String time) {
        TeeTime teeTime = new TeeTime(userId, courseId, teeTimeDate, time);
        String sql = "INSERT INTO tee_times (tee_time_id, user_id, league_id, tee_time_date, tee_time) VALUES(?,?,?,?,?) RETURNING tee_time_id";
        Long newTeeTimeId = jdbcTemplate.queryForObject(sql, Long.class, userId, courseId, teeTimeDate, time);
        teeTime.setTeeTimeId(newTeeTimeId);
        return teeTime;
    }


    private TeeTime mapRowToTeeTime(SqlRowSet results) {
        TeeTime teeTime = new TeeTime();
        teeTime.setTeeTimeId(results.getLong("tee_time_id"));
        teeTime.setUserId(results.getLong("user_id"));
        teeTime.setLeagueId(results.getLong("league_id"));
        teeTime.setTeeTimeDate(results.getString("tee_time_date"));
        teeTime.setTime(results.getString("tee_time"));
        return teeTime;
    }
}
