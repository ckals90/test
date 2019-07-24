<template>
  <div id="app">
	<router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  data: () => ({
    super_userId:'씨발',
  }),
}
</script>

<style>
@charset "UTF-8";
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
