<template>
<div class="details-main">
  <div class="course-details">
    <p>Course Name: {{ course.course_name }}</p>
    <p>Course Par: {{ course.course_par }}</p>
    <p>Course Length: {{ course.course_length }} yards</p>
    <p>Course Address: {{ course.address }}</p>
    <p>Course City: {{ course.city }}</p>
    <p>Course State: {{ course.state }}</p>
    <p>Course Zip: {{ course.zip }}</p>
    <GmapMap
      id="map"
      :center="center"
      :zoom="15"
      style="width: 80%; height: 350px;"
    />
  </div>
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
        course_length: 0,
        lat: 0,
        lng: 0,
        address: "",
        city: "",
        state: "",
        zip: "",
      },
      center: { lat: 0, lng: 0 },
    };
  },
  created() {
    CourseService.getCourseById(this.course.courseId)
      .then((response) => {
        if (response.status === 200) {
          this.course = response.data;
          this.center = { lat: this.course.lat, lng: this.course.lng };
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

<style scoped>

.details-main {
  width: 80%;
}
.course-details {
  display: flex;
  flex-direction: column;
  flex-basis: 50%;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  background-color: rgba(250, 135, 123, 0.7);
  padding: 2px;
  border-radius: 30px;
  height: 90%;
  margin: 2% 25% 2% 25%;
}

#map {
  align-self: center;
  border: Black 1px solid;
}

p {
  margin: 2%;
}
</style>