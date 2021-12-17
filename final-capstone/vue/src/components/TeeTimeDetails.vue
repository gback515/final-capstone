<template>
  <div>
    <div>
      <h1>Tee Time Details</h1>
      <p>Tee Time Date: {{ teeTime.tee_time_date }}</p>
      <p>Tee Time: {{ teeTime.tee_time }}</p>
    </div>
    <div>
      <scores-form v-bind:teeTimeId="teeTimeId" />
    </div>
  </div>
</template>

<script>
import TeeTimeService from "@/services/TeeTimeService.js";
import ScoresForm from "./ScoresForm.vue";

export default {
  components: { ScoresForm },
  data() {
    return {
      times: [],
      teeTimeId: parseInt(this.$route.params.teeTimeId),
      teeTime: {
        tee_time_date: "",
        tee_time: "",
      },
    };
  },
  created() {
    let teeTimePromise = TeeTimeService.getTeeTimeById(this.teeTimeId);
    teeTimePromise
      .then((response) => {
        if (response.status === 200) {
          this.teeTime = response.data;
        }
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          console.log("Tee Time not available.");
          this.$router.push("/tee-time/:teeTimeId");
        }
      });
  },
};
</script>

<style>
</style>