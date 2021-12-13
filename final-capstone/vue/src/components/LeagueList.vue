<template>
  <div class="leagues">
    <h1 class="head">My Leagues</h1>
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
      },
    };
  },

  methods: {
    viewLeague(leagueId) {
      this.$route.push(`league/${leagueId}`);
    },
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
  background-color: rgba(250, 135, 123, 0.7);
  text-align: center;
  font-size: 30px;
  padding: 40%;
  border-radius: 30px;
}

.new-league {
  margin: 20% 30% 5% 30%;
  background-color: rgba(250, 135, 123, 0.5);
  text-align: center;
  font-size: 30px;
  padding: 3%;
  border-radius: 30px;
}

.leagues {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex-basis: 80%;
  width: 100%;
}
</style>