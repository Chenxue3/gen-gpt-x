<template>
  <div class="item">
    <div class="icon">
      <i :class="example.icon"></i>
    </div>
    <div class="left">
      <div class="title">
        {{ example.title }}
      </div>
      <div class="des">
        {{ example.des }}
      </div>
      <div class="btn">
        <el-button round @click="goToPage()">Create</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["example"],
  name: "example-item",
  data() {
    return {};
  },

  methods: {
    goToPage() {
      var path = "/tree";
      this.$router.push({ path: path });
    },
  },
};
</script>

<style scoped>
.item {
  margin-top: 20px;
  width: 80%;
  height: 45%;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-content: space-around;
  border-bottom: 1px solid #888;
  font-family: "Comic Sans MS Normal", "Comic Sans MS", sans-serif;
}
.icon {
  font-size: 6em;
  line-height: initial;
  display: flex;
  align-items: center;
  margin-right: 10px;
}
.left {
  display: flex;
  flex-direction: column;
  line-height: initial;
  text-align: left;
}
.title {
  height: 20%;
  font-size: 2em;
  display: flex;
  box-sizing: border-box;
  margin-left: 10px;
  display: flex;
  align-items: center;
  margin-bottom: -20px;
}
.des {
  height: 60%;
  box-sizing: border-box;
  margin-left: 10px;
  display: flex;
  align-items: center;
}
.btn {
  height: 20%;
}
</style>