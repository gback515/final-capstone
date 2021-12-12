<template>
  <div>
    <div>
      
    </div>
    <div>
      <button class="btn" v-on:click="isHidden = !isHidden">
        Add New Golfer
      </button>
      <add-golfer v-if="!isHidden" />
    </div>
    <ul>
      <li v-for="user in members" :key="user.userId">{{ user.username }}</li>
    </ul>
  </div>
</template>

<script>
import LeagueService from "@/services/LeagueService.js";
import AddGolfer from "../views/AddGolfer.vue";
export default {
  isHidden: true,
  components: { AddGolfer },
  methods: {},
  created() {
    LeagueService.getLeague(this.league.leagueId);
  },
};
</script>

<style>
</style>