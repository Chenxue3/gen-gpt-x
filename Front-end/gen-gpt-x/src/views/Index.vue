<!-- 
 * @description: index page
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
          <!-- title -->
          <el-aside width="630px"></el-aside>
          <div class="title">GenGPT-X</div>
        </el-container>
        <el-footer height="200px">
          <div class="right">
            <div class="icon">
              <i class="el-icon-s-opportunity" style="color:rgb(242, 192, 106)"></i>
            </div>
            <!-- go to real-life examples page -->
            <div class="btn">
              <el-button round @click="goPage()">Create GPT Based on Real Examples</el-button>
            </div>
          </div>
           <div class="right">
            <!-- go generation page by GenGPT-X -->
            <div class="icon">
              <font-awesome-icon icon="fa-solid fa-wand-magic-sparkles" />
            </div>
            <div class="btn">
              <el-button round @click="setPara(0)">Create Abstract GPTs by GenGPT-X</el-button>
            </div>
          </div>
          <div class="left">
            <div class="icon" style="color:rgb(134, 186, 61)">
              <font-awesome-icon icon="fa-solid fa-tree"  />
            </div>
            
            <div class="btn">
              <el-button round @click="setPara(1)"
                >Create Abstrct GPTs by GenGPT</el-button
              >
            </div>
            <help-icon></help-icon>
          </div>
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script scoped>
import HelpIcon from '@/components/HelpIcon.vue';
export default {
  components: { HelpIcon },
  name: "index-page",
  data() {
    return {};
  },
  methods: {
    setPara(type) {
      this.$router.push({
              name: "setPara",
              params: {
                type: type,
              },
            });
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
  font-family: "Comic Sans MS Normal", "Comic Sans MS", sans-serif;
  font-weight: 300;
  font-size: 6em;
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