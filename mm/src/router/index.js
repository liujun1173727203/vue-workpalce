import Vue from 'vue'
import Router from 'vue-router'
import userinfo from '@/components/userinfo'
import login from '@/components/login'
import meeting from '@/components/meeting'
import video from '@/components/video'
import fileAction from '@/components/fileAction'
import audiopage from '@/components/audiopage'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: login
    },
    {
      path: '/userinfo',
      component: userinfo
    },
    {
      path:'/meeting',
      component:meeting
    },
    {
      path:'/video',
      component:video
    },
    {
      path:'/audios',
      component:audiopage
    },
    {
      path:'/file',
      component:fileAction
    },
  ]
})
