<template>
  <div class="container">
    <div class="league-details">
      <p>League Name: {{ league.league_name }}</p>
      <p>League Admin: {{ league.league_admin }}</p>
      <p>Course: {{ league.league_course }}</p>
      <p>Day Of Week: {{ league.day_of_week }}</p>
    </div>
    <div class="member-list">
      <h3>Members: </h3>
      <ul>
        <li id="members" v-for="member in members" :key="member.id">{{ member.username }}</li>
      </ul>
    </div>
    <div class="tee-time-list">
      <h3>Tee times:</h3>
      <div v-for="teeTime in teeTimes" v-bind:key="teeTime.tee_time_id">
        <router-link
          v-bind:to="{
            name: 'teeTimeDetails',
            params: { teeTimeId: teeTime.tee_time_id },
          }"
        >
          {{ teeTime.tee_time }} on {{ teeTime.tee_time_date }}
        </router-link>
      </div>
    </div>

    <div class="create">
      <h3>Create Tee Time:</h3>
      <router-link
        class="score-link"
        v-bind:to="{ path: `/tee-time/league/${league.id}` }"
        >Create Tee Time
      </router-link>
      <h3>Add Golfer:</h3>
      <add-golfer />
    </div>
  </div>
</template>

<script>
import LeagueService from "@/services/LeagueService.js";
import AddGolfer from "../views/AddGolfer.vue";
import TeeTimeService from "../services/TeeTimeService";
export default {
  data() {
    return {
      members: [],
      teeTimes: [],
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
    TeeTimeService.getTeeTimeByLeague(this.league.leagueId).then((response) => {
      this.teeTimes = response.data;
    });
    LeagueService.getMembersByLeague(this.league.leagueId)
    .then((response) => {
      if (response.status === 200) {
        this.members = response.data;
      }
    })
  },

  isHidden: true,
  components: { AddGolfer },
  methods: {},
};
</script>

<style>
.league-details {
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  background-color: rgba(250, 135, 123, 0.7);
  padding: 10%;
  border-radius: 10px;
  margin-top: 5%;
  grid-area: detail-ga;
}

.tee-time-list {
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  background-color: rgba(250, 135, 123, 0.7);
  padding: 10%;
  border-radius: 10px;
  margin-top: 5%;
  grid-area: list-ga;
}

.create {
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  background-color: rgba(250, 135, 123, 0.7);
  padding: 10%;
  border-radius: 10px;
  margin-top: 5%;
  margin-bottom: 40%;
  grid-area: add-ga;
}

.member-list {
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  background-color: rgba(250, 135, 123, 0.7);
  padding: 10%;
  border-radius: 10px;
  margin-top: 5%;
  margin-bottom: 40%;
  grid-area: member-ga;
}

input[type="text"] {
  width: 120px;
  height: 25px;
  transition: width 0.5s;
  border: none;
  background-color: rgba(160, 141, 116, 0.8);
  border-radius: 3px;
}

button {
  display: flex;
  justify-content: center;
  margin: 5% 25% 5%;
  width: 30%;
  background-color: rgb(255, 168, 168);
  border: black 1px solid;
  color: black;
  padding: 5px 30px;
  text-align: center;
  text-decoration: none;
  font-size: 15px;
  border-radius: 5px;
  font-weight: bold;
}

button:hover {
  background-color: rgb(250, 137, 137);
  cursor: pointer;
}

.container {
  width: 85vw;
  height: 100vh;
  margin: 0% 10% 10% 10%;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  row-gap: 20px;
  column-gap: 20px;
  grid-template-areas:
    "detail-ga list-ga"
    "member-ga add-ga";
}
</style>