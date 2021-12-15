<template>
  <form v-on:submit.prevent="submitScore" class="score-form">
    <h1>Input Final Round Scores</h1>
    <div id="name" class="form-scores">
      <label for="score-card">Score</label>
      <input
        id="score-card"
        type="number"
        class="form-score"
        v-model="score"
        autocomplete="off"
      />
    </div>
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
  </form>
</template>

<script>
import ScoreService from "../services/ScoreService";

export default {
  name: "AddScore",
  props: {
    scoreId: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      score: {
        user_id: "",
        tee_time_id: "",
        score: "",
      },
    };
  },

  methods: {
    submitScore() {
      const newScore = {
        scores: this.score.score,
        // score: parseInt(this.scores.score),
      };
      if (this.scoreId === 0) {
        ScoreService.addScore(newScore).then((response) => {
          if (response.status === 201) {
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