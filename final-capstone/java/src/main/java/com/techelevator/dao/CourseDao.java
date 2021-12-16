package com.techelevator.dao;

import com.techelevator.model.Course;

import java.util.List;

public interface CourseDao {

    Long addCourse(Course course);

    List<Course> findAll();

    Course findCourseById(long courseId);

    List<Course> findCourseByPar(int coursePar);

    Course findCourseByName(String courseName);

    Course findCourseByCoordinate(String courseCoordinate);

    Course findCourseByAddress(String courseAddress);

    List<Course> findCourseByState(String courseState);

    List<Course> findCourseByCity(String courseCity);

    List<Course> findCourseByZip(String courseZip);

    Course findCourseByTeeTimeId(Long teeTimeId);
}
