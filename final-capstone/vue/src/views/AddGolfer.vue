<template>
  <form v-on:submit.prevent="submitGolfer" class="golfer-form">
    <div class="form-golfer">
      <label for="username">Golfer Username</label>
      <input
        id="username"
        type="text"
        class="form-username"
        v-model="username"
      />
    </div>
    <div>
      <label for="userId">Golfer User ID</label>
      <input id="userId" type="text" class="form-userId" v-model="userId" />
    </div>
    <div class="form-golfer">
      <label for="leagueId">League ID</label>
      <input
        id="leagueId"
        type="text"
        class="form-leagueId"
        v-model="leagueId"
      />
    </div>
    <button type="submit">Submit</button>
  </form>
</template>

<script>
import leagueService from "@/services/LeagueService.js";
export default {
  name: "AddGolfer",
  data() {
    return {
      username: "",
      userId: 0,
      leagueId: 0,
    };
  },

  methods: {
    submitGolfer() {
      leagueService.addUserToLeague(
        parseInt(this.leagueId),
        parseInt(this.userId)
        
      );
    },
  },
};
</script>

<style>
</style>