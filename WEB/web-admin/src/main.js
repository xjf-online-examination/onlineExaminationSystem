import Vue from 'vue';
import iView from 'iview';
import router from './router';
import store from './store';
import config from '@/config';
import './index.less';
import '@/assets/icons/iconfont.css';
import axios from 'axios';
import App from './App.vue';
import tabletotal from './components/journalizing/total/index';
import tablemoney from './components/journalizing/money/index';
import tabletext from './components/journalizing/text/index';
import tableselect from './components/journalizing/select/index';
import tablescore from './components/journalizing/score/index';

axios.defaults.withCredentials = true;
Vue.use(iView);
Vue.config.productionTip = false;
Vue.prototype.$config = config;
Vue.component('tabletotal', tabletotal);
Vue.component('tabletext', tabletext);
Vue.component('tableselect', tableselect);
Vue.component('tablemoney', tablemoney);
Vue.component('tablescore', tablescore);
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
