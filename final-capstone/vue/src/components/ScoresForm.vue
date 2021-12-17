<template>
  <div class="score">
    <h2>Input Final Round Scores</h2>
    <div class="score">
      <form v-on:submit.prevent="submitScore" class="score">
        <div class="score">
          <label for="score-card">Score: </label>
          <input
            id="score-card"
            type="number"
            v-model="score.score"
            autocomplete="off"
          />
        </div>
        <div class="score">
          <div>
            <button type="submit" class="btn">Submit</button>
          </div>
          <div>
            <button class="btn" v-on:click.prevent="cancelForm">Cancel</button>
          </div>
        </div>
      </form>
    </div>
    <div id="view-scores">
      <h2>All Scores</h2>
      <li v-for="s in scores" v-bind:key="s.user_id">
        User {{ s.user_id }} score: {{ s.score }}
      </li>
    </div>
  </div>
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
    ScoreService.getScoreByTeeTimeId(this.score.tee_time_id).then(
      (response) => {
        if (response.status === 200) {
          this.scores = response.data;
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
      };
      ScoreService.addScore(newScore).then((response) => {
        if (response.status === 201) {
          location.reload();
        }
      });
    },
    cancelForm() {
      location.reload();
    },
  },
};
</script>

<style>
.score {
  display: flex;
  flex-direction: column;
}
</style>