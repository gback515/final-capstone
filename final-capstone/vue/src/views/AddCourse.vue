<template>
  <div>
    <form v-on:submit.prevent="submitCourse" class="course-form">
      <div id="name">
        <label for="course-name">Course Name</label>
        <input
          id="couse-name"
          type="text"
          class="form-course-name"
          v-model="courseName"
        />
      </div>
      <div id="coodinate">
        <label for="course-coodinate">Corse Coordinate</label>
        <input
          id="couse-coodinate"
          type="text"
          class="form-course-coodinate"
          v-model="courseCoodinate"
        />
      </div>
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import CourseScervice from "@/services/CourseService.js";
export default {
  name: "AddCourse",
  data() {
    return {
      courseName: "",
      courseCoordinate: "",
    };
  },

  methods: {
    submitCourse() {
      CourseScervice.addCourse(this.addCourse);
    },
  },
};
</script>

<style>
</style>