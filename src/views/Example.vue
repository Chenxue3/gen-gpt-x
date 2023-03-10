<template>
  <div>
    <el-container>
      <el-header> GenGPT-X </el-header>
      <el-container>
        <el-aside width="700px">
          <select-example :example='examples[0]'></select-example>
          <select-example :example='examples[1]'></select-example>
        </el-aside>
        <el-container>
          <el-main></el-main>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import SelectExample from "../components/SelectExample.vue";
export default {
  components: { SelectExample },
  name: "example-page",
  data() {
    return {
      examples: [
        {
          icon: "el-icon-s-grid",
          title: "Block's Word",
          page:"/blocksword",
          des: "In the Block's World, the goal(i.e. desire) of an agent is to put block A on block B while placing block B on top of block C, which is put on the table.",
        },{
          icon: "el-icon-setting",
          title: "Craft Word",
          page:"/craftword",
          des: "In the Craft World domain, agents must collect resources and use them to craft various items. For example, collected wood can be crafted into a stick at a workbench, then combined with iron at a toolshed to craft an axe. The axe can then be used to mine a gem."
        }
      ],
    };
  },
};
</script>

<style scoped>
.el-header {
  color: #333;
  text-align: left;
  line-height: 60px;
  font-family: "Comic Sans MS Normal", "Comic Sans MS", sans-serif;
  font-weight: 300;
  font-size: 3em;
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
  color: #333;
  text-align: center;
  line-height: 530px;
  background-image: url("@/assets/aside-example.png");
  background-size: cover;
}
</style>