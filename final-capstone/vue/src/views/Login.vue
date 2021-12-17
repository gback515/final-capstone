<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="header">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <label for="username" class="u-label">Username</label>
      <input
        type="text"
        id="name"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="p-label">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link style="text-decoration: none; color: inherit" :to="{ name: 'register' }" id="register"
        >Need an account?</router-link
      >
      <button type="submit" id="submit">Sign in</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/"); // Change to user's list of leagues
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>
<style>
.header {
  grid-area: header-ga;
}

#name {
  grid-area: username-ga;
  background-color: white;
  border: black 2px solid;
}

.u-label {
  grid-area: u-label-ga;
}

.p-label {
  grid-area: p-label-ga;
}

#password {
  grid-area: password-ga;
  border-radius: 5px;
}

#register {
  background-color: rgb(255, 168, 168);
  border: solid black 1px;
  margin-top: 5%;
  padding: 1%;
  width: 50%;
  text-align: center;
  font-size: 16px;
  font-weight: bold;
  border-radius: 5px;
  justify-self: center;
  grid-area: create-ga;
}

#register:hover {
  background-color: rgb(250, 137, 137);
}

#submit {
  grid-area: submit-ga;
  width: 50%;
}
.form-signin {
  width: 100%;
  font-size: 1.5rem;
  display: grid;
  width: auto;
  margin-top: 30%;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "header-ga   header-ga"
    "u-label-ga  username-ga"
    "p-label-ga  password-ga"
    "submit-ga   submit-ga"
    "create-ga   create-ga";
}
</style>