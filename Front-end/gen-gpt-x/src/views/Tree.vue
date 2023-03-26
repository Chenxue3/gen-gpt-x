<template>
  <div class="tree" v-loading="loading">
    <div class="size-setting">
      <el-button @click="controlScale('bigger')">+</el-button>
      <el-button @click="controlScale('smaller')">-</el-button>
      <el-button @click="controlScale('restore')">1:1</el-button>
    </div>

    <div class="tree-control" v-if="this.isNormal && !this.fullscreen">
      <el-button
        icon="el-icon-arrow-left"
        :disabled="treeIndex == 0"
        @click="changeTree(-1)"
      ></el-button>
      Goal-Plan Tree: {{ this.treeIndex + 1 }} /
      {{ this.$route.params.para["sy_num_tree"] }}
      <el-button @click="changeTree(1)" :disabled="treeIndex == treeSize"
        ><i class="el-icon-arrow-right el-icon--right"></i
      ></el-button>
    </div>

    <div class="tree-control" v-if="this.isCraftworld && !this.fullscreen">
      <span v-if="this.treeIndex != 0" class="spanNext">
        {{this.craftWorld[this.treeIndex-1].name }} 
      </span>
      <span v-else class="spanNext">no more~</span>
      <el-button
        icon="el-icon-arrow-left"
        :disabled="treeIndex == 0"
        @click="changeCraftTree(-1)"
      ></el-button>
      {{this.craftWorld[this.treeIndex].name }} 
      <el-button @click="changeCraftTree(1)" :disabled="treeIndex == 9"
        ><i class="el-icon-arrow-right el-icon--right"></i
      ></el-button>
      <span v-if="this.treeIndex != 9" class="spanNext">
        {{this.craftWorld[this.treeIndex+1].name }} 
      </span>
      <span v-else class="spanNext">no more~</span>
    </div>

    <div class="tree-control" v-if="this.isBlocksworld && !this.fullscreen">
      Block's World
    </div>
    <div class="intro">
      <el-popover placement="left" width="300" trigger="hover">
        <intro-g-p-t></intro-g-p-t>
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
        <el-button
          slot="reference"
          icon="el-icon-info"
          circle
          style="margin-bottom: 15px"
        ></el-button>
      </el-popover>

      <el-tooltip
        effect="light"
        placement="left"
        content="Back To Home Page"
        visible-arrow="false"
      >
        <el-button icon="el-icon-s-home" circle @click="backHome()" v-if="!this.fullscreen"></el-button>
      </el-tooltip>
    </div>
    <div class="full-screen">
      <el-button
        :icon='fullscreen ? "el-icon-close": "el-icon-full-screen"'
        circle
        @click="setFull()"
      ></el-button>
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
import IntroGPT from "../components/IntroGPT.vue";
import { api as fullscreen } from "vue-fullscreen";
export default {
  components: { VueTree, ShowParas, IntroGPT },
  name: "tree-page",
  data() {
    return {
      fullscreen: false,
      teleport: true,
      render: 0,
      treeIndex: 0,
      isBlocksworld: false,
      treeSize: 0,
      scale: 0.5,
      isCraftworld: false,
      isNormal: false,
      loading: true,
      direction: "vertical",
      linkStyle: "straight",
      craftWorld:[],
      wholeTree: {},
      // this.direction="horizontal";
      treeData: {
        name: "none",
      },
      treeConfig: { nodeWidth: 120, nodeHeight: 80, levelHeight: 200 },
    };
  },

  created() {
    window.addEventListener("wheel", this.handleScroll);
    this.init();
  },
  beforeDestroy() {
    window.removeEventListener("wheel", this.scrollFunc);
    this.fullscreen = false;
  },
  methods: {
    init() {
      this.wholeTree = this.$route.params.data;
      //blocks world
      if (this.$route.params.type == 0) {
        this.treeConfig.nodeWidth = 210;
        this.treeConfig.levelHeight = 200;
        this.treeData = this.$route.params.data;
        this.isBlocksworld = true;
        this.loading = false;
      }
      if (this.$route.params.type == 1) {
        var craftData = this.$route.params.data;
        for (var i in craftData) {
          this.craftWorld.push(craftData[i]);
          this.treeSize = i;
        }
        this.treeConfig.nodeWidth = 180;
        this.treeConfig.levelHeight = 200;
        this.treeIndex = 0;
        this.treeData = this.craftWorld[0];
        this.isCraftworld = true;
        this.loading = false;
        // console.log(this.craftWorld)
      }
      if (this.$route.params.type == 2) {
        this.isNormal = true;
        this.paras = this.$route.params.para;
        // this.treeData = this.$route.params.data;
        for (var j in this.$route.params.data) {
          this.$route.params.data[j];
          this.treeSize = j;
        }
        this.treeData = this.$route.params.data[this.treeIndex];
        this.loading = false;
      }
    },

    /**
     * when full screen, use can use mouse to control the size
     * */
    handleScroll(e) {
      e = e || window.event;
      if (e.wheelDelta) {
        if (e.wheelDelta > 0 && this.fullscreen) {
          this.controlScale('bigger')
        }
        if (e.wheelDelta < 0 && this.fullscreen) {
          this.controlScale('smaller')
        }
      } else if (e.detail) {
        if (e.detail < 0 && this.fullscreen) {
          this.controlScale('bigger')
        }
        if (e.detail > 0 && this.fullscreen) {
          this.controlScale('smaller')
        }
      }
    },
    async setFull() {
      await fullscreen.toggle(this.$el.querySelector(".main-tree"), {
        teleport: this.teleport,
        callback: (isFullscreen) => {
          this.fullscreen = isFullscreen;
        },
      });
      this.fullscreen = fullscreen.isFullscreen;
    },
    backHome() {
      this.$router.push({
        name: "index",
      });
    },
    changeTree(num) {
      this.loading = true;
      this.treeIndex = this.treeIndex + num;
      this.treeData = this.wholeTree[this.treeIndex];
      this.loading = false;
    },
    changeCraftTree(num){
      this.treeIndex = this.treeIndex + num;
      // console.log(this.craftWorld[this.treeIndex])
      this.treeData = this.craftWorld[this.treeIndex]
    },
    downloadxml() {
      if (this.isNormal) {
        location.href = "http://localhost:8081/downloadxml";
      }
      if (this.isBlocksworld) {
        location.href = "http://localhost:8081/downloadBlocksWorldXML";
      }
      if(this.isCraftworld){
        location.href = "http://localhost:8081/downloadCraftWorldXML";
      }
    },
    downloadjson() {
      if (this.isBlocksworld) {
        location.href = "http://localhost:8081/downloadBlocksWorldJSON";
      }
      if (this.isNormal) {
        location.href = "http://localhost:8081/downloadjson";
      }
      if(this.isCraftworld){
        location.href = "http://localhost:8081/downloadCraftWorldXML";
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
  font-size: large;
  position: fixed;
  right: 50%;
  transform: translateX(50%);
  top: 20px;
  z-index: 999;
  font-family: "Comic Sans MS Normal", "Comic Sans MS", sans-serif;
}
.spanNext{
  font-size: smaller;
  color: gainsboro;
}
.full-screen {
  position: fixed;
  right: 20px;
  bottom: 20px;
  z-index: 999;
}
.tree {
  position: absolute;
  width: 100%;
  height: 100%;
  line-height: initial;
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
  