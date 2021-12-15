<template>
  <div class="main">
    <div class="leagues">
    <h1 class="head">All Leagues</h1>
    <div v-for="league in leagues" :key="league.id">
      <router-link
        class="league-link"
        style="text-decoration: none; color: inherit"
        v-bind:to="{
          name: 'league-details',
          params: { leagueId: league.id },
        }"
        >{{ league.league_name }}</router-link
      >
    </div>
    <router-link
      class="new-league"
      style="text-decoration: none; color: inherit"
      v-bind:to="{ name: 'create-league' }"
      >Add New League</router-link
    >
    </div>
  </div>
</template>

<script>
import LeagueService from "@/services/LeagueService.js";

export default {
  data() {
    return {
      leagues: [],
      league: {
        leagueId: "",
        leagueName: "",
        leagueAdmin: 0,
        leagueCourse: 0,
        dayOfWeek: "",
        hover: false
      },
    };
  },

  methods: {
    viewLeague(leagueId) {
      this.$route.push(`league/${leagueId}`);
    },
  },

  created() {
    LeagueService.getAllLeagues()
      .then((response) => {
        if (response.status === 200) {
          this.leagues = response.data;
        }
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          alert(
            "League not available. This league may have been deleted or you have entered an invalid league ID."
          );
          this.$router.push("/");
        }
      });
  },
};
</script>

<style>
.main {
  width: 85vw;
  height: 100vh;
}

.leagues {
  display: flex;
  align-items: center;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  flex-direction: column;
  margin: 5% 10% 0% 12%;
  background-color: rgba(250, 135, 123, 0.8);
  padding: 5px;
  border-radius: 30px;
  width: 75%;
  height: 80%;
}

.head {
  display: flex;
  justify-self: start;
  align-self: center;
}

.league-link.router-link-active {
  color: white;
}

.league-link {
  display: flex;
  flex-direction: column;
  height: 5px;
  padding: 8px;
  margin: 0 0 30% 0;
  font-size: 25px;
  justify-content: center;
  align-items: center;
}

.new-league {
  margin: 28%;
  background-color: rgb(255, 168, 168);
  border: black 1px solid;
  text-align: center;
  font-size: 20px;
  padding: 2%;
  border-radius: 10px;
}
.new-league:hover {
  background-color: rgb(250, 137, 137);
}
</style>