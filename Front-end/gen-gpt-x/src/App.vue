<template>
  <div id="app">
    <router-view></router-view>
    <el-backtop target=".page-component__scroll .el-scrollbar__wrap"></el-backtop>
  </div>
  
</template>

<script>
// import index from "./views/Index.vue"

export default {
  name: 'App',
  components: {
    // index
  }
}
</script>

<style>
::-webkit-scrollbar {
  width: 0 !important;
}
::-webkit-scrollbar {
  width: 0 !important;
  height: 0;
}
</style>
