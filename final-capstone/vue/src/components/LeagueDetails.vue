<template>
  <div>
    <h1>{{ league.leagueName }}</h1>
    <h2>{{ league.leagueAdmin }}</h2>
    <h2>{{ league.leagueCourse }}</h2>
    <h2>{{ league.dayOfWeek }}</h2>
    <form action="addMembers">
        <input type="text" >
    </form>
    <h2>Members</h2>
  </div>
</template>

<script>
import LeagueService from "@/services/LeagueService.js";
export default {
  data() {
      return {
    league: {
        leagueName: "",
        leagueAdmin: 0,
        leagueCourse: 0,
        dayOfWeek: "",
        members: []
    }
      }
  },

  created() {
      LeagueService.getLeagueDetails(this.$route.params.leagueId)
      .then(response => {
          if (response.status === 200) {
              this.league = response.data;
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
</style>