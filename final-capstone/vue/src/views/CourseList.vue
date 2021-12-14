<template>
  <div class="courses">
    <div class="course-form">
      <div class="course-list" v-for="course in courses" :key="course.courseId">
        <router-link
          tag="li"
          class="course-link"
          style="text-decoration: none; color: inherit; list-style-type: none"
          v-bind:to="{
            name: 'course-details',
            params: { courseId: course.course_id },
          }"
          >{{ course.course_name }}</router-link
        >
      </div>
      <nav>
        <router-link
          class="search-for-courses"
          style="text-decoration: none; color: inherit"
          v-bind:to="{ name: 'search-courses' }"
          >Search for Courses
        </router-link>
        <router-link
          class="add-course-link"
          style="text-decoration: none; color: inherit"
          v-bind:to="{ name: 'add-course' }"
          >Add Course
        </router-link>
      </nav>
    </div>
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
        lat: 0,
        lng: 0,
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
.courses {
  height: 100vh;
  width: 85vw;
}

.course-form {
  display: flex;
  align-items: center;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  flex-direction: column;
  margin: 5% 10% 10% 12%;
  background-color: rgba(250, 135, 123, 0.8);
  border-radius: 30px;
  width: 75%;
  height: 80%;
  box-sizing: border-box;
  resize: both;
}

.course-list {
  height: 7%;

}

nav {
  display: flex;
  width: 100%;
  justify-content: space-between;
}

.course-link {
  margin-bottom: 1%;
}

.course-link:hover {
  cursor: pointer;
  font-size: 17px;
}

.add-course-link {
  background-color: rgb(255, 168, 168);
  border: solid black 1px;
  margin: 5%;
  padding: 2%;
  height: 17%;
  width: 30%;
  text-align: center;
  border-radius: 5px;
  justify-self: flex-end;
}

.add-course-link:hover {
  background-color: rgb(250, 137, 137);
}

.search-for-courses {
  background-color: rgb(255, 168, 168);
  border: solid black 1px;
  margin: 5%;
  padding: 2%;
  height: 17%;
  width: 30%;
  text-align: center;
  border-radius: 5px;
  justify-self: flex-end;
}

.search-for-courses:hover {
  background-color: rgb(250, 137, 137);
}
</style>