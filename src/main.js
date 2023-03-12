import Vue from 'vue'
import App from './App.vue'
import router from '@/router/index.js';
import VueTree from "@ssthouse/vue-tree-chart";
import axios from "axios";
// import element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// config

Vue.use(ElementUI,axios)
Vue.component(VueTree)
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
