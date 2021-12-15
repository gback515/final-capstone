package com.techelevator.dao;

import com.techelevator.model.Score;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcScoreDao implements ScoreDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcScoreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Score> findAllScores() {
        List<Score> scores = new ArrayList<Score>();
        String sql = "SELECT user_id, tee_time_id, score FROM user_tee_time_score;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Score score =  mapRowToScore(results);
            scores.add(score);
        }
        return scores;
    }

    @Override
    public List<Score> findScoresByUserId(Long userId) {
        List<Score> scores = new ArrayList<Score>();
        String sql = "SELECT user_id, tee_time_id, score FROM user_tee_time_score WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Score score = mapRowToScore(results);
            scores.add(score);
        }
        return scores;
    }

    @Override
    public List<Score> findScoresByLeagueId(Long leagueId) {
        List<Score> scores = new ArrayList<Score>();
        String sql = "SELECT user_id, tee_time_id, score FROM user_tee_time_score WHERE league_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, leagueId);
        while (results.next()) {
            Score score = mapRowToScore(results);
            scores.add(score);
        }
        return scores;
    }

    @Override
    public List<Score> findScoresByTeeTimeId(Long teeTimeId) {
        List<Score> scores = new ArrayList<Score>();
        String sql = "SELECT user_id, tee_time_id, score FROM user_tee_time_score WHERE tee_time_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teeTimeId);
        while (results.next()) {
            Score score = mapRowToScore(results);
            scores.add(score);
        }
        return scores;
    }

    @Override
    public boolean create(Long userId, Long teeTimeId, Long score) {
        String sql = "INSERT INTO user_tee_time_score (user_id, tee_time_id, score) VALUES (?, ?, ?);";
        jdbcTemplate.update(sql, userId, teeTimeId, score);
        return true;
    }


    private Score mapRowToScore(SqlRowSet results) {
        Score score = new Score();
        score.setUserId(results.getLong("user_id"));
        score.setTeeTimeId(results.getLong("tee_time_id"));
        score.setScore(results.getLong("score"));
        return score;
    }




}
