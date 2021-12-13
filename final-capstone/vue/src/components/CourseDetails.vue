<template>
  <div>
    <p>Course Name: {{ course.course_name }}</p>
    <p>Course Par: {{ course.course_par }}</p>
    <p>Course Coordinate: {{ course.coordinate }}</p>
    <p>Course Address: {{ course.address }}</p>
    <p>Course City: {{ course.city }}</p>
    <p>Course State: {{ course.state }}</p>
    <p>Course Zip: {{ course.zip }}</p>
  </div>
</template>

<script>
import CourseService from "@/services/CourseService.js";

export default {
  data() {
    return {
      course: {
        courseId: this.$route.params.courseId,
        course_name: "",
        course_par: 0,
        coordinate: "",
        address: "",
        city: "",
        state: "",
        zip: "",
      },
    };
  },
  created() {
    CourseService.getCourseById(this.course.courseId)
      .then((response) => {
        if (response.status === 200) {
          this.course = response.data;
        }
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          alert("Course not available. This course may have been deleted.");
          this.$router.push("/course-list");
        }
      });
  },
};
</script>

<style>
</style>