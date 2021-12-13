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
        {{ league.league_name }} {{ league.day_of_week }}
      </li>
    </ul>
  </div>
</template>

<script>
import LeagueService from "../services/LeagueService";
export default {
  name: "home",
  created() {
    let leaguePromise = LeagueService.getAllLeagues();
    leaguePromise.then((response) => {
      this.league = response.data;
    });
  },
};
</script>
<style>
</style>
