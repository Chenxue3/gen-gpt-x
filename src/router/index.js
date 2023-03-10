import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

// import views

import index from '@/views/Index.vue'
import Menu from '@/views/Menu.vue'
import example from "@/views/Example.vue"
import tree from "@/views/Tree.vue"
import setPara from "@/views/SetPara.vue"
const router = new VueRouter({
  routes: [
    {
      path:'/index',
      name:'index',
      component:index
    },
    {
      path: '/menu',
      name:"menu",
      component: Menu
    },
    {
      path: '/example',
      name:"example",
      component: example
    },
    {
      path:"/tree",
      name:"tree",
      component:tree
    },{
      path:"/setPara",
      name:"setPara",
      component:setPara
    }
  ]
})

export default router
