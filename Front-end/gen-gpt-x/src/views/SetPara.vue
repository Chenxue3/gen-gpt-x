<template>
  <div>
    <el-container v-loading="loading">
      <el-aside width="400px"></el-aside>
      <el-container>
        <el-header>
          <my-header></my-header>
        </el-header>
        <el-main>
          <!-- set sy_depth -->
          <div class="content">
            <div class="text">
              {{ setting[0].text }}
            </div>
            <div class="set">
              <el-input-number
                v-model="sy_depth"
                controls-position="right"
                :step="setting[0].step"
                @change="handleChange"
                :min="setting[0].min"
                :max="setting[0].max"
                step-strictly
              ></el-input-number>
              <el-tooltip
                class="item"
                effect="dark"
                :content="setting[0].note"
                placement="top"
              >
                <i class="el-icon-warning-outline"></i>
              </el-tooltip>
            </div>
          </div>
          <!-- end -->

          <!-- set sy_num_goal -->
          <div class="content">
            <div class="text">
              {{ setting[1].text }}
            </div>
            <div class="set">
              <el-input-number
                v-model="sy_num_goal"
                controls-position="right"
                :step="setting[1].step"
                :min="setting[1].min"
                :max="setting[1].max"
                step-strictly
              ></el-input-number>
              <el-tooltip
                class="item"
                effect="dark"
                :content="setting[1].note"
                placement="top"
              >
                <i class="el-icon-warning-outline"></i>
              </el-tooltip>
            </div>
          </div>
          <!-- end -->
          <!-- set sy_num_plan -->
          <div class="content">
            <div class="text">
              {{ setting[2].text }}
            </div>
            <div class="set">
              <el-input-number
                v-model="sy_num_plan"
                controls-position="right"
                :step="setting[2].step"
                :min="setting[2].min"
                :max="setting[2].max"
                step-strictly
              ></el-input-number>
              <el-tooltip
                class="item"
                effect="dark"
                :content="setting[2].note"
                placement="top"
              >
                <i class="el-icon-warning-outline"></i>
              </el-tooltip>
            </div>
          </div>
          <!-- end -->

          <!-- set sy_num_action -->
          <div class="content">
            <div class="text">
              {{ setting[3].text }}
            </div>
            <div class="set">
              <el-input-number
                v-model="sy_num_action"
                controls-position="right"
                :step="setting[3].step"
                :min="setting[3].min"
                :max="setting[3].max"
                step-strictly
              ></el-input-number>
              <el-tooltip
                class="item"
                effect="dark"
                :content="setting[3].note"
                placement="top"
              >
                <i class="el-icon-warning-outline"></i>
              </el-tooltip>
            </div>
          </div>
          <!-- end -->

          <!-- set sy_prob_leaf -->
          <div class="content">
            <div class="text">
              {{ setting[4].text }}
            </div>
            <div class="set">
              <el-input-number
                v-model="sy_prob_leaf"
                controls-position="right"
                :step="setting[4].step"
                :min="setting[4].min"
                :max="setting[4].max"
                step-strictly
              ></el-input-number>
              <el-tooltip
                class="item"
                effect="dark"
                :content="setting[4].note"
                placement="top"
              >
                <i class="el-icon-warning-outline"></i>
              </el-tooltip>
            </div>
          </div>
          <!-- end -->

          <!-- set sy_num_var -->
          <div class="content">
            <div class="text">
              {{ setting[5].text }}
            </div>
            <div class="set">
              <el-input-number
                v-model="sy_num_var"
                controls-position="right"
                :step="setting[5].step"
                :min="setting[5].min"
                :max="setting[5].max"
                step-strictly
              ></el-input-number>
              <el-tooltip
                class="item"
                effect="dark"
                :content="setting[5].note"
                placement="top"
              >
                <i class="el-icon-warning-outline"></i>
              </el-tooltip>
            </div>
          </div>
          <!-- end -->

          <!-- set sy_num_selected -->
          <div class="content">
            <div class="text">
              {{ setting[6].text }}
            </div>
            <div class="set">
              <el-input-number
                v-model="sy_num_selected"
                controls-position="right"
                :step="setting[6].step"
                :min="setting[6].min"
                :max="sy_num_var"
                step-strictly
              ></el-input-number>
              <el-tooltip
                class="item"
                effect="dark"
                :content="setting[6].note"
                placement="top"
              >
                <i class="el-icon-warning-outline"></i>
              </el-tooltip>
            </div>
          </div>
          <!-- end -->

          <!-- set num_tree -->
          <div class="content">
            <div class="text">
              {{ setting[7].text }}
            </div>
            <div class="set">
              <el-input-number
                v-model="num_tree"
                controls-position="right"
                :step="setting[7].step"
                :min="setting[7].min"
                :max="setting[7].max"
                step-strictly
              ></el-input-number>
              <el-tooltip
                class="item"
                effect="dark"
                :content="setting[7].note"
                placement="top"
              >
                <i class="el-icon-warning-outline"></i>
              </el-tooltip>
            </div>
          </div>
          <!-- end -->
        </el-main>
        <el-footer
          ><el-button round @click="goPage()" class="create"
            >Create!</el-button
          ></el-footer
        >
      </el-container>
    </el-container>
  </div>
