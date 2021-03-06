package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcLeagueDao implements LeagueDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcLeagueDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByLeagueName (String leagueName) {
        return jdbcTemplate.queryForObject("SELECT league_id FROM leagues WHERE league_name = ?", int.class, leagueName);
    }

    @Override
    public League getLeagueById(Long leagueId) {
        String sql = "SELECT league_id, league_name, league_admin, league_course, day_of_week, active FROM leagues WHERE league_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, leagueId);
        if (results.next()) {
            return mapRowToLeague(results);
        } else {
            throw new RuntimeException("leagueId " + leagueId + " was not found.");
        }
    }

    @Override
    public List<League> findAll() {
        List<League> leagues = new ArrayList<League>();
        String sql = "SELECT league_id, league_name, league_admin, league_course, day_of_week, active FROM leagues;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            League league = mapRowToLeague(results);
            leagues.add(league);
        }
        return leagues;
    }

    @Override
    public List<League> findAllActive() {
        List<League> leagues = new ArrayList<League>();
        String sql = "SELECT league_id, league_name, league_admin, league_course, day_of_week, active FROM leagues WHERE active = true;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            League league = mapRowToLeague(results);
            leagues.add(league);
        }
        return leagues;
    }

    @Override
    public List<League> findAllInactive() {
        List<League> leagues = new ArrayList<League>();
        String sql = "SELECT league_id, league_name, league_admin, league_course, day_of_week, active FROM leagues WHERE active = false;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            League league = mapRowToLeague(results);
            leagues.add(league);
        }
        return leagues;
    }

    @Override
    public List<League> findLeaguesByUser(long userId) {
        List<League> leagues = new ArrayList<League>();
        String sql = "SELECT leagues.league_id, league_name, league_admin, league_course, day_of_week, active" +
                " FROM leagues JOIN user_league ON leagues.league_id = user_league.league_id WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            League league = mapRowToLeague(results);
            leagues.add(league);
        }
        return leagues;
    }

    @Override
    public League findByLeagueName(String leagueName) throws LeagueNameAlreadyExistsException {
        for (League league : this.findAll()) {
            if (league.getLeagueName().equalsIgnoreCase(leagueName)) {
                return league;
            }
        }
        throw new LeagueNameAlreadyExistsException();
    }

    @Override
    public League create(String leagueName, Long leagueAdmin, Long courseName, String dayOfWeek) {
        League league = new League(leagueName, leagueAdmin, courseName, dayOfWeek);
        String sql1 = "INSERT INTO leagues (league_name, league_admin, league_course, day_of_week) VALUES(?,?,?,?) RETURNING league_id;";
        Long newLeagueId = jdbcTemplate.queryForObject(sql1, Long.class, leagueName, leagueAdmin, courseName, dayOfWeek);
        league.setId(newLeagueId);
        return league;
    }

    @Override
    public long addUser(String userName, Long leagueId) {
        long userId = 0L;
        try {
            String sql1 = "SELECT user_id FROM users WHERE username = ?";
            userId = jdbcTemplate.queryForObject(sql1, Long.class, userName);
        } catch (DataAccessException e) {
            throw new UserDoesNotExistException();
        }
        String sql2 = "INSERT INTO user_league (user_id, league_id) VALUES (?, ?);";
        jdbcTemplate.update(sql2, userId, leagueId);
        return userId;
    }

    private League mapRowToLeague(SqlRowSet results) {
        League league = new League();
        league.setId(results.getLong("league_id"));
        league.setLeagueAdmin(results.getLong("league_admin"));
        league.setLeagueName(results.getString("league_name"));
        league.setLeagueCourse(results.getLong("league_course"));
        league.setDayOfWeek(results.getString("day_of_week"));
        league.isActive(results.getBoolean("active"));
        return league;
    }
}
