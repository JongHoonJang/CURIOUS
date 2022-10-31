import { createApp } from "vue";
import App from "./App.vue";
import { createPinia } from "pinia";
import router from "./router";
import AOS from "aos";
import "aos/dist/aos.css";
import axios from "axios"
axios.defaults.headers.common['Content-Type'] = 'application/x-www-form-urlencoded'
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

createApp(App).use(createPinia()).use(router, AOS.init(),axios).mount("#app");