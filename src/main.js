import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import * as echarts from 'echarts'
import store from './store'
import axios from 'axios';

Vue.prototype.$baseUrl = process.env.baseUrl
Vue.prototype.$echarts = echarts;
Vue.prototype.$axios = axios;

Vue.use(ElementUI)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
