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
  </div>
</template>

<script>
import LeagueService from "../services/LeagueService";
export default {
  name: "home",
  data() {
    return {
      leagues: [],
    };
  },
  created() {
    let leaguePromise = LeagueService.getMyLeagues(this.$store.state.user.id);
    leaguePromise.then((response) => {
      this.leagues = response.data;
    });
  },
};
</script>
<style>
</style>
