<template>
  <div class="tree">
    <el-button @click="onChangeDataset">change dataset</el-button>
    <vue-tree
      style="width: 100%; height: 100%;"
      :dataset="richMediaData"
      :config="treeConfig"
      linkStyle="straight"
    >
      <template v-slot:node="{ node, collapsed }">
        <div
          class="rich-media-node"
          :style="{ border: collapsed ? '2px solid grey' : '' }"
        >
        <el-tooltip class="item" effect="dark" :content="node.value" placement="top">
          <img
            :src="node.avatar"
            style="width: 48px; height: 48px; border-raduis: 4px;"
          />
        </el-tooltip>
          <span style="padding: 4px 0; font-weight: bold;"
            >{{ node.name }}</span
          >
        </div>
      </template>
    </vue-tree>
  </div>
</template>
    
<script>
import VueTree from "@ssthouse/vue-tree-chart";
export default {
  name:"craft-woeld",
  components: { VueTree },
  data() {
    return {
      showTree: false,
      richMediaData: {
        name: 'James',
        value: 800,
        avatar:
          'https://upload.wikimedia.org/wikipedia/commons/a/a0/Circle_-_black_simple.svg',
        children: [
          {
            name: 'Bob',
            value: 400,
            avatar:
              'https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Square_-_black_simple.svg/800px-Square_-_black_simple.svg.png',
            children: [
              {
                name: 'C1',
                value: 100,
                avatar:
                  'https://live.yworks.com/demos/complete/interactiveorgchart/resources/usericon_female4.svg'
              },
              {
                name: 'C2',
                value: 300,
                avatar:
                  'https://live.yworks.com/demos/complete/interactiveorgchart/resources/usericon_male2.svg'
              },
              {
                name: 'C3',
                value: 200,
                avatar:
                  'https://live.yworks.com/demos/complete/interactiveorgchart/resources/usericon_male3.svg'
              }
            ]
          },
          {
            name: 'Smith',
            value: 200,
            avatar:
              'https://live.yworks.com/demos/complete/interactiveorgchart/resources/usericon_male3.svg',
            children: [
              {
                name: 'S1',
                value: 230,
                avatar:
                  'https://live.yworks.com/demos/complete/interactiveorgchart/resources/usericon_female1.svg'
              }
            ]
          },
          {
            name: 'Jackson',
            value: 300,
            avatar:
              'https://live.yworks.com/demos/complete/interactiveorgchart/resources/usericon_female3.svg'
          }
        ]
      },
      treeConfig: { nodeWidth: 120, nodeHeight: 80, levelHeight: 200 },
    };
  },
  methods: {
    onChangeDataset() {
      
      this.sampleData = {
        value: "100",
        children: [
          { value: "moveto(x,y)" },
          { value: "300", children: [{ value: "400" }, { value: "500" }] },
        ],
      };
    },
    onClick() {
      this.showTree = true;
    },
  },
};
</script>
    
    <style scoped>
.tree {
  width: 100%;
  height: 100%;
  line-height: initial;
}
.rich-media-node {
  padding: 8px;
  display: flex;
  height: 60px;
  flex-direction: column;
  align-items: center;
  border: 2px dashed darkcyan;
  justify-content: center;
  background-color: #FFF;
}
</style>