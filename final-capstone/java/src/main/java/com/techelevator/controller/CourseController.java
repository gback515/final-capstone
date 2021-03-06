package com.techelevator.controller;

import com.techelevator.dao.CourseDao;
import com.techelevator.model.Course;
import com.techelevator.model.CourseNameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class CourseController {

    private CourseDao courseDao;

    public CourseController(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/add-course", method = RequestMethod.POST)
    public void createCourse(@Valid @RequestBody Course newCourse) {
        try {
            courseDao.addCourse(newCourse);
        } catch (CourseNameAlreadyExistsException e) {
            System.out.println("Course already exists");
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/course/id/{courseId}", method = RequestMethod.GET)
    public Course getCourseById(@Valid @PathVariable("courseId") long courseId) {
        return courseDao.findCourseById(courseId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/course/{courseName}", method = RequestMethod.GET)
    public Course getCourseByName(@Valid @PathVariable("courseName") String courseName) {
        return courseDao.findCourseByName(courseName);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/course/tee-time/{teeTimeId}", method = RequestMethod.GET)
    public Course getCourseByTeeTimeId(@Valid @PathVariable("teeTimeId") Long teeTimeId){
        return courseDao.findCourseByTeeTimeId(teeTimeId);
    }
}
