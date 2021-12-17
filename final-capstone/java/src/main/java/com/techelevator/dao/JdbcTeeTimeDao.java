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
        String sql = "SELECT tee_time_id,  tee_time_date, tee_time FROM tee_times;";
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
        String sql = "SELECT tee_times.tee_time_id, tee_time_date, tee_time FROM tee_times " +
                "JOIN user_tee_time_score ON user_tee_time_score.tee_time_id = tee_times.tee_time_id " +
                "WHERE user_tee_time_score.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);
        while (results.next()) {
            TeeTime teeTime = mapRowToTeeTime(results);
            teeTimes.add(teeTime);
        }
        return teeTimes;
    }
    // SELECT tee_times.tee_time_id, tee_time_date, tee_time FROM tee_times  JOIN tee_time_league on tee_times.tee_time_id = tee_time_league.tee_time_id JOIN leagues ON tee_time_league.league_id = leagues.league_id WHERE leagues.league_id = ?;
    @Override
    public List<TeeTime>findTeeTimeByLeagueId(Long leagueId) {
        List<TeeTime> teeTimes = new ArrayList<>();
        String sql = "SELECT tee_times.tee_time_id, tee_time_date, tee_time FROM tee_times  " +
                "JOIN tee_time_league on tee_times.tee_time_id = tee_time_league.tee_time_id " +
                "JOIN leagues ON tee_time_league.league_id = leagues.league_id " +
                "WHERE leagues.league_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,leagueId);
        while (results.next()) {
            TeeTime teeTime = mapRowToTeeTime(results);
            teeTimes.add(teeTime);
        }
        return teeTimes;
    }

    @Override
    public TeeTime create(String teeTimeDate, String time, long leagueId, long userId) {
        TeeTime teeTime = new TeeTime(teeTimeDate, time);
        String sql = "INSERT INTO tee_times (tee_time_date, tee_time) VALUES(?,?) RETURNING tee_time_id";
        Long newTeeTimeId = jdbcTemplate.queryForObject(sql, Long.class, teeTimeDate, time);
        teeTime.setTeeTimeId(newTeeTimeId);
        String sql2 = "INSERT INTO tee_time_league (tee_time_id, league_id) VALUES (?,?)";
        jdbcTemplate.update(sql2, newTeeTimeId, leagueId);
        int defaultScore = 0;
        String sql3 = "INSERT INTO user_tee_time_score (user_id, tee_time_id, score)" +
                " VALUES (?,?,?);";
        jdbcTemplate.update(sql3, userId, newTeeTimeId, defaultScore);
        return teeTime;
    }

    @Override
    public boolean addScoreToTeeTime(Long teeTimeId, Long score) {
        String sql = "INSERT INTO user_tee_time_score (tee_time_id, score) VALUES (?,?)";
        jdbcTemplate.update(sql, teeTimeId, score);
        return true;
    }

    @Override
    public TeeTime findTeeTimeByTeeTimeId(Long teeTimeId) {
        String sql = "SELECT tee_time_id, tee_time, tee_time_date FROM tee_times " +
                "WHERE tee_time_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,teeTimeId);
        if (results.next()) {
            TeeTime teeTime = mapRowToTeeTime(results);
            return teeTime;
        }
        else return null; // change to teeTime doesn't exist exception
    }


    private TeeTime mapRowToTeeTime(SqlRowSet results) {
        TeeTime teeTime = new TeeTime();
        teeTime.setTeeTimeId(results.getLong("tee_time_id"));
        teeTime.setTeeTimeDate(results.getString("tee_time_date"));
        teeTime.setTime(results.getString("tee_time"));
        return teeTime;
    }
}
