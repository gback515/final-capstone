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
        id="username"
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
      <router-link :to="{ name: 'register' }" id="create"
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
            this.$router.push("/");
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

#username {
  grid-area: username-ga;
}

.u-label {
  grid-area: u-label-ga;
}

.p-label {
  grid-area: p-label-ga;
}

#password {
  grid-area: password-ga;
}

#create {
  grid-area: create-ga;
}

#submit {
  grid-area: submit-ga;
}
.form-signin {
  width: 50%;
  font-size: 1.5rem;
  justify-content: center;
  display: grid;
  width: auto;
  margin-top: 30%;
  display: inline;
  grid-template-areas:
    "header-ga   header-ga"
    "u-label-ga  username-ga"
    "p-label-ga  password-ga"
    "submit-ga   submit-ga"
    "create-ga   create-ga";
}
</style>