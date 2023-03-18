<template>
    <div>
      <el-link href="/index" class="header">GenGPT-X</el-link>
    </div>
    
  </template>
  
  <script>
  
  export default {
    name: 'header-item',
    methods:{
      backIndex(){
        this.$route.push({
          name:"index"
        })
      }
    },
    mounted(){
      
    }
  }
  </script>
  
  <style scoped>

.header{
    font-family: "Comic Sans MS Normal", "Comic Sans MS", sans-serif;
    font-weight: 300;
    font-size: 2em;

}
  </style>
  