<template>
  <div class="home">
    <h1>Home</h1>
    <h2>Your Recent Scores</h2>
    <ul>
      <li></li>
    </ul>
    <h2>Days You Play</h2>
    <ul>
      <li v-for="league in leagues" v-bind:key="league.id">
        {{ league.day_of_week }} : {{ league.league_name }}
      </li>
    </ul>
    <h2>Tee Times</h2>
    <ul>
      <li v-for="teeTime in teeTimes" v-bind:key="teeTime.tee_time_id">
        {{ teeTime.tee_time }} @ course on {{ teeTime.tee_time_date }}
      </li>
    </ul>
  </div>
</template>

<script>
import LeagueService from "../services/LeagueService";
import TeeTimeService from "../services/TeeTimeService";
export default {
  name: "home",
  data() {
    return {
      leagues: [],
      teeTimes: [],
    };
  },
  created() {
    let leaguePromise = LeagueService.getMyLeagues(this.$store.state.user.id);
    leaguePromise.then((response) => {
      this.leagues = response.data;
    });
    let teeTimePromise = TeeTimeService.getTeeTimes();
    teeTimePromise.then((response) => {
      this.teeTimes = response.data;
    });
  },
};
</script>
<style>
</style>