</template>

<script>
import MyHeader from '../components/MyHeader.vue';
export default {
  components: { MyHeader },
  name: "set-para",
  data() {
    return {
      loading: false,
      seed: 100,
      sy_depth: 3,
      num_tree: 5,
      sy_num_goal: 2,
      sy_num_plan: 2,
      sy_num_action: 2,
      sy_num_var: 60,
      sy_num_selected: 30,
      sy_prob_leaf: 0.5,
      setting: [
        {
          text: "Maximum depth of the tree:",
          note: "Set the maximum depth of the tree. default 3, >=1",
          min: 1,
          max: 100,
          step: 1,
        },
        {
          text: "Number of subgoals in each plan:",
          note: "Number of subgoals in each plan,default 2, >=1",
          min: 1,
          max: 100,
          step: 1,
        },
        {
          text: "Number of plans to achieve a goal:",
          note: "Number of plans to achieve a goal.default 2, >=1",
          min: 1,
          max: 100,
          step: 1,
        },
        {
          text: "Number of actions in each plan:",
          note: "Number of actions in each plan. default 2, >=2",
          min: 2,
          max: 100,
          step: 1,
        },
        {
          text: "Probability of a plan being leaf plan:",
          note: "Probability of a plan being leaf plan. default 0.5, should be >= 0 or <=1",
          min: 0,
          max: 1,
          step: 0.01,
        },
        {
          text: "Total number of environment variables: ",
          note: "Total number of environment variables. default 60, greater or eauql than 1",
          min: 1,
          max: 200,
          step: 1,
        },
        {
          text: "Number of selected variables for each GPT:",
          note: "Number of selected variables for each GPT, default 30, should be less or equal than the number of variables, and also greater or eauql than 1",
          min: 1,
          max: 200,
          step: 1,
        },
        {
          text: "Number of goal-plan trees:",
          note: "Number of goal-plan trees. default 3",
          min: 1,
          max: 30,
          step: 1,
        },
      ],
    };
  },
  methods: {
    handleChange() {
      //tbc
    },
    goPage() {
      this.loading = true;
      var paras = {
        seed: this.seed,
        sy_depth: this.sy_depth,
        sy_num_tree: this.num_tree,
        sy_num_goal: this.sy_num_goal,
        sy_num_plan: this.sy_num_plan,
        sy_num_var: this.sy_num_var,
        sy_num_action: this.sy_num_action,
        sy_num_selected: this.sy_num_selected,
        sy_prob_leaf: this.sy_prob_leaf,
      };
      var that = this;
      this.$http.post("/setPara", paras).then((res) => {
        console.log(res);
        if (res.status == 200) {
          that.loading == false;
          if ("msg" in res.data) {
            this.$alert(res.data.msg, "Error", {
              confirmButtonText: "Got it",
              type: 'error',
              callback: () => {
                this.loading = false;
              },
            });
          } else {
            that.$router.push({
              name: "tree",
              params: {
                data: res.data,
                para: paras,
                type: "2",
              },
            });
          }
        }
      }).catch((error)=>{
        this.$alert(error, "Error", {
              confirmButtonText: "OK",
              type: 'error',
              callback: () => {
                this.loading = false;
              },
            });
      });
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

.create {
  font-size: 1.1em;
  box-sizing: border-box;
}
.content {
  display: flex;
  flex-direction: row;
  line-height: initial;
  margin-top: 1.3%;
  border-bottom: 0.5px gainsboro dashed;
  align-content: space-between;
  justify-content: space-between;
}
.text {
  font-family: "Comic Sans MS Normal", "Comic Sans MS", sans-serif;
  font-size: 1.3em;
}
.el-icon-warning-outline {
  margin-left: 5px;
  color: darkgray;
  font-size: 1.3em;
}
</style>