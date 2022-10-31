import { defineStore } from "pinia";
import axios from 'axios'
import router from "@/router"

export const useAccountStore = defineStore("accounts", {
  state: () => ({
    accesstoken: localStorage.getItem('access') || '' ,
    refreshtoken: localStorage.getItem('refresh') || '',
    profile: {},
  }),
  getters: {
    isLoggedIn: state => !!state.refreshtoken,
    authHeader: state => ({ Authorization: `Bearer ${state.accesstoken}`}),

  },
  actions: {
    naverLogin() {
      router.push({url:process.env.VUE_APP_NAVER_LOGIN})
      .then(res => {
        console.log(res)
        // localStorage.setItem('access', res.data.accessToken)
        // localStorage.setItem('refresh', res.data.refreshToken)
        // router.push({name:"MainView"})
      })
    },
    kakaoLogin() {
      axios.get(process.env.VUE_APP_KAKAO_LOGIN, {
      }).then(res => {
        console.log(res.data)
      }).catch(err => {
      console.log(err.response);
      });
    },
    googleLogin() {
      axios.get("/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile&response_type=code&client_id=551951054745-nl3ns2eqfi9h85bsrlc6di4hudkm1vlb.apps.googleusercontent.com&redirect_uri=http://localhost:8080/api/users/google/callback", {
        headers:{
          "Content-Type": "Application/json",
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS",
        }
      })
        .then(res => {
          console.log(res.data)
        })
        .catch(err => {
          console.log(err.response);
        });
    },
  }
})