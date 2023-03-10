<template>
  <div>
    <el-container>
      <el-aside width="400px"></el-aside>
      <el-container>
        <el-header>GenGPT-X</el-header>
        <el-main>
            <div v-for="(item,index) in setting" :key="index">
                <para-setting :item="item"></para-setting>
            </div>
            
        </el-main>
        <el-footer
          ><el-button round @click="goPage()" class="create"
            >Craete</el-button
          ></el-footer
        >
      </el-container>
    </el-container>
  </div>
</template>

<script>
import ParaSetting from '@/components/ParaSetting.vue';
export default {
  components: { ParaSetting },

  name: "set-para",
  data() {
    return {
        setting:[
            {
                text:"Maximum depth of the tree:",
                note:"Set the maximum depth of the tree. default 3, >=1",
                min:1,
                max:100
            },
            {
                text:"Number of subgoals in each plan:",
                note:"Number of subgoals in each plan,default 3, >=1",
                min:1,
                max:100
            },
            {
                text:"Number of plans to achieve a goal:",
                note:"Number of plans to achieve a goal.default 3, >=1",
                min:1,
                max:100
            },
            {
                text:"Number of actions in each plan:",
                note:"Number of actions in each plan. default 3, >=2",
                min:2,
                max:100
            },
            {
                text:"Probability of a plan being leaf plan:",
                note:"Probability of a plan being leaf plan. default 0, >= 0 or <=1",
                min:0,
                max:1
            },
            {
                text:"Total number of environment variables: ",
                note:"Total number of environment variables. default 60, >- 1",
                min:1,
                max:100
            },
            {
                text:"Number of selected variables for each GPT:",
                note:"Number of selected variables for each GPT, default 30, #ν ≥ #ε ≥ 1, ",
                min:1,
                max:100
            },{
                text:"Number of goal-plan trees:",
                note:"Number of goal-plan trees. default 30",
                min:1,
                max:50
            }


    ]
    };
  },
  methods: {
    goPage() {
      this.$router.push({ path: "/tree" });
    },
  },
};
</script >

<style scoped>
.el-header,
.el-footer {
  color: #333;
  text-align: center;
  line-height: 60px;
  height: 100px;
}

.el-header {
  height: 100px;
  font-family: "Comic Sans MS Normal", "Comic Sans MS", sans-serif;
  font-weight: 300;
  font-size: 2em;
}

.el-aside {
  color: #333;
  text-align: center;
  line-height: 600px;
  background-image: url(@/assets/side.png);
  background-size: contain;
  background-repeat: no-repeat;
}

.el-main {
  color: #333;
  text-align: center;
  line-height: 400px;
}

.create{
    font-size: 1.1em;
    box-sizing: border-box;
}
</style>