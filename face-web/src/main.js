// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import MyFrame from './MyFrame.vue';
import router from './router';
import $ from 'jquery';
import echarts from 'echarts';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import common from './assets/js/common';
import cookieUtil from './assets/js/cookieUtil';
import jQueryRotate from './assets/js/jQueryRotate';

import 'swiper/dist/css/swiper.css';
import './assets/css/reset.css';
import './assets/css/custom.css';
import './assets/css/public.css';
import './assets/css/index.css';
import '@/assets/css/element.css';

Vue.prototype.router = router;
Vue.prototype.echarts = echarts;
Vue.prototype.$ = $;
Vue.prototype.common = common;
Vue.prototype.cookieUtil = cookieUtil;
Vue.prototype.loadingOptions = {
  fullscreen: true,
  body: true,
  lock: true,
  text: '数据加载中，请稍后……',
  spinner: 'el-icon-loading',
  background: 'rgba(0,0,0,0.1)'
};

Vue.use(ElementUI);
Vue.config.productionTip = true;

Vue.prototype.get = function (url, success, error, async, load) {
  ajax('GET', url, null, success, error, async, load);
};
Vue.prototype.post = function (url, data, success, error, async, load) {
  ajax('POST', url, data, success, error, async, load);
};

Vue.prototype.rotateToLeft = function(){
  $('.dtailContent').on('click', '.turnLft', function(){
    var oimg = $('.infoimg',$(this).closest('.imgouter'));
    var ang = parseInt(oimg.attr('angle'))-90;

    oimg.rotate({
      animateTo: ang,
      duration: 500,
    });
    oimg.attr('angle',ang);
  });
}
Vue.prototype.rotateToRight = function(){
  $('.dtailContent').on('click', '.turnRt', function(){
    var oimg = $('.infoimg',$(this).closest('.imgouter'));
    var ang = parseInt(oimg.attr('angle'))+90;

    oimg.rotate({
      animateTo: ang,
      duration: 500,
    });
    oimg.attr('angle',ang);
  });
}


function ajax(method, url, data, success, error, async, load) {
  if (!localStorage.getItem('token')) {
    if (url !== '/system/login') {
      router.push('/login');
      return;
    }
  }
  let req = {
    type: method,
    url: url,
    cache: false,
    withCredentials: true,
    contentType: 'application/json',
    dataType: 'JSON',
    beforeSend: function (xhr) {
      if (localStorage.getItem('token')) {
        xhr.setRequestHeader('Authorization', localStorage.getItem('token'));
      }
    },
    complete: function (xhr, data) {
      if (load !== false) {
        loading.close();
      }
      let now = parseInt(Date.now() / 1000);
      let exTime = isNaN(parseInt(localStorage.getItem('exTime'))) ? parseInt(Date.now() / 1000) : parseInt(localStorage.getItem('exTime'));
      // 获取相关Http Response header
      if (data === 'success') {
        if (now > exTime) {
          localStorage.setItem('exTime', now + (20 * 60));
          localStorage.setItem('token', xhr.getResponseHeader('token'));
        }
      }
    },
    success: function (r) {
      if (success) {
        success(r.data);
      }
    },
    error: function (e) {
      if (error) {
        error(e);
        return;
      }
      if (e.responseJSON && e.responseJSON.code === 403) {
        window.location.href = '/login?msg=登录已过期,请重新登录';
        return;
      }
      if (e.responseJSON && e.responseJSON.message) {
        ElementUI.Message.error(e.responseJSON.message);
        return;
      }
      if (url.indexOf('/system') > -1) {
        ElementUI.Message.error('系统管理服务繁忙，请稍后再进行尝试');
        return;
      }
      if (url.indexOf('/admin') > -1) {
        ElementUI.Message.error('后台管理服务繁忙，请稍后再进行尝试');
        return;
      }
      if (url.indexOf('/face/api') > -1) {
        ElementUI.Message.error('人脸检测服务繁忙，请稍后再进行尝试');
        return;
      }
      ElementUI.Message.error('服务器繁忙，请稍后再进行尝试');
    }
  };
  if (async === false || async === true) {
    req.async = async;
  }
  if (data) {
    req.data = JSON.stringify(data);
  }
  let loading;
  if (load !== false) {
    loading = ElementUI.Loading.service(Vue.prototype.loadingOptions);
  }
  $.ajax(req);
}

// 将时间戳（10位）转为 Y-M-D h:m:s格式的过滤器
Vue.filter('timestampTo', function (timestamp) {
  let date = new Date(timestamp * 1000),//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-',
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-',
    D = date.getDate() + ' ',
    h = date.getHours() + ':',
    m = date.getMinutes() + ':',
    s = date.getSeconds();
  return Y + M + D + h + m + s;
});

Vue.filter('numTo', function (value) {
  if (!value && value !== 0) {
    return '-';
  }
  // 截取当前数据到小数点后两位

  let realVal = parseFloat(value).toFixed(2);

  // num.toFixed(2)获取的是字符串

  return parseFloat(realVal)
});

Vue.filter('toFixed', function (value, num) {
  if (!value) {
    return '-';
  }
  // 截取当前数据到小数点后两位

  let realVal = parseFloat(value).toFixed(num);

  // num.toFixed(2)获取的是字符串

  return parseFloat(realVal)
});


router.beforeEach((to, from, next) => {
  if (to.matched.some(record => !record.meta.ignoreAuth)) {  // 判断该路由是否需要登录权限
    if (!localStorage.getItem('token')) {  // 判断当前的token是否存在
      next({path: '/login'});
      return;
    }
    next();
  } else {
    if (to.path === '/') {
      next({path: '/home'});
      return;
    }
    next();
  }
});
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {
    MyFrame
  },
  template: '<MyFrame/>'
});
