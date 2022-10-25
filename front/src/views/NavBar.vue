<template>
  <div>
    <header class="nav-bar">
      <div class="nav-bar-logo">
        <img class="nav-bar-logo-img" src="@/assets/curius_logos/curius_logo_1.png" alt="" />
      </div>
      <div class="nav-bar-login">
        <p v-if="isLogin" @click="login()">로그인</p>
        <p v-else @click="logout()">로그아웃</p>
      </div>
    </header>
  </div>
</template>

<script>
import { ref } from "vue";
import { useAccountStore } from "@/stores/accounts";

export default {
  name: "navBar",
  setup() {
    const isLogin = ref(false);
    const account = useAccountStore(); // 로그인 기능 구현되면 연결하기

    const login = () => {
      isLogin.value = false;
    };

    const logout = () => {
      isLogin.value = true;
    };

    return {
      isLogin,
      account,
      login,
      logout,
    };
  },
};
</script>

<style scoped>
.nav-bar {
  position: fixed;
  top: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100vw;
  height: 8vh;
}

.nav-bar-login {
  border-style: none;
  background: none;
  margin-right: 5vw;
}

.nav-bar-logo-img {
  height: 5vh;
  margin-left: 7vw;
}
</style>
