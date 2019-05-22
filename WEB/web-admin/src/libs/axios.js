import axios from 'axios';
import store from '@/store';
import {
  getToken,
  getUserCode,
} from './util';
// import { Spin } from 'iview'
const addErrorLog = (errorInfo) => {
  const {
    statusText,
    status,
    request: {
      responseURL,
    },
  } = errorInfo;
  const info = {
    type: 'ajax',
    code: status,
    mes: statusText,
    url: responseURL,
  };
  if (!responseURL.includes('save_error_logger')) store.dispatch('addErrorLog', info);
};

class HttpRequest {
  constructor(baseUrl = baseURL) {
    this.baseUrl = baseUrl;
    this.queue = {};
  }

  getInsideConfig() {
    const config = {
      baseURL: this.baseUrl,
      headers: {
        //
      },
    };
    return config;
  }

  destroy(url) {
    delete this.queue[url];
    if (!Object.keys(this.queue).length) {
      // Spin.hide()
    }
  }

  interceptors(instance, url) {
    // 请求拦截
    instance.interceptors.request.use((config) => {
      // 添加全局的loading...
      if (!Object.keys(this.queue).length) {
        // Spin.show() // 不建议开启，因为界面不友好
      }
      this.queue[url] = true;

      // config.data.timestamp = new Date().getTime();
      if (url.indexOf('login') < 0 && config.method.toUpperCase() === 'POST') {
        config.data.securityKey = getToken();
        config.data.userCode = getUserCode();
      }
      return config;
    }, error => Promise.reject(error));
    // 响应拦截
    instance.interceptors.response.use((res) => {
      this.destroy(url);
      const {
        data,
      } = res;
      return data;
    }, (error) => {
      this.destroy(url);
      let errorInfo = error.response;
      if (!errorInfo) {
        const {
          request: {
            statusText,
            status,
          },
          config,
        } = JSON.parse(JSON.stringify(error));
        errorInfo = {
          statusText,
          status,
          request: {
            responseURL: config.url,
          },
        };
      }
      addErrorLog(errorInfo);
      return Promise.reject(error);
    });
  }

  request(options) {
    const instance = axios.create();
    options = Object.assign(this.getInsideConfig(), options);
    this.interceptors(instance, options.url);
    return instance(options);
  }
}
export default HttpRequest;
