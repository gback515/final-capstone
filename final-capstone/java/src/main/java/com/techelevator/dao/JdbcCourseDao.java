package com.techelevator.dao;

import com.techelevator.model.Course;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JdbcCourseDao implements CourseDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long addCourse(Course course) {
        String courseName = course.getCourseName();
        int coursePar = course.getCoursePar();
        int courseLength = course.getCourseLength();
        double courseLat = course.getCourseLat();
        double courseLng = course.getCourseLng();
        String courseAddress = course.getCourseAddress();
        String courseCity = course.getCourseCity();
        String courseState = course.getCourseState();
        String courseZip = course.getCourseZip();
        String sql = "INSERT INTO courses (course_name, course_par, course_length, lat, lng, address, city, state, zip)" +
                " VALUES (?,?,?,?,?,?,?,?,?) RETURNING course_id;";
        return jdbcTemplate.queryForObject(sql, Long.class, courseName, coursePar, courseLength, courseLat, courseLng, courseAddress, courseCity, courseState, courseZip);
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<Course>();
        String sql = "SELECT course_id, course_name, course_par, course_length, lat, lng, address, city, state, zip FROM courses;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Course course = mapRowToCourse(results);
            courses.add(course);
        }
        return courses;
    }

    @Override
    public Course findCourseById(long courseId) {
        String sql = "SELECT course_id, course_name, course_par, course_length, lat, lng, address, city, state, zip FROM courses WHERE course_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseId);
        if (results.next()) {
            return mapRowToCourse(results);
        } else {
            throw new RuntimeException("courseId " + courseId + " was not found.");
        }
    }

    @Override
    public List<Course> findCourseByPar(int coursePar) {
        List<Course> courses = new ArrayList<Course>();
        String sql = "SELECT course_id, course_name, course_length, lat, lng, address, city, state, zip FROM courses WHERE course_par = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, coursePar);
        while (results.next()) {
            Course course = mapRowToCourse(results);
            courses.add(course);
        }
        return courses;
    }

    @Override
    public Course findCourseByName(String courseName) {
        String sql = "SELECT course_id, course_name, course_length, lat, lng, address, city, state, zip FROM courses WHERE course_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseName);
        if (results.next()) {
            return mapRowToCourse(results);
        } else {
            throw new RuntimeException("courseName " + courseName + " was not found.");
        }
    }

    @Override
    public Course findCourseByCoordinate(String courseCoordinate) {
        String sql = "SELECT course_id, course_name, course_length, lat, lng, address, city, state, zip FROM courses WHERE coordinate = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseCoordinate);
        if (results.next()) {
            return mapRowToCourse(results);
        } else {
            throw new RuntimeException("courseCoordinate " + courseCoordinate + " was not found.");
        }
    }

    @Override
    public Course findCourseByAddress(String courseAddress) {
        String sql = "SELECT course_id, course_name, course_length, lat, lng, address, city, state, zip FROM courses WHERE address = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseAddress);
        if (results.next()) {
            return mapRowToCourse(results);
        } else {
            throw new RuntimeException("courseAddress " + courseAddress + " was not found.");
        }
    }

    @Override
    public List<Course> findCourseByState(String courseState) {
        List<Course> courses = new ArrayList<Course>();
        String sql = "SELECT course_id, course_name, course_length, lat, lng, address, city, state, zip FROM courses WHERE state = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseState);
        while (results.next()) {
            Course course = mapRowToCourse(results);
            courses.add(course);
        }
        return courses;
    }

    @Override
    public List<Course> findCourseByCity(String courseCity) {
        List<Course> courses = new ArrayList<Course>();
        String sql = "SELECT course_id, course_name, course_length, lat, lng, address, city, state, zip FROM courses WHERE city = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseCity);
        while (results.next()) {
            Course course = mapRowToCourse(results);
            courses.add(course);
        }
        return courses;
    }

    @Override
    public List<Course> findCourseByZip(String courseZip) {
        List<Course> courses = new ArrayList<Course>();
        String sql = "SELECT course_id, course_name, course_length, lat, lng, address, city, state, zip FROM courses WHERE zip = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseZip);
        while (results.next()) {
            Course course = mapRowToCourse(results);
            courses.add(course);
        }
        return courses;
    }

    private Course mapRowToCourse(SqlRowSet results) {
        Course course = new Course();
        course.setCourseId(results.getLong("course_id"));
        course.setCourseName(results.getString("course_name"));
        course.setCoursePar(results.getInt("course_par"));
        course.setCourseLength(results.getInt("course_length"));
        String courseLatStr = results.getString("lat");
        assert courseLatStr != null;
        double courseLat = Double.parseDouble(courseLatStr);
        course.setCourseLat(courseLat);
        String courseLngStr = results.getString("lng");
        assert courseLngStr != null;
        double courseLng = Double.parseDouble(courseLngStr);
        course.setCourseLng(courseLng);
        course.setCourseAddress(results.getString("address"));
        course.setCourseCity(results.getString("city"));
        course.setCourseState(results.getString("state"));
        course.setCourseZip(results.getString("zip"));
        return course;
    }
}
