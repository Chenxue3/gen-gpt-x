<template>
  <div>
    <font-awesome-icon icon="fa-solid fa-sliders" /> Parameters:
    <br />
    <li v-for="(key, value) in des" :key="key">
      {{ key }} {{ paras[value] }}
    </li>
    
  </div>
</template>
  
  <script>
export default {
  name: "show-paras",
  props: ["paras"],
  components: {},
  data() {
    return {
      des: {
        seed: "Number of seed:",
        sy_depth: "Maximum depth of the tree:",
        sy_num_tree: "Number of goal-plan trees:",
        sy_num_goal: "Number of subgoals in each plan:",
        sy_num_plan: "Number of plans to achieve a goal:",
        sy_num_var: "Total number of environment variables:",
        sy_num_action: "Number of actions in each plan:",
        sy_num_selected:
          "Number of selected variables for each goal-plan-tree:",
        sy_prob_leaf: "Probability of a plan being leaf plan:",
      },
    };
  },
};
</script>
  
  <style scoped>
</style>
  