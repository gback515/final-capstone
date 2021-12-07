<template>
  <form v-on:submit.prevent="submitLeague" class="league-form">
      <div class="form-group">
          <label for="name">League Name</label>
          <input id="name" type="text" class="form-control" v-model="league.name" autocomplete="off" />
      </div>
      <div class="form-group">
          <label for="home-course">Home Course</label>
          <select id="home-course" class="form-control" v-model="league.homeCourse">
              <option value="Course List">Add Course List Here</option>
          </select>
      </div>
      <div class="form-group">
          <label for="number-of-games">Number of Games</label>
          <input id="number-of-games" class="form-control" type="number" name="number-of-games" v-model="league.numberOfGames" />
      </div>
      <div class="form-group">
        <label for="number-of-teams">Number of Teams</label>
        <input id="number-of-teams" class="form-control" type="number" name="number-of-teams" v-model="league.numberOfTeams" />
      </div>
      <div class="form-group">
        <label for="players-per-team">Players Per Team</label>
        <input id="players-per-team" class="form-control" type="number" name="players-per-team" min="1" max="4" v-model="league.playersPerTeam" />
      </div>
      <div class="form-group">
        <label for="day-of-week">Day of the Week to Play on</label>
        <select id="day-of-week" class="form-control" name="day-of-week" v-model="league.dayOfWeekToPlay">
          <option value="Sunday">Sunday</option>
          <option value="Monday">Monday</option>
          <option value="Tuesday">Tuesday</option>
          <option value="Wednesday">Wednesday</option>
          <option value="Thursday">Thursday</option>
          <option value="Friday">Friday</option>
          <option value="Saturday">Saturday</option>
        </select>
      </div>
      <div class="form-group">
        <label for="length-of-league">Length of League in Weeks</label>
        <input id="length-of-league" class="form-control" type="number" name="length-of-league">
      </div>
      <button class="btn btn-submit">Submit</button>
      <button class="btn btn-cancel" v-on:click.prevent="cancelForm" type="cancel">Cancel</button>
  </form>
</template>

<script>
import leagueService from "../services/LeagueService";
export default {
  name: "create-league",
  props: {
    leagueId: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      league: {
        name: "",
        homeCourse: "",
        numberOfGames: "",
        numberOfTeams: "",
        playersPerTeam: "",
        dayOfWeekToPlay: "",
        lengthOfLeague: "",
        members: [""],
      },
    };
  },
  methods: {
    submitLeague() {
      const newLeague = {
        name: this.league.name,
        homeCourse: this.league.homeCourse,
        numberOfGames: this.league.numberOfGames,
        numberOfTeams: this.league.numberOfTeams,
        playersPerTeam: this.league.playersPerTeam,
        dayOfWeekToPlay: this.league.dayOfWeekToPlay,
        lengthOfLeague: this.league.lengthOfLeague,
        members: this.league.members,
      };
      if (this.leagueId === 0) {
        leagueService
          .addLeague(newLeague)
          .then((response) => {
            if (response.status === 201) {
              this.$router.push(`/league/${newLeague.leagueId}`);
            }
          })
          .catch((error) => {
            this.handleErrorResponse(error, "adding");
          });
      }
    },
    cancelForm() {
        this.$router.push('/')
    }
  },
};
</script>

<style>
</style>