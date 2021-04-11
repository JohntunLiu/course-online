import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login.vue'
import App from "./App";

Vue.use(Router);

export default new Router({
  mode: 'history',
  base: process.env.BSSE_URL,
  routes:[{
    path: '*',
    redirect: "login",
  },{
    path: "/login",
    component: Login
  }]
})
