import { createApp } from "vue";
import App from "./App.vue";
import { createPinia } from "pinia";
import router from "./router";
import AOS from "aos";
import "aos/dist/aos.css";

createApp(App).use(createPinia()).use(router, AOS.init()).mount("#app");