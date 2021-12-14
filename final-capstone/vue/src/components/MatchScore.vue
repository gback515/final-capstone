<template>
  <div class="league-members">
    <h1>League Members:</h1>
    <div v-for="member in league" :key="member.id"></div>
    <p>Golfer Name: {{ user.username }}</p>
    <p>Score: {{ scores.score }}</p>
  </div>
</template>

<script>
import MatchService from "@/services/MatchService.js";

export default {
  data() {
    return {
      users: [],
      user: {
        userId: this.$route.params.userId,
        username: "",
      },
      scores: {
        course_id: "",
        league_id: "",
        score: "",
        date_played: "",
      },
    };
  },
  created() {
   MatchService.getScores(this.score.matchId)
      .then((response) => {
        if (response.status === 200) {
          this.user = response.data;
        }
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          alert("Golfer not in match.");
          this.$router.push("/league-list");
        }
      });


    // MatchService.getMatches(this.match.matchId)
    //   .then((response) => {
    //     if (response.status === 200) {
    //       this.user = response.data;
    //     }
    //   })
    //   .catch((error) => {
    //     if (error.response && error.response.status === 404) {
    //       alert("Golfer not in match.");
    //       this.$router.push("/league-list");
    //     }
    //   });
  },
};
</script>

<style>
.league-members {
  display: flex;
  flex-direction: column;
  flex-basis: 50%;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  background-color: rgba(250, 135, 123, 0.7);
  padding: 2px;
  border-radius: 30px;
  width: 50%;
  height: 50%;
  margin: 2% 25% 2% 25%;
}
</style>