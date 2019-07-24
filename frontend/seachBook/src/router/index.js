import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'loginForm',
      component: Login
    },
    {
      path: '/comLogin',
      name: 'comLogin',
      component: () => import('../components/searchBookMain.vue'),
      props: true
    },
    {
      path: '/join',
      name: 'join',
      component: () => import('../components/JoinUser.vue')
    },
    {
      path: '/searchBookDetail',
      name: 'searchBookDetail',
      component: () => import('../components/searchBookDetail.vue')
    }

  ]
})
