import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import * as echarts from 'echarts'
import store from './store'
import axios from 'axios';
import PrimeVue from 'primevue/config';


import 'primevue/resources/themes/saga-blue/theme.css';
import 'primevue/resources/primevue.min.css';
import 'primeicons/primeicons.css';


Vue.prototype.$baseUrl = process.env.baseUrl
Vue.prototype.$echarts = echarts;
Vue.prototype.$axios = axios;

Vue.use(ElementUI)
Vue.use(PrimeVue);
Vue.config.productionTip = false


// PrimeVue组件
import Steps from 'primevue/steps';
import Button from 'primevue/button';
import Carousel from 'primevue/carousel';
import Card from 'primevue/card';
import Editor from 'primevue/editor';
import Fieldset from 'primevue/fieldset';
// eslint-disable-next-line
Vue.component('Steps', Steps);
// eslint-disable-next-line
Vue.component('Button', Button);
// eslint-disable-next-line
Vue.component('Carousel', Carousel);
// eslint-disable-next-line
Vue.component('Card', Card);
// eslint-disable-next-line
Vue.component('Editor', Editor);
// eslint-disable-next-line
Vue.component('Fieldset', Fieldset);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
