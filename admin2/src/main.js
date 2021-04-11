import { createApp } from 'vue'
import Vue from 'vue'
import App from './App.vue'
import router from './router'

// createApp(App).mount('#app')
createApp(App).use(router).config.productionTip = false;

 createApp({
  // 挂载里路由到根实例
  router,
  render: h => h(App),
}).use(router).mount('#app');