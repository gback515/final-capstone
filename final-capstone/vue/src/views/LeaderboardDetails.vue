<template>
  <div>This is where the leaderboard will go</div>
</template>

<script>
import leaderboardService from "@/services/LeaderboardService.js";

export default {
  data() {
    return {
      leagueId: this.$route.params.leagueId,
      leaderboard: {
        score: [],
      },
    };
  },
  created() {
    leaderboardService
      .getLeaderboardById(this.leagueId)
      .then((response) => {
        if (response.status === 200) {
          this.leaderboard = response.data;
          this.$router.push(`/league/this.${this.leagueId}`); // Go to league details page
        }
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          alert(
            "Leaderboard not available. This Leaderboard may have been deleted or you may have entered an invalid League ID."
          );
          this.$router.push(`/leagues/${this.$store.state.user.id}`); // Go to league list page
        }
      });
  },
};
</script>

<style>
</style>