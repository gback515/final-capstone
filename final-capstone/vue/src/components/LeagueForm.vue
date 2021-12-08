<template>
  <form v-on:submit.prevent="submitLeague" class="league-form">
      <div class="form-group">
          <label for="name">League Name </label>
          <input id="name" type="text" class="form-control" v-model="league.leagueName" autocomplete="off" />
      </div>
      <div class="form-group">
          <label for="league-course">Home Course </label>
          <select id="league-course" class="form-control" v-model="league.leagueCourse">
              <option value="0">Add Course List Here</option>
          </select>
      </div>
      <div class="form-group">
        <label for="day-of-week">Day of the Week to Play </label>
        <select id="day-of-week" class="form-control" name="day-of-week" v-model="league.dayOfWeek">
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
        leagueName: "",
        leagueAdmin: this.$store.state.user.id,
        leagueCourse: 0,
        dayOfWeek: "",
        //members: [],
      },
    };
  },
  methods: {
    submitLeague() {
      const newLeague = {
        league_name: this.league.leagueName,
        league_admin: this.league.leagueAdmin,
        league_course: parseInt(this.league.leagueCourse),
        day_of_week: this.league.dayOfWeek,
        //members: this.league.members,
      };
      if (this.leagueId === 0) {
        leagueService
          .addLeague(newLeague)
          .then((response) => {
            if (response.status === 201) {
              this.$router.push(`/`);
            }
          })
          //.catch((error) => {
           // this.handleErrorResponse(error, "adding");
          //});
      }
    },
    cancelForm() {
        this.$router.push('/')
    }
  },
};
</script>

<style>
.league-form {
  padding: 10px;
  margin-top: 10px;
  margin-bottom: 10px;
}
.form-group {
  margin-bottom: 10px;
  margin-top: 10px;
}
</style>