<template>
  <div class="useles">
    <form v-on:submit.prevent="submitCourse" class="course-form">
      <h1>Add New Course</h1>
      <div class="form-group">
        <label for="course-name">Course Name</label>
        <input
          id="couse-name"
          type="text"
          class="form-course-name"
          v-model="courseName"
        />
      </div>
      <div class="form-group">
        <label for="course-coodinate">Corse Coordinate</label>
        <input
          id="couse-coodinate"
          type="text"
          class="form-course-coodinate"
          v-model="courseCoodinate"
        />
      </div>
      <button class="btn" type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import CourseService from "@/services/CourseService.js";
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
      CourseService.addCourse(this.addCourse);
      this.$router.push("/"); // Change to course list view
    },
  },
};
</script>

<style scoped>
h1 {
  margin-left: 20%;
  padding-top: 4%;
}

.useles {
  width: 100vh;
}
.course-form {
  margin-right: 5%;
  margin-left: 45%;
  margin-top: 25%;
  background-color: rgba(250, 135, 123, 0.5);
  padding: 5%;
  border-radius: 30px;
  width: 70%;
  height: 45%;
  box-sizing: border-box;
}

h1 {
  margin-left: 13%;
  margin-top: -10%;
  margin-bottom: 30px;
}

.form-group {
  margin-bottom: 5%;
  margin-top: 5%;
  display: flex;
  justify-content: space-around;
  justify-items: center;
  padding-bottom: 5px;
}

input[type="text"] {
  border: none;
  background-color: rgba(160, 141, 116, 0.733);
  border-radius: 3px;
}

input:hover {
  cursor: text;
}

select:hover {
  cursor: pointer;
}

select {
  background-color: rgba(160, 141, 116, 0.76);
  border: none;
  color: white;
  border-radius: 3px;
}

.btn {
  margin-top: 5%;
  margin-left: 30%;
  background-color: rgba(160, 141, 116, 0.733);
  border: none;
  color: white;
  padding: 15px 35px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  border-radius: 10px;
  font-weight: bold;
}

.btn:hover {
  background-color: tan;
  cursor: pointer;
}
</style>