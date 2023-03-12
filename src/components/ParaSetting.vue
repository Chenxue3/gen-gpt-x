<!-- 
 * @description: 
 * @author: cxs 
 * @date: 2022-10-24 15:01:06
 * @version: V1.0.0 
-->



<template>
  <div class="content">
    <div class="text">
      {{ item.text }}
    </div>
    <div class="set">
      <el-input-number
        v-model="num"
        controls-position="right"
        :step="item.step"
        @change="handleChange"
        :min=item.min
        :max=item.max
      ></el-input-number>
      <el-tooltip class="item" effect="dark" :content="item.note" placement="top">
        <i class="el-icon-warning-outline"></i>
      </el-tooltip>
    </div>
  </div>
</template>
  
  <script scoped>
export default {
  props: ["item"],
  data() {
    return {
      num: 1,
    };
  },
  mounted(){
    this.num = this.item.value
  },
  methods: {
    handleChange(value) {
      console.log(value);
    },
  },
};
</script>
  <style scoped>
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
.el-icon-warning-outline{
    margin-left: 5px;
    color: darkgray;
    font-size: 1.3em;
}
</style>