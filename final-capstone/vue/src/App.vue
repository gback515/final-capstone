<template>
  <div id="app">
    <div id="nav">
      <img id="golf-ball" src="@/assets/golf-ball-logo.png" />
      <router-link
        class="link"
        style="text-decoration: none; color: inherit"
        v-bind:to="{ name: 'home' }"
        >Home</router-link
      >
      <router-link
        class="link"
        style="text-decoration: none; color: inherit"
        v-bind:to="{ name: 'create-league' }"
        >Create New League</router-link
      >
      <router-link
        class="link"
        style="text-decoration: none; color: inherit"
        v-bind:to="{ name: 'add-golfer' }"
        >Add Golfer</router-link
      >
      <router-link
        class="link"
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
  justify-content: space-between;
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
  background-color: rgba(31, 219, 72, 0.925);
  align-items: center;
  padding-top: 10px;
}
#golf-ball {
  max-width: 100%;
  height: auto;
}
.link:hover {
  color: grey;
}
.link:active {
  font-weight: bold;
}
</style>
