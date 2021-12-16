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
        <button type="submit" class="btn btn-submit">Submit</button>
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
          User {{ score.user_id }} score: {{ score.teeTimeId }}
        </li>
      </div>
    </div>
  </form>
</template>

<script>
import ScoreService from "../services/ScoreService";

export default {
  props: ["teeTimeId"],
  data() {
    return {
      scores: [],
      score: {
        user_id: this.$store.state.user.id,
        tee_time_id: parseInt(this.teeTimeId),
        score: 0,
      },
    };
  },
  created() {
    ScoreService.getScoreByTeeTimeId(parseInt(this.teeTimeId)).then(
      (response) => {
        if (response.status === 200) {
          this.scores = response.data;
          // this.$router.push(`/tee-time/${this.tee_time_id}`);
        }
      }
    );
  },

  methods: {
    submitScore() {
      const newScore = {
        user_id: this.score.user_id,
        tee_time_id: this.score.tee_time_id,
        score: this.score.score,
        // this.score.score,
        // score: parseInt(this.scores.score),
      };

      ScoreService.addScore(newScore).then((response) => {
        if (response.status === 201) {
          this.$router.push(`/tee-time/${this.tee_time_id}`);
        }
      });
    },
    cancelForm() {
      this.$router.push(`/tee-time/${this.tee_time_id}`);
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



      userId: this.$route.params,
      tee_times: [
        {
          tee_time_id: 0,
          tee_time_date: "",
          tee_time: "",
        },
      ],


   name: "create-score",
  props: {
    teeTimeId: {
      type: Number,
      default: 0,
    },
  },
  -->