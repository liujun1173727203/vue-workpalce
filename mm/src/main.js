// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router'
import axios from 'axios'
import global from './components/global'
import VueWorker from 'vue-worker'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(VueWorker)
/* eslint-disable no-new */

//引用axios，将其挂到vue的原型上
Vue.prototype.$http = axios
axios.defaults.baseURL=""
Vue.prototype.$infoList = global.infoList
Vue.prototype.$global =global

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
