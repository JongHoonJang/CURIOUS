import { defineStore } from "pinia";
import axios from 'axios'
import router from "@/router"
import api from '@/api/api'
export const useAccountStore = defineStore("accounts", {
  state: () => ({
    accesstoken: localStorage.getItem('token') || '' ,
    refreshtoken: localStorage.getItem('refresh') || '',
    profile: {},
  }),
  getters: {
    isLoggedIn: state => !!state.accesstoken,
    authHeader: state => ({ Authorization: `Bearer ${state.accesstoken}`}),

  },
  actions: {
    saveToken(token) {
      this.accesstoken = token
      localStorage.setItem('token', token)
    },
    //token값 삭제
    removeToken() {
      this.accesstoken = ''
      localStorage.setItem('token', '')
    },
    fetchLogin(type, code) {
      axios.get(api.accounts.login(type),{
        params:{
          code:code
        }
      })
      .then(res => {
        console.log(res.data.data)
        this.saveToken(res.data.data.accessToken)
        router.push({name:'MainView'})
      })
    },
    fetchProfile() {
      axios.get(api.accounts.profile(),{
        headers: this.authHeader
      })
      .then(res => {
        console.log(res.data)
        this.profile = res.data
      })
    }
  }
})