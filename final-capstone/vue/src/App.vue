<template>
  <div id="app">
    <div id="nav">
      <img id="golf-ball" src="@/assets/golf-ball-logo.png" />
      <router-link
        class="links"
        style="text-decoration: none; color: inherit"
        v-bind:to="{ name: 'home' }"
        >Home</router-link
      >
      <router-link
        class="links"
        style="text-decoration: none; color: inherit"
        v-bind:to="{ path: `/leagues/${this.$store.state.user.id}` }"
        >Leagues</router-link
      >
      <router-link
        class="links"
        style="text-decoration: none; color: inherit"
        v-bind:to="{ name: 'add-course' }"
        >Add Course</router-link
      >
      <router-link
        class="links"
        style="text-decoration: none; color: inherit"
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
#app {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
    "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji",
    "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
  background: url(assets/golf-background.jpg) left / cover no-repeat;
  width: auto;
  display: flex;
  justify-content: flex-start;
  height: 100vh;
  overflow: hidden;
  margin: 0px;
}
body {
  margin: 0px;
}
#nav {
  display: flex;
  flex-direction: column;
  flex-basis: 17%;
  background-color: rgba(160, 141, 116, 0.849);
  align-items: center;
  padding-top: 10px;
}
#golf-ball {
  max-width: 100%;
  height: auto;
}
.links {
  background-color: rgb(255, 168, 168);
  border: solid black 1px;
  margin-top: 5%;
  padding: 5%;
  border-radius: 4px;
}

.links:hover {
  background-color: rgb(250, 137, 137);
}

.links:visited {
  color: grey;
  font-weight: bold;
}
</style>
