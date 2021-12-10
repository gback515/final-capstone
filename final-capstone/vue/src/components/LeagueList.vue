<template>
  <div class="leagues">
    <h1 class="head">My Leagues</h1>
    <div v-for="league in leagues" :key="league.leagueId" v-on:click="viewLeague(league.leagueId)">
      <router-link class="league-link" style="text-decoration: none; color: inherit" v-bind:to="{ name: 'league-details' }">{{ league.league_name }}</router-link>
    </div>
  </div>
</template>

<script>
import LeagueService from "@/services/LeagueService.js";

export default {
  data() {
    return {
      isHidden: true,
      leagues: [],
      league: {
        leagueName: "",
        leagueAdmin: 0,
        leagueCourse: 0,
        dayOfWeek: "",
        members: [],
      },
    };
  },

  methods: {
    viewLeague(leagueId) {
      this.$route.push(`league/${leagueId}`);
    }
  },

  created() {
    LeagueService.getMyLeagues(this.$store.state.user.id)
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
  width: 100vw;
}

.head {
  margin: 5% 37%;
}

.league-link {
  margin: 5% 20% 5% 30%; 
  background-color: rgba(250, 135, 123, 0.5);
  text-align: center;
  font-size: 30px;
  padding: 5%;
  border-radius: 30px;
}

.leagues {
  width: 100%;
}

.btn {
  display: flex;
  justify-content: center;
  margin: 10% 33.5%;
  width: 20%;
  background-color: rgba(160, 141, 116, 0.733);
  border: none;
  color: white;
  padding: 10px 10px;
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