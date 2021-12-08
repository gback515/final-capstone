<template>
  <form v-on:submit.prevent="submitLeague" class="league-form">
    <div class="form-group">
      <label for="name">League Name </label>
      <input
        id="name"
        type="text"
        class="form-control"
        v-model="league.name"
        autocomplete="off"
      />
    </div>
    <div class="form-group">
      <label for="league-course">Home Course </label>
      <select
        id="league-course"
        class="form-control"
        v-model="league.homeCourse"
      >
        <option value="Course List">Add Course List Here</option>
      </select>
    </div>
    <div class="form-group">
      <label for="day-of-week">Day of the Week to Play </label>
      <select
        id="day-of-week"
        class="form-control"
        name="day-of-week"
        v-model="league.dayOfWeekToPlay"
      >
        <option value="Sunday">Sunday</option>
        <option value="Monday">Monday</option>
        <option value="Tuesday">Tuesday</option>
        <option value="Wednesday">Wednesday</option>
        <option value="Thursday">Thursday</option>
        <option value="Friday">Friday</option>
        <option value="Saturday">Saturday</option>
      </select>
    </div>
    <button class="btn btn-submit">Submit</button>
    <button
      class="btn btn-cancel"
      v-on:click.prevent="cancelForm"
      type="cancel"
    >
      Cancel
    </button>
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
        leagueAdministrator: "",
        leagueCourse: "",
        dayOfWeekToPlay: "",
        members: [],
      },
    };
  },
  methods: {
    submitLeague() {
      const newLeague = {
        name: this.league.name,
        homeCourse: this.league.homeCourse,
        dayOfWeekToPlay: this.league.dayOfWeekToPlay,
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
      this.$router.push("/");
    },
  },
};
</script>

<style>
/* .league-form {
  padding: 10px;
  margin-top: 10px;
  margin-bottom: 10px;
}
.form-group {
  margin-bottom: 10px;
  margin-top: 10px;
} */
</style>