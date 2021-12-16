<template>
  <div>
    <form v-on:submit.prevent="submitTeeTime">
      <label for="time">Time</label>
      <input id="time" type="text" v-model="teeTime.tee_time" />
      <label for="date">Date</label>
      <input id="date" type="text" v-model="teeTime.tee_time_date" />
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import TeeTimeService from "../services/TeeTimeService";
export default {
  data() {
    return {
      leagueId: parseInt(this.$route.params.leagueId),
      teeTime: {
        tee_time_id: 0,
        tee_time_date: "",
        tee_time: "",
      },
    };
  },
  methods: {
    submitTeeTime() {
      const newTeeTime = {
        tee_time_date: this.teeTime.tee_time_date,
        tee_time: this.teeTime.tee_time,
      };
      if (this.teeTime.tee_time_id === 0) {
        TeeTimeService.addTeeTime(
          this.leagueId,
          this.$store.state.user.id,
          newTeeTime
        ).then((response) => {
          if (response.status === 201) {
            this.teeTime.tee_time_id = parseInt(response.data);
            this.$router.push(`/league/${this.leagueId}`);
          }
        });
      }
    },
  },
};
</script>

<style>
</style>