import { createRouter, createWebHistory } from 'vue-router'
import RandingView from '@/views/randing/RandingView'
import LoginView from '@/views/account/LoginView'
import ProfileView from '@/views/account/ProfileView'
import MetaverseView from '@/views/metaverse/MetaverseView'
import MainView from '@/views/main/MainView'

const routes = [
  {
    path: '/',
    name: 'RandingView',
    component: RandingView
  },
  {
    path: '/login',
    name: 'LoginView',
    component: LoginView
  },
  {
    path: '/profile',
    name: 'ProfileView',
    component: ProfileView
  },
  {
    path: '/metaverse',
    name: 'MetaverseView',
    component: MetaverseView
  },
  {
    path: '/main',
    name: 'MainView',
    component: MainView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
