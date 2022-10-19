import { defineStore } from "pinia";
// import axios from 'axios'
// import api from "@/api/api"
// import router from "@/router"

export const useAccountStore = defineStore("accounts", {
  state: () => ({
    token: localStorage.getItem('token') || '' ,
    profile: {},
  }),
  getters: {
    isLoggedIn: state => !!state.token,
    authHeader: state => ({ Authorization: `Bearer ${state.token}`}),

  },
  actions: {
    
  }
})