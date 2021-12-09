<template>
  <div class="useles">
    <form v-on:submit.prevent="submitCourse" class="course-form">
      <h1>Add New Course</h1>
      <div id="name" class="form-group">
        <label for="course-name">Course Name</label>
        <input
          id="couse-name"
          type="text"
          class="form-course-name"
          v-model="course.courseName"
        />
      </div>
      <div id="par" class="form-group">
        <label for="course-par">Course Par</label>
        <input id="course-par" type="number" v-model="course.coursePar" />
      </div>
      <div id="length" class="form-group">
        <label for="course-length">Course Length (yards)</label>
        <input type="number" id="course-length" v-model="course.courseLength" />
      </div>
      <div id="coor" class="form-group">
        <label for="course-coordinate">Course Coordinate</label>
        <input type="text" id="course-coordinate" v-model="course.courseCoordinate">
      </div>
      <div id="add" class="form-group">
        <label for="address">Street Address</label>
        <input type="text" id="address" v-model="course.courseAddress" />
      </div>
      <div id="cit" class="form-group">
        <label for="city">City</label>
        <input type="text" id="city" v-model="course.courseCity" />
      </div>
      <div id="sta" class="form-group">
        <label for="state">State</label>
        <input type="text" id="state" v-model="course.courseState" />
      </div>
      <div id="zi" class="form-group">
        <label for="zip">Zip Code</label>
        <input type="text" id="zip" v-model="course.courseZip" />
      </div>
      <button id="subm" class="btn" type="submit">Submit</button>
      <button id="can" class="btn" type="cancel" v-on:click="cancelForm">Cancel</button>
    </form>
  </div>
</template>

<script>
import CourseService from "../services/CourseService";
export default {
  name: "AddCourse",
  props: {
    courseId: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      course: {
        courseName: "",
        coursePar: 0,
        courseLength: 0,
        courseCoordinate: "",
        courseAddress: "",
        courseCity: "",
        courseState: "",
        courseZip: "",
      },
    };
  },

  methods: {
    submitCourse() {
      const newCourse = {
        course_name: this.course.courseName,
        course_par: this.course.coursePar,
        course_length: this.course.courseLength,
        coordinate: this.course.courseCoordinate,
        address: this.course.courseAddress,
        city: this.course.courseCity,
        state: this.course.courseState,
        zip: this.course.courseZip
      };
      if (this.courseId === 0) {
        CourseService.addCourse(newCourse).then((response) => {
          if (response.status === 201) {
            this.$router.push("/")
          }
        })
      }
    },
    cancelForm() {
      this.$router.push("/");
    }
  },
};
</script>

<style scoped>
h1 {
  font-family: cursive;
  grid-area: h1-ga;
  padding-top: 6%;
  padding-left: 18%;
}

#name {
  grid-area: name;
}

#par {
  grid-area: par;
}

#length {
  grid-area: length;
}

#coor {
  grid-area: coor;
}

#add {
  grid-area: addr;
}

#cit {
  grid-area: cit;
}

#sta {
  grid-area: sta;
}

#zi {
  grid-area: zi;
}

#sub {
  grid-area: subm;
}

#can {
  grid-area: can;
}

.useles {
  width: 100vh;
}
.course-form {
  display: grid;
  margin-left: 30%;
  margin-top: 20%;
  background-color: rgba(250, 135, 123, 0.5);
  padding: 5%;
  border-radius: 30px;
  width: 90%;
  height: 45%;
  grid-template-columns: 1fr 1fr;
  grid-template-areas: 
      "h1-ga    h1-ga"
      "name     par"
      "length   coor"
      "addr     cit"
      "sta      zi"
      "subm     can";
  justify-items: end;
}

h1 {
  margin-left: -20%;
  margin-top: -10%;
  margin-bottom: 30px;
  justify-self: center;
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

input[type="number"] {
  border: none;
  background-color: rgba(160, 141, 116, 0.733);
  border-radius: 3px;
  color: white;
}

input {
  margin-left: 6px;
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