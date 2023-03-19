<template>
    <div class="des">
        In the upper left corner, you can control the size of the goal-plan-tree. 
        <el-divider></el-divider>
        Click <span class="underline">nodes with the green dotted border</span> to collapse the corresponding child nodes. 
        <br />
        Click <span class="underlineS">nodes with the grey solid border</span> to extend the corresponding child nodes.
        <el-divider></el-divider>
        You can use full-screen browsing by click bottom right icon (<i class="el-icon-full-screen"></i>), and use the mouse wheel to quickly zoom the tree.
        <el-divider></el-divider>
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
</template>

<script>
export default {
   

}
</script>

<style scoped>
.des{
    font-family: "Comic Sans MS Normal", "Comic Sans MS", sans-serif;
    word-wrap:break-word;
    word-break: keep-all;
}
.underline{
    text-decoration:underline 2px dashed darkcyan;
}
.underlineS{
    text-decoration:underline 2px solid grey;
}
</style>