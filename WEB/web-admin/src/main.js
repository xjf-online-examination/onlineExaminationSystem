import Vue from 'vue';
import router from '@/router';
import store from '@/store';
import iView from 'iview';
import config from '@/config';
import './index.less';
import '@/assets/icons/iconfont.css';
import axios from 'axios';
import App from './App.vue';

axios.defaults.withCredentials = true;
Vue.use(iView);
Vue.config.productionTip = false;
Vue.prototype.$config = config;

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
