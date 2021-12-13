<template>
  <div class="course-details">
    <p>Course Name:  {{ course.course_name }}</p>
    <p>Course Par:  {{ course.course_par }}</p>
    <p>Course Coordinate:  {{ course.coordinate }}</p>
    <p>Course Address:  {{ course.address }}</p>
    <p>Course City:  {{ course.city }}</p>
    <p>Course State:  {{ course.state }}</p>
    <p>Course Zip:  {{ course.zip }}</p>
    <GmapMap
      :center='center'
      :zoom='12'
      style='width:100%;  height: 400px;'
    />
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
      center: { lat: 39.316, lng: -84.425 }
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
  
  .course-details {
    display: flex;
    flex-direction: column;
    flex-basis: 50%;
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    background-color: rgba(250, 135, 123, 0.7);
    padding: 2px;
    border-radius: 30px;
    width: 50%;
    height: 90%;
    margin: 2% 25% 2% 25%
  }

</style>