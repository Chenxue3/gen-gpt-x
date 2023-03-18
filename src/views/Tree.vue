<template>
  <div class="tree" v-loading="loading">
    
    <div class="size-setting">
      <el-button @click="controlScale('bigger')">+</el-button>
      <el-button @click="controlScale('smaller')">-</el-button>
      <el-button @click="controlScale('restore')">1:1</el-button>
    </div>
  
    <div class="tree-control" v-if="this.isNormal">
      <el-button
          icon="el-icon-arrow-left"
          :disabled="treeIndex == 0"
          @click="changeTree(-1)"
        ></el-button>
      Tree: {{ this.treeIndex+1 }} / {{this.$route.params.para["sy_num_tree"] }}
        <el-button @click="changeTree(1)" :disabled="treeIndex == treeSize"
          ><i class="el-icon-arrow-right el-icon--right"></i
        ></el-button>
    </div>
    <div class="tree-control" v-if="!this.isNormal">
     <i v-if="this.isBlocksworld">Blocks World</i>
     <i v-else>Craft World</i>
    </div>
    <div class="intro">
      <el-popover placement="left" width="300" trigger="hover">
        <div class="des">
          Triangle
          <img src="@/assets/img_action.png" alt="" width="50px" />
          reprents ACTIONS.
          <br />
          Square
          <img src="@/assets/img_plan.png" alt="" width="60px" />
          reprents PLANS.
          <br />
          Circle
          <img src="@/assets/img_goal.svg" alt="" width="50px" />
          reprents GOALS.
          <br />
          <br />
          <el-divider></el-divider>
          <el-link type="primary" href="https://github.com/yvy714/GenGPT"
            >Learn more about GenGPT-X</el-link
          >
        </div>
        <el-button
          slot="reference"
          icon="el-icon-question"
          circle
          style="margin-bottom: 15px"
        ></el-button>
      </el-popover>
      <el-popover placement="left" title="Download" width="300" trigger="hover">
        <el-button @click="downloadxml()">Download XML</el-button>
        <el-button @click="downloadjson()">Download Json</el-button>
        <el-button
          slot="reference"
          icon="el-icon-download"
          style="margin-bottom: 15px"
          circle
        ></el-button>
      </el-popover>

      <el-popover v-if="isNormal" placement="left" width="350" trigger="hover">
        <div class="paras">
          <show-paras :paras="this.$route.params.para"></show-paras>
        </div>
        <el-button slot="reference" icon="el-icon-info" circle style="margin-bottom: 15px"></el-button>
      </el-popover>

      <el-popover placement="left" width="200" trigger="hover">
        Back To Home Page:
       <my-header></my-header>
      <el-button slot="reference" icon="el-icon-s-home" circle  @click="backHome()"></el-button>
      </el-popover>
    </div>
    <vue-tree
      ref="scaleTree"
      style="width: 100%; height: 100%; overflow: visible"
      :dataset="treeData"
      :config="treeConfig"
      :linkStyle="this.linkStyle"
      :direction="this.direction"
      :currentScale="this.scale"
      class="main-tree"
      
    >
      <template v-slot:node="{ node, collapsed }">
        <div
          class="tree-node"
          :style="{ border: collapsed ? '2px solid grey' : '' }"
        >
          <el-tooltip effect="dark" placement="top">
            <div slot="content">
              Type: {{ node.type }} <br />
              <br />Conditions: {{ node.conds }}
            </div>
            <img
              :src="node.attr"
              style="width: 48px; height: 48px; border-raduis: 4px"
            />
          </el-tooltip>
          <span style="padding: 4px 0; font-weight: bold">{{ node.name }}</span>
        </div>
      </template>
    </vue-tree>
  </div>
</template>
  
  <script>
import VueTree from "@ssthouse/vue-tree-chart";
import ShowParas from "@/components/ShowParas.vue";
import MyHeader from '../components/MyHeader.vue';
export default {
  components: { VueTree,ShowParas,MyHeader},
  name: "tree-page",
  data() {
    return {
      render: 0,
      treeIndex: 0,
      isBlocksworld: false,
      treeSize: 0,
      scale: 0.5,
      isCraftworld: false,
      isNormal: false,
      loading : true,
      direction: "vertical",
      linkStyle: "straight",
      wholeTree:{},
      // this.direction="horizontal";
      treeData: {
        name: "none",
      },
      treeConfig: { nodeWidth: 120, nodeHeight: 80, levelHeight: 200 },
    };
  },
  created() {
    console.log("get:" + this.$route.params.type);
    this.wholeTree = this.$route.params.data;
    //blocks world
    if (this.$route.params.type == 0) {
      this.treeConfig.nodeWidth = 200;
      this.treeConfig.levelHeight = 200;
      this.linkStyle = "curve";
      this.treeData = this.$route.params.data;
      this.isBlocksworld = true;
      this.loading = false;
    }
    if (this.$route.params.type == 1) {
      console.log("json:");
      for (var i in this.$route.params.data) {
        console.log(i);
        this.treeSize = i;
      }
      this.treeData = this.$route.params.data[this.treeIndex];

      this.isCraftworld = true;
      this.loading = false;
    }
    if (this.$route.params.type == 2) {
      this.isNormal = true;
      this.paras = this.$route.params.para;
      // this.treeData = this.$route.params.data;
      console.log("json:");
      for (var j in this.$route.params.data) {
        this.$route.params.data[j];
        this.treeSize = j;
      }
      this.treeData = this.$route.params.data[this.treeIndex];
      this.loading = false;
    }
  },
  methods: {
    backHome(){
      this.$route.push({
          name:"index"
        })
    },
    changeTree(num){
      this.loading = true;
      this.treeIndex = this.treeIndex+num;
      this.treeData = this.wholeTree[this.treeIndex]
      console.log("tree size"+this.treeSize, "tree index"+this.treeIndex)
      this.loading = false;
    },
    downloadxml() {
      if(this.isNormal){
      location.href = "http://localhost:8081/downloadxml";
      }
      if(this.isBlocksworld){
        location.href = "http://localhost:8081/downloadBlocksWorldXML";
      }
    },
    downloadjson() {
      if(this.isBlocksworld){
        location.href = "http://localhost:8081/downloadBlocksWorldJSON";
      }
      if(this.isNormal){
        location.href = "http://localhost:8081/downloadjson";
      }
    },
    controlScale(command) {
      switch (command) {
        case "bigger":
          this.$refs.scaleTree.zoomIn();
          break;
        case "smaller":
          this.$refs.scaleTree.zoomOut();
          break;
        case "restore":
          this.$refs.scaleTree.restoreScale();
          break;
      }
    },
    onClick() {
      this.showTree = true;
    },
  },
  props: {
    msg: String,
  },
};
</script>
  
  <style scoped>
  .size-setting {
  position: fixed;
  left: 20px;
  top: 20px;
  z-index: 999;
}

.intro {
  font-family: "Comic Sans MS Normal", "Comic Sans MS", sans-serif;
  position: fixed;
  right: 20px;
  top: 20px;
  z-index: 999;
  display: flex;
  flex-direction: column;
}
.tree-control {
  position: fixed;
  right: 50%;
  transform: translateX(50%);
  top: 20px;
  z-index: 999;
  font-family: "Comic Sans MS Normal", "Comic Sans MS", sans-serif;
  background-color: lightcyan;
}
.tree {
  position: absolute;
  width: 100%;
  height: 100%;
  line-height: initial;
  z-index: -999;
}
.tree-node {
  padding: 8px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 2px dashed darkcyan;
  justify-content: center;
  background-color: #fff;
}

.main-tree {
  margin-top: 5%;
}
</style>
  