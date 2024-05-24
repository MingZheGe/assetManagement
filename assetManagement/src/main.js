import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import axios from 'axios'
 import * as echarts from 'echarts'
 import './assets/icons/iconfont.js'
 import { DatePicker } from 'ant-design-vue'
 import 'ant-design-vue/dist/antd.css';
 import { Button } from 'ant-design-vue';
 import 'xe-utils'
      import VXETable from 'vxe-table'
      import 'vxe-table/lib/style.css'
      import htmlToPdf from '@/components/utils/htmlToPdf'

      // 设置反向代理，前端请求默认发送到 http://localhost:8443/api
axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8443/api'
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios = axios
Vue.config.productionTip = false
 Vue.use(Button);
 Vue.use(VXETable)
Vue.prototype.$axios = axios
Vue.prototype.$echarts = echarts

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(htmlToPdf)
Vue.use(DatePicker);
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')