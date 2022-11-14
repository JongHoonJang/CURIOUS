import { defineStore } from "pinia";
import axios from 'axios';
import router from "@/router";
import api from '@/api/api';

export const useAccountStore = defineStore("accounts", {
  state: () => ({
    accesstoken: localStorage.getItem('token') || '' ,
    profile: {},
  }),
  getters: {
    isLoggedIn: state => !!state.accesstoken,
    authHeader: state => ({ Authorization: `Bearer ${state.accesstoken}`}),

  },
  actions: {
    saveToken(token) {
      this.accesstoken = token;
      localStorage.setItem('token', token);
    },
    //token값 삭제
    removeToken() {
      this.accesstoken = '';
      localStorage.setItem('token', '');
    },
    fetchLogin(type, code) {
      axios.get(api.accounts.login(type),{
        params:{
          code:code
        },
        headers:{
          'Access-Control-Allow-Origin': '*'
        }
      })
      .then(res => {
        console.log(res.data.data);
        this.saveToken(res.data.data);
        router.push({name:'MainView'});
      })
      .catch(err => {
        console.error(err.response)
      })
    },
    logout() {
      axios.get(api.accounts.logout(),{
        withCredentials: true,
      })
      .then(() => {
        this.removeToken();
        alert("로그아웃되었습니다.");
        router.push({name:'RandingView'});
      })
    },
    fetchProfile() {
      axios.get(api.accounts.profile(),{
        headers: this.authHeader
      })
      .then(res => {
        this.profile = res.data.data;
      })
      .catch(error => {
        console.log(error.response);
        if (error.response.status==401){
          this.tokenReissue()
        }
      })
    },
    tokenReissue() {
      axios.get(api.accounts.reissue(),{
        withCredentials: true,
        headers: this.authHeader,
      })
      .then(res => {
        this.saveToken(res.data.data)
        alert("재발급!")
      })
    }
    

  }
});