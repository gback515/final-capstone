<template>
  <div class="course-form">
    <div v-for="course in courses" :key="course.courseId">
      <router-link
        class="course-link"
        style="text-decoration: none; color: inherit"
        v-bind:to="{
          name: 'course-details',
          params: { courseId: course.course_id },
        }"
        >{{ course.course_name }}</router-link
      >
    </div>
    <router-link
      class="links"
      style="text-decoration: none; color: inherit"
      v-bind:to="{ name: 'add-course' }"
      >Add Course
    </router-link>
  </div>
</template>

<script>
import CourseService from "@/services/CourseService.js";

export default {
  data() {
    return {
      courses: [],
      course: {
        courseName: "",
        coursePar: 0,
        coordinate: "",
        address: "",
        city: "",
        state: "",
        zip: "",
      },
    };
  },

  created() {
    CourseService.getCourses()
      .then((response) => {
        if (response.status === 200) {
          this.courses = response.data;
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
.course-form {
  display: flex;
  flex-direction: column;
  margin-left: 16%;
  margin-top: 5%;
  background-color: rgba(250, 135, 123, 0.5);
  padding: 5px;
  border-radius: 30px;
  width: 50%;
  height: 70%;
  box-sizing: border-box;
  resize: both;
}
</style>