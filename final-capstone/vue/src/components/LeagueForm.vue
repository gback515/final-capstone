<template>
  <form v-on:submit.prevent="submitLeague" class="league-form">
    <h1>Create New League</h1>
    <div id="input-box">
    <div class="form-group">
      <label for="name">League Name</label>
      <input
        id="league-name"
        type="text"
        class="form-control"
        v-model="league.leagueName"
        autocomplete="off"
      />
    </div>
    <div class="form-group">
      <label for="league-course">Home Course</label>
      <select
        id="league-course"
        class="form-control"
        v-model="league.leagueCourse"
      >
        <option
          v-for="course in courses"
          v-bind:key="course.course_id"
          v-bind:value="course.course_id"
        >
          {{ course.course_name }}
        </option>
      </select>
    </div>
    <div class="form-group">
      <label for="day-of-week">Day of the Week to Play</label>
      <select
        id="day-of-week"
        class="form-control"
        name="day-of-week"
        v-model="league.dayOfWeek"
      >
        <option value="Sunday">Sunday</option>
        <option value="Monday">Monday</option>
        <option value="Tuesday">Tuesday</option>
        <option value="Wednesday">Wednesday</option>
        <option value="Thursday">Thursday</option>
        <option value="Friday">Friday</option>
        <option value="Saturday">Saturday</option>
      </select>
    </div>
    </div>
    <div class="buttons">
      <button class="btn btn-submit">Submit</button>
      <button
        class="btn btn-cancel"
        v-on:click.prevent="cancelForm"
        type="cancel"
      >
        Cancel
      </button>
    </div>
    <router-link
      class="link"
      style="color: white; text-decoration: none"
      v-bind:to="{ name: 'add-course' }"
      >Add New Course</router-link
    >
  </form>
</template>

<script>
import CourseService from "../services/CourseService";
import leagueService from "../services/LeagueService";
export default {
  name: "create-league",
  props: {
    leagueId: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      userId: 0,
      userLeague: {
        userName: "",
        leagueId: 0,
      },
      courses: [
        {
          courseId: 0,
          courseName: "",
        },
      ],
      league: {
        leagueId: 0,
        leagueName: "",
        leagueAdmin: parseInt(this.$store.state.user.id),
        leagueCourse: 0,
        dayOfWeek: "",
      },
    };
  },
  created() {
    let coursePromise = CourseService.getCourses();
    coursePromise.then((response) => {
      this.courses = response.data;
    });
  },
  methods: {
    submitLeague() {
      const newLeague = {
        league_name: this.league.leagueName,
        league_admin: this.league.leagueAdmin,
        league_course: parseInt(this.league.leagueCourse),
        day_of_week: this.league.dayOfWeek,
      };
      leagueService
        .addLeague(newLeague)
        .then((response) => {
          if (response.status === 201) {
            this.league.leagueId = response.data;
            this.addGolfer();
            this.$router.push(`/league/${this.league.leagueId}`);
          }
        })
    },
    addGolfer() {
      this.userLeague.userName = this.$store.state.user.username;
      this.userLeague.leagueId = this.league.leagueId;
      leagueService.addUserToLeague(this.userLeague)
      .then((response) => {
        if (response.status === 201) {
          this.userId = response.data;
          location.reload();
        }
      })
    },
    cancelForm() {
      this.$router.push("/leagues");
    },
  },
};
</script>

<style scoped>
.league-form {
  display: flex;
  flex-direction: column;
  margin-left: 16%;
  margin-top: 5%;
  background-color: rgba(250, 135, 123, 0.8);
  padding: 5px;
  border-radius: 30px;
  width: 50%;
  height: 80%;
  box-sizing: border-box;
  resize: both;
}

#input-box {
  height: 100%;
  margin-bottom: 10%;
  margin-top: 0%;
}

.link {
  margin: 0% 32% 2%;
  background-color: rgba(160, 141, 116, 0.8);
  border: black 1px solid;
  padding: 15px 20px;
  text-align: center;
  font-size: 16px;
  border-radius: 10px;
  font-weight: bold;
  width: 30%;
}

.link:hover {
  background-color: rgb(250, 137, 137);
  cursor: pointer;
}

h1 {
  margin-left: 18%;
  margin-bottom: 0;
}

.form-group {
  display: flex;
  margin: 5% 10% 10% 5%;
  width: 70%;
  justify-content: space-around;
  justify-items: center;
}

input[type="text"] {
  width: 100px;
  transition: width 0.5s;
  border: none;
  background-color: rgba(160, 141, 116, 0.8);
  border-radius: 3px;
}

input[type="text"]:focus {
  width: 200px;
  border: none;
  background-color: rgba(160, 141, 116, 0.8);
  border-radius: 3px;
}

input:hover {
  cursor: text;
}

select:hover {
  cursor: pointer;
}

select {
  background-color: rgba(160, 141, 116, 0.8);
  border: none;
  color: white;
  border-radius: 3px;
  width: 200px;
}

.buttons {
  display: flex;
  flex-wrap: wrap;
}

.btn {
  display: flex;
  justify-content: center;
  margin: 3% 10% 2%;
  width: 30%;
  background-color: rgba(160, 141, 116, 0.8);
  border: black 1px solid;
  color: white;
  padding: 15px 20px;
  text-align: center;
  text-decoration: none;
  font-size: 16px;
  border-radius: 10px;
  font-weight: bold;
}

.btn:hover {
  background-color: rgb(250, 137, 137);
  cursor: pointer;
}
</style>