import Vue from 'vue'
import App from './App.vue'
import router from '@/router/index.js';
import VueTree from "@ssthouse/vue-tree-chart";
import axios from "axios";
import VueFullscreen from 'vue-fullscreen';
/* import the fontawesome core */
import { library } from '@fortawesome/fontawesome-svg-core'

/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

/* import specific icons */
import { faTree,faCube,faHammer,faSliders,faHandshakeAngle,faBook,faWandMagicSparkles } from '@fortawesome/free-solid-svg-icons'

// import element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
/* add icons to the library */
library.add(faTree,faCube,faHammer,faSliders,faHandshakeAngle,faBook,faWandMagicSparkles)

/* add font awesome icon component */
Vue.component('font-awesome-icon', FontAwesomeIcon)
// config
axios.defaults.baseURL="http://localhost:8081"
Vue.prototype.$http = axios
Vue.use(ElementUI,VueFullscreen)
Vue.component(VueTree)
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
