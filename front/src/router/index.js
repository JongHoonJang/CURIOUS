import { createRouter, createWebHistory } from 'vue-router'
import RandingView from '@/views/randing/RandingView'
import LoginView from '@/views/randing/LoginView'
import ProfileView from '@/views/randing/ProfileView'
import MetaverseView from '@/views/randing/MetaverseView'
import MainView from '@/views/randing/MainView'

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
