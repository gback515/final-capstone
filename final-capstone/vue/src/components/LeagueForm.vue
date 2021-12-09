<template>
  <form v-on:submit.prevent="submitLeague" class="league-form">
    <h1>Create New League</h1>
    <div class="form-group">
      <label for="name">League Name </label>
      <input
        id="name"
        type="text"
        class="form-control"
        v-model="league.leagueName"
        autocomplete="off"
      />
    </div>
    <div class="form-group">
      <label for="league-course">Home Course </label>
      <select
        id="league-course"
        class="form-control"
        v-model="league.leagueCourse"
      >
        <option value="0">Add Course List Here</option>
      </select>
    </div>
    <div class="form-group">
      <label for="day-of-week">Day of the Week to Play </label>
      <select
        id="day-of-week"
        class="form-control"
        name="day-of-week"
        v-model="league.dayOfWeek"
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
    <router-link class="link"
        style="color: blue"
        v-bind:to="{ name: 'add-course' }"
        >Add New Course</router-link>
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
        members: [],
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
        members: this.league.members,
      };
      if (this.leagueId === 0) {
        leagueService.addLeague(newLeague).then((response) => {
          if (response.status === 201) {
            this.$router.push(`/`);
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

<style scoped>

.league-form {
  margin-left: 23%;
  margin-top: 10%;
  background-color: rgba(250, 135, 123, 0.5);
  padding: 5px;
  border-radius: 30px;
  width: 30%;
  height: 54%;
  box-sizing: border-box;
}

.link {
  margin-left: 125px;
}

h1 {
  margin-left: 9%;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 5%;
  margin-left: 12%;
  padding-bottom: 5px;
}

input[type=text] {
  border: none;
  background-color: rgba(160, 141, 116, 0.733);
  border-radius: 3px;
}

input:hover {
  cursor: text;
}

select:hover {
  cursor: pointer;
}

select {
  background-color: rgba(160, 141, 116, 0.76);
  border: none;
  color: white;
  border-radius: 3px;
}

.btn {
  margin-left: 16%;
  background-color: rgba(160, 141, 116, 0.733);
  border: none;
  color: white;
  padding: 15px 20px;
  text-align: center;
  text-decoration: none;
  font-size: 16px;
  border-radius: 10px;
  font-weight: bold;
}

.btn:hover {
  background-color: tan;
  cursor: pointer;
}

</style>