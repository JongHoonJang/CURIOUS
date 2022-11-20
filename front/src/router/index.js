import { createRouter, createWebHistory } from 'vue-router';
import RandingView from '@/views/randing/RandingView';
import LoginView from '@/views/account/LoginView';
import ProfileView from '@/views/account/ProfileView';
import MetaverseView from '@/views/metaverse/MetaverseView';
import MainView2 from '@/views/main/MainView2';
import NaverLogin from '@/views/account/NaverLogin';
import KakaoLogin from '@/views/account/KakaoLogin';
import GoogleLogin from '@/views/account/GoogleLogin';
const routes = [
  {
    path: '/',
    name: 'RandingView',
    component: RandingView
  },
  {
    path: '/users/naver/callback',
    name: 'NaverLogin',
    component: NaverLogin
  },
  {
    path: '/users/kakao/callback',
    name: 'KakaoLogin',
    component: KakaoLogin
  },
  {
    path: '/users/google/callback',
    name: 'GoogleLogin',
    component: GoogleLogin
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
    name: 'MainView2',
    component: MainView2
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
// 로우터 가드
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  // 토근이 존재할때 로그인화면으로 가려고하면 메인으로 이동
  if(to.name === 'LoginView' || to.name === 'GoogleLogin' || to.name === 'KakaoLogin'|| to.name === 'NaverLogin' ) {
    if(token) {
      next({ name:'MainView' });
    }
  }
  // 토큰이 없을때 로그인 페이지로 이동
  else if (to.name === 'MainView' || to.name === 'ProfileView' || to.name === 'MetaverseView') {
    if(!token) {
      next({ name:'LoginView'});
    }
  }
  next();
})

export default router;
