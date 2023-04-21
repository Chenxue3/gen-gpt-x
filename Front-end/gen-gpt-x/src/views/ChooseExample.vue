<!-- 
 * @description: used to choose an example to create a GPT
 * @fileName: ChooseExample.vue
 * @author: cxs 
 * @version: V1.0.0 
-->

<template>
  <div>
    <!-- show the real-life examples -->
    <el-container>
      <el-header>
        <my-header></my-header> 
      </el-header>
      <el-container class="container">
        <el-aside width="70%">
          <select-example :example="examples[0]"></select-example>
          <select-example :example="examples[1]"></select-example>
        </el-aside>
        <el-main width="30%"></el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script scoped>

import MyHeader from '@/components/MyHeader.vue';
import SelectExample from "../components/ExampleItem.vue";
export default {
  components: { SelectExample, MyHeader },
  name: "example-page",
  data() {
    return {
      // the real-life examples
      examples: [
        {
          icon: "fa-solid fa-cube",
          title: "Block's World",
          page: "/blocksword",
          type: 0,
          des: "In the Block's World, the goal of an agent is to put block A on block B while placing block B on top of block C, which is put on the table.",
        },
        {
          icon: "fa-solid fa-hammer",
          type: 1,
          title: "Craft World",
          page: "/craftword",
          des: "In the Craft World domain, agents must collect resources and use them to craft various items. For example, collected wood can be crafted into a stick at a workbench, then combined with iron at a toolshed to craft an axe. The axe can then be used to mine a gem.",
        },
      ],
    };
  },
};
</script>

<style scoped>
.container{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.el-header {
  color: #333;
  text-align: left;
  line-height: 60px;
}

.el-aside {
  color: #333;
  text-align: center;
  line-height: 510px;
  height: 510px;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
}

.el-main {
  width: 30%;
  color: #333;
  text-align: center;
  background-image: url("@/assets/aside-example.png");
  background-size: contain;
  background-repeat: no-repeat;
}
</style>