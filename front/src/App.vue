<template>
  <NavBar />
  <router-view />
</template>

<script>
import NavBar from "./views/NavBar.vue";

export default {
  components: { NavBar },
};
</script>

<style>

</style>
