import { createApp } from 'vue';
import App from './App.vue';
import { createPinia } from 'pinia';
import router from './router';
import AOS from 'aos';
import 'aos/dist/aos.css';
import axios from 'axios';
import VueCookies from 'vue3-cookies';
axios.defaults.withCredentials = true;

createApp(App).use(createPinia()).use(VueCookies, { secure: true }).use(router, AOS.init(), axios).mount('#app');
