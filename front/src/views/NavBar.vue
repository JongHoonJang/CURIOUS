<template>
  <div>
    <header class="nav-bar">
      <div class="nav-bar-logo">
        <img class="nav-bar-logo-img" src="@/assets/curius_logos/curius_logo_1.png" alt="" />
      </div>
      <div class="nav-bar-login">
        <p v-if="account.isLoggedIn" @click="logout()">로그아웃</p>
        <p v-if="!account.isLoggedIn" @click="login()">로그인</p>
      </div>
    </header>
  </div>
</template>

<script>
import { ref } from "vue";
import { useAccountStore } from "@/stores/accounts";
import router from "@/router"
export default {
  setup() {
    const account = ref(useAccountStore()); // 로그인 기능 구현되면 연결하기
    const login = () => {
      router.push({name: "LoginView"})
    };

    const logout = () => {
      account.value.logout()
    };

    return {

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
