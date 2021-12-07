package com.techelevator.dao;

import com.techelevator.model.League;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
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
        String sql = "SELECT * FROM leagues WHERE league_id = ?;";
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
        String sql = "SELECT * FROM leagues;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            League league = mapRowToLeague(results);
            leagues.add(league);
        }
        return leagues;
    }

    @Override
    public League findByLeagueName(String leagueName) throws LeageNameNotFoundException {
        for (League league : this.findAll()) {
            if (leage.getLeageName().toLowerCase().equals(leagueName.toLowerCase())) {
                return league;
            }
        }
        throw new LeageNameNotFoundException("League " + leagueName + " was not found.");
    }

    @Override
    public League create(String leagueName, String leagueCourse) {
        League league = new League(leagueName, leagueCourse);
        String sql = "INSERT INTO leagues (league_name, league_course) VALUES(?,?) RETURNING league_id;";
        int newLeagueId = jdbcTemplate.update(sql, leagueName, leagueCourse);
        league.setLeagueId(newLeagueId);
        return league;
    }

    private League mapRowToLeague(SqlRowSet results) {
        League league = new League();
        user.setId(results.getLong("user_id"));
        user.setLeagueName(results.getString("league_name"));
        user.setLeagueCourse(results.getString("league_course"));
        return league;
    }
}
