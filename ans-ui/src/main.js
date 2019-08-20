import '@/plugins/axios'
import '@/plugins/use'
import './plugins/element.js'
import "font-awesome/css/font-awesome.css";

import App from './App.vue'
import VCalendar from 'v-calendar';
import VCharts from 'v-charts'
import Vue from 'vue'
import api from '@/api'
import config from "@/config/defaultSettings";
import permissions from "@/common/permissions";
import router from '@/router'
import rules from "@/common/rules";
import store from '@/store'
import validator from "@/common/validator";
import Cookies from 'js-cookie'
import VXETable from 'vxe-table'
import 'vxe-table/lib/index.css'
import VXETablePluginElement from 'vxe-table-plugin-element'

Vue.use(VXETable)
VXETable.use(VXETablePluginElement)

Vue.use(VCharts)
Vue.use(VCalendar, {
  paneWidth: 800
});

Vue.config.productionTip = false
Vue.prototype.$config = config;
Vue.prototype.$rules = rules;
Vue.prototype.$api = api;
Vue.prototype.$validator = validator;
Vue.prototype.$cookie = Cookies;
Vue.prototype.$clone = obj => {
  return JSON.parse(JSON.stringify(obj));
};
Vue.prototype.$format = function (date, fmt) {
  var o = {
    "M+": date.getMonth() + 1, //月份
    "d+": date.getDate(), //日
    "h+": date.getHours(), //小时
    "m+": date.getMinutes(), //分
    "s+": date.getSeconds(), //秒
    "q+": Math.floor((date.getMonth() + 3) / 3), //季度
    S: date.getMilliseconds() //毫秒
  };
  if (/(y+)/.test(fmt))
    fmt = fmt.replace(
        RegExp.$1,
        (date.getFullYear() + "").substr(4 - RegExp.$1.length)
    );
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt))
      fmt = fmt.replace(
          RegExp.$1,
          RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length)
      );
  return fmt;
};

Vue.mixin({
  data() {
    return {
      installPermissions: permissions,
    }
  }
})

Vue.directive("permission", {
  bind: (el, binding) => {
    if (!store.getters.hasPermission(binding.value)) {
      if (el.parentNode) {
        el.parentNode.removeChild(el);
      } else {
        el.style.display = "none";
      }
    }
  }
});
router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.name) {
    document.title = to.meta.name;
  }
  next();
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')