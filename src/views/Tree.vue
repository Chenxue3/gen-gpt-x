<template>
  <div class="page">
    <el-container>
      <el-aside width="300px">
        <div class="tit">GenGPT-X</div>
        <div class="des">
          Triangle 
          <img src="@/assets/img_action.png" alt="" width="50px">
          reprents ACTIONS.
          Square
          <img src="@/assets/img_plan.png" alt="" width="60px">
          reprents PLANS.
          <br>
          Circle
          <img src="@/assets/img_goal.svg" alt="" width="50px">
          reprents GOALS.
          <br><br>
           <div class="more"> >>>Learn more about GenGPT-X</div>
        </div>
        <div class="download">
          <el-button>Download XML</el-button>
          <el-button>Download Json</el-button>
        </div>
      </el-aside>
      <el-main>
        <blocksword></blocksword>
        <craftword  v-if="false"></craftword>
      </el-main>
    </el-container>
  </div>
</template>
  
  <script>
import Craftword from "@/components/Craftworld.vue";
import Blocksword from "../components/Blocksword.vue";
export default {
  components: { Blocksword, Craftword },
  name: "tree-page",
  props: {
    msg: String,
  },
};
</script>
  
  <style scoped>
.el-aside {
  font-family: "Comic Sans MS Normal", "Comic Sans MS", sans-serif;
  font-weight: 300;
  border-right: black 2px dashed;
  color: #333;
  text-align: center;
  height: 600px;
}

.el-main {
  color: #333;
  text-align: center;
  height: 600px;
}
.tit{
  font-size: 2em;
}

.more{
  font-size:small;
}
.download{
  box-sizing: border-box;
  margin-top: 100%;
}
</style>
  