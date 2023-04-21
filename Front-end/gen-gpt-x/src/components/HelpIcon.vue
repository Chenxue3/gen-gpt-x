<template>
  <div>
    <el-tooltip effect="dark" placement="bottom" content="Help" >
      <font-awesome-icon  class="help" icon="fa-solid fa-book" @click="goHelp()" />
    </el-tooltip>
  </div>
</template>

<script>
export default {
    created(){
        
    },
    methods:{
      // go to the help page
        goHelp(){
            window.open("https://xueshanchen.github.io/")
        }
    }
};
</script>

<style>
.help{
    position: fixed;
    right: 20px;
    top: 10px;
    z-index: 999;
    font-size: 2em;
    color:rgb(160, 191, 124);
}
.help:hover{
    position: fixed;
    right: 20px;
    top: 10px;
    z-index: 999;
    font-size: 2.5em;
    color:rgb(64, 116, 52);
}
</style>