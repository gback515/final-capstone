<template>
  <div>
    <h1>Tee Time Details</h1>
    <div class="tee-time-details">
      <p>Tee Time Date:: {{ teeTime.tee_time_date }}</p>
      <p>Tee Time:: {{ teeTime.tee_time }}</p>
    </div>
    <router-link class="score-link" v-bind:to="{ name: 'add-score' }"
      >Input Scores
    </router-link>
  </div>
</template>

<script>
import TeeTimeService from "@/services/TeeTimeService.js";
// import ScoreService from "@/services/ScoreService.js";

export default {
  date() {
    return {
      teeTime: {
        teeTimeId: this.$route.params.teeTimeId,
        tee_time_date: "",
        tee_time: "",
      },
    };
  },
  created() {
    let teeTimePromise = TeeTimeService.getTeeTimes();
    teeTimePromise
      .then((response) => {
        if (response.status === 200) {
          this.teeTime = response.data;
        }
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          alert("Tee Time not available.");
          this.$router.push("/tee-time/:teeTimeId");
        }
      });
  },
};
</script>

<style>
</style>