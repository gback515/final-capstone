<template>
  <form v-on:submit.prevent="submitScore" class="score-form">
    <h1>Input Final Round Scores</h1>
    <div id="name" class="form-score">
      <label for="score-card">Score</label>
      <input
        id="score-card"
        type="number"
        class="form-score"
        v-model="score.score"
        autocomplete="off"
      />

      <div class="buttons">
        <button class="btn btn-submit">Submit</button>
        <button
          class="btn btn-cancel"
          v-on:click.prevent="cancelForm"
          type="cancel"
        >
          Cancel
        </button>
      </div>
      <div class="form-scores">
        <h1 class="header">All Scores</h1>
        <li v-for="score in scores" v-bind:key="score">
          {{ score.tee_time_id }}
        </li>
      </div>
    </div>
  </form>
</template>

<script>
import ScoreService from "../services/ScoreService";
import TeeTimeService from "../services/TeeTimeService";

export default {
  name: "create-score",
  props: {
    teeTimeId: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      tee_times: [
        {
          tee_time_id: 0,
          tee_time_date: "",
          tee_time: "",
        },
      ],
      score: {
        userId: this.$store.state.user.id,
        tee_timeId: 0,
        score: "",
      },
    };
  },

  created() {
    let teeTimePromise = TeeTimeService.getTeeTimes();
    teeTimePromise.then((response) => {
      this.tee_times = response.data;
    });
  },

  methods: {
    submitScore() {
      const newScore = {
        user_id: this.score.userId,
        tee_time_id: this.score.teeTimeId,
        score: this.score.score,
        // this.score.score,
        // score: parseInt(this.scores.score),
      };
      if (this.teeTimeid === 0) {
        ScoreService.addScore(newScore).then((response) => {
          if (response.status === 201) {
            this.score.userId = response.data;
            this.$router.push("/");
          }
        });
      }
    },
    cancelForm() {
      this.$router.push("/");
    },
  },
};
</script>

<style>
/* h1 {
  font-family: cursive;
  grid-area: h1-ga;
  padding-top: 6%;
  padding-left: 18%;
} */
</style>




<!-- created() {
    let leaguePromise = ScoreService.getleagueId();
    leaguePromise.then((response) => {
      this.league = response.data;
    });
  },
  -->