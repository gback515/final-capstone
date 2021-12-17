<template>
  <div class="home">
    <h1 id="header">Bogeys & Buddies</h1>
    <h2>Your Recent Scores</h2>
    <ul>
      <li v-for="score in scores" v-bind:key="score.user_id">
        {{ score.score }}
      </li>
    </ul>
    <h2>League Games</h2>
    <ul>
      <li v-for="league in leagues" v-bind:key="league.id">
        {{ league.day_of_week }} : {{ league.league_name }}
      </li>
    </ul>
    <h2>Tee Times</h2>
    <ul>
      <li v-for="teeTime in teeTimes" v-bind:key="teeTime.tee_time_id">
        {{ teeTime.tee_time }} on {{ teeTime.tee_time_date }}
      </li>
    </ul>
  </div>
</template>

<script>
import LeagueService from "../services/LeagueService";
import TeeTimeService from "../services/TeeTimeService";
import CourseService from "../services/CourseService";
import ScoreService from "../services/ScoreService.js";
export default {
  name: "home",
  data() {
    return {
      scores: [],
      leagues: [],
      teeTimes: [],
      courses: [],
      course: {
        courseId: 0,
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
    };
  },
  methods: {
    getCourse(teeTimeId) {
      let coursePromise = CourseService.getCourseByTeeTimeId(teeTimeId);
      coursePromise.then((response) => {
        if (response.status === 200) {
          this.course = response.data;
        }
      });
      return this.course.course_name;
    },
  },
  created() {
    let leaguePromise = LeagueService.getMyLeagues(this.$store.state.user.id);
    leaguePromise.then((response) => {
      if (response.status === 200) {
        this.leagues = response.data;
      }
    });
    let teeTimePromise = TeeTimeService.getTeeTimeByUser(
      this.$store.state.user.id
    );
    teeTimePromise.then((response) => {
      if (response.status === 200) {
        this.teeTimes = response.data;
      }
    });

    ScoreService.getScoreByUserId(this.$store.state.user.id).then(
      (response) => {
        if (response.status === 200) {
          this.scores = response.data;
        }
      }
    );
  },
};
</script>
<style>
.home {
  height: 50vh;
  margin-top: 5%;
  margin-left: 23%;
  padding: 5%;
  padding-top: 2%;
  border-radius: 8px;
  background-color: rgba(250, 135, 123, 0.8);
}
</style>
