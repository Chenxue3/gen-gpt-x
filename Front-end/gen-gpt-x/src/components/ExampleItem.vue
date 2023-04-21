<template>
  <div class="item">
    <!-- show the icon -->
    <div class="icon">
      <font-awesome-icon :icon="example.icon" />
    </div>
    <!-- show the title and description -->
    <div class="left">
      <div class="title">
        {{ example.title }}
      </div>
      <div class="des">
        {{ example.des }}
      </div>
      <div class="btn">
        <el-button round @click="goPage(example.type)">Create</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["example"],
  name: "example-item",
  data() {
    return {
      loading: false,
    };
  },

  methods: {
    // go to the tree page
    goPage(type) {
      this.loading = true;
      var add;
      if (type == 0) {
        add = "/getBlocksWorld";
        
      } else {
         add ="/getCraftWorld";
      }
      this.getData(add, type);
    },
    // get the data from the server
    getData(add, type) {
      var that = this;
      this.$http
        .get(add)
        .then((res) => {
          console.log("get the data:");
          console.log(res);
          if (res.status == 200) {
            that.loading == false;
            if ("msg" in res.data) {
              this.$alert(res.data.msg, "Error", {
                confirmButtonText: "Got it",
                type: "error",
                callback: () => {
                  this.loading = false;
                },
              });
            } else {
              that.$router.push({
                name: "tree",
                params: {
                  data: res.data,
                  type: type,
                },
              });
            }
          }
        })
        .catch((error) => {
          this.$alert(error, "Error", {
            confirmButtonText: "OK",
            type: "error",
            callback: () => {
              this.loading = false;
            },
          });
        });
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