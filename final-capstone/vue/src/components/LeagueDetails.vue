<template>
  <div>
    <div class="league-details">
      <p>League Name: {{ league.league_name }}</p>
      <p>League Admin: {{ league.league_admin }}</p>
      <p>Course: {{ league.league_course }}</p>
      <p>Day Of Week: {{ league.day_of_week }}</p>
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
  data() {
    return {
      league: {
        leagueId: this.$route.params.leagueId,
        league_name: "",
        league_admin: "",
        league_course: "",
        day_of_week: "",
      },
    };
  },
  created() {
    LeagueService.getLeague(this.league.leagueId)
      .then((response) => {
        if (response.status === 200) {
          this.league = response.data;
        }
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          alert("League not available. This league may have been deleted.");
          this.$router.push("/league-list");
        }
      });
  },

  isHidden: true,
  components: { AddGolfer },
  methods: {},
};
</script>

<style>
.league-details {
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