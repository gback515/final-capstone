<template>
  <form ref="anyName" v-on:submit.prevent="submitGolfer" class="golfer-form">
    <div  id="user-not-found" v-if="userNotFound">
      <strong>Username Not Found!</strong>
    </div>
    <div id="user-repeated" v-if="userRepeated">
      <strong>User has already been added to league!</strong>
    </div>
    <div class="form-golfer">
      <label for="username">Golfer Username</label>
      <input
        id="username"
        type="text"
        class="form-username"
        v-on:click="userNotFound = false; userRepeated = false"
        v-model="userLeague.userName"
      />
    </div>
    <button class="btn" type="submit">Submit</button>
  </form>
</template>

<script>
import leagueService from "@/services/LeagueService.js";
export default {
  name: "AddGolfer",
  data() {
    return {
      userNotFound: false,
      userRepeated: false,
      userLeague: {
        userName: "",
        leagueId: this.$router.leagueId,
      },
      username: "",
      userId: 0,
      leagueId: 0,
    };
  },

  methods: {
    submitGolfer() {
      leagueService.addUserToLeague(
        this.userLeague
      ).then((response) => {
        if (response.status === 200) {
          this.$refs.anyName.reset();
        }
        }).catch((error) => {
          const response = error.response;

        if (response.status === 400) {
          this.userNotFound = true;
        }
        if (response.status === 500) {
          this.userRepeated = true;
        }
      })
    },
  },
};
</script>

<style>
/* .btn {
  display: flex;
  justify-content: center;
  margin: 3% 10% 2%;
  width: 30%;
  background-color: rgba(160, 141, 116, 0.8);
  border: black 1px solid;
  color: white;
  padding: 15px 20px;
  text-align: center;
  text-decoration: none;
  font-size: 16px;
  border-radius: 10px;
  font-weight: bold;
} */
</style>