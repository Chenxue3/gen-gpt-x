<!-- 
 * @description: 
 * @fileName: index.vue 
 * @author: cxs 
 * @date: 2022-10-24 11:16:13
 * @version: V1.0.0 
-->
<template>
  <div class="page">
    <el-container>
      <el-container>
        <el-container>
          <el-aside width="630px"></el-aside>
            <div class="title">
              <my-header></my-header>
            </div>
        </el-container>
        <el-footer height="200px">
          <div class="right">
            <div class="icon">
              <i class="el-icon-s-opportunity"></i>
            </div>
            <div class="btn">
              <el-button round @click="goPage()">Choose An Example</el-button>
            </div>
          </div>
          <div class="left">
            <div class="icon">
              <font-awesome-icon icon="fa-solid fa-tree" />
            </div>
            <div class="btn">
              <el-button round @click="setPara()"
                >Create An Abstrct GPT</el-button
              >
            </div>
          </div>
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script scoped>

import MyHeader from '@/components/MyHeader.vue';
export default {
  
  name: "index-page",
  components:{MyHeader},
  data() {
    return {};
  },
  methods: {
    setPara() {
      this.$router.push({ path: "/setPara" });
    },
    goPage() {
      this.$router.push({ path: "/example" });
    },
  },
};
</script>

<style scoped>
.page {
  padding: 0%;
}

.el-aside {
  color: #333;
  text-align: center;
  line-height: 350px;
  height: 350px;
  background-image: url("@/assets/aside_index.png");
  background-repeat: no-repeat;
}

.title {
  margin-top: 60px;
  font-size: 3em;

}
body > .el-container {
  margin-bottom: 0px;
}

.el-footer {
  color: #333;
  text-align: center;
  line-height: 200px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
.right,
.left {
  width: 50%;
  display: flex;
  flex-direction: column;
  align-content: space-around;
  justify-content: center;
}
.icon {
  margin-top: 50px;
  font-size: 5em;
  height: fit-content;
}
.btn {
  height: fit-content;
  margin-top: -100px;
  font-family: "Comic Sans MS Normal", "Comic Sans MS", sans-serif;
}
</style>