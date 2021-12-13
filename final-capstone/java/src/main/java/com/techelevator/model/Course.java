package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {
    @JsonProperty("course_id")
    private Long courseId;
    @JsonProperty("course_name")
    private String courseName; // Consider unique restraint in database
    @JsonProperty("course_par")
    private int coursePar;
    @JsonProperty("course_length")
    private int courseLength; // In yards, change to array for scratch, senior, women
    @JsonProperty("lat")
    private double courseLat;
    @JsonProperty("lng")
    private double courseLng;
    @JsonProperty("address")
    private String courseAddress; // Consider unique restraint in database
    @JsonProperty("city")
    private String courseCity;
    @JsonProperty("state")
    private String courseState; // Consider state restriction e.g., full state name vs abbreviation
    @JsonProperty("zip")
    private String courseZip;

    public Course() {}

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public Course(String courseName, int coursePar) {
        this.courseName = courseName;
        this.coursePar = coursePar;
    }

    public Course (String courseName, int coursePar, double courseLat, double courseLng) {
        this.courseName = courseName;
        this.coursePar = coursePar;
        this.courseLat = courseLat;
        this.courseLng = courseLng;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCoursePar() {
        return coursePar;
    }

    public void setCoursePar(int coursePar) {
        this.coursePar = coursePar;
    }

    public int getCourseLength() {
        return courseLength;
    }

    public void setCourseLength(int courseLength) {
        this.courseLength = courseLength;
    }

    public double getCourseLat() {
        return courseLat;
    }

    public void setCourseLat(double courseLat) {
        this.courseLat = courseLat;
    }

    public double getCourseLng() {
        return courseLng;
    }

    public void setCourseLng(double courseLng) {
        this.courseLng = courseLng;
    }

    public String getCourseAddress() {
        return courseAddress;
    }

    public void setCourseAddress(String courseAddress) {
        this.courseAddress = courseAddress;
    }

    public String getCourseCity() {
        return courseCity;
    }

    public void setCourseCity(String courseCity) {
        this.courseCity = courseCity;
    }

    public String getCourseState() {
        return courseState;
    }

    public void setCourseState(String courseState) {
        this.courseState = courseState;
    }

    public String getCourseZip() {
        return courseZip;
    }

    public void setCourseZip(String courseZip) {
        this.courseZip = courseZip;
    }
}
