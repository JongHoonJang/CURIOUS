<template>
  <div>
    <nav class="nav-bar">
      <div class="nav-bar-logo">
        <img class="nav-bar-logo-img" src="@/assets/curius_logos/curius_logo_1.png" alt="" />
      </div>
      <div class="nav-bar-login">
        <div class="nav-laft">
          <p v-if="account.isLoggedIn" @click="profile()">마이페이지</p>
          <p v-if="account.isLoggedIn" @click="logout()">로그아웃</p>
        </div>
        <p v-if="!account.isLoggedIn" @click="login()">로그인</p>
      </div>
    </nav>
  </div>
</template>

<script>
import { ref } from "vue";
import { useAccountStore } from "@/stores/accounts";
import router from "@/router"
export default {
  setup() {
    const account = ref(useAccountStore());
    const login = () => {
      router.push({name: "LoginView"})
    };
    const logout = () => {
      
      account.value.logout()
    };
    const profile = () => {
      router.push({name: "ProfileView"})
    };
    return {
      account,
      login,
      logout,
      profile,
      
    };
  },
};
</script>

<style scoped>
@font-face {
    font-family: 'BMJUA_ttf';
    src: url(../assets/BMJUA_ttf.ttf);
    font-weight: normal;
    font-style: normal;
}
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
  font-family: "BMJUA_ttf";
  border-style: none;
  background: none;
  margin-right: 5vw;
}

.nav-bar-logo-img {
  height: 5vh;
  margin-left: 7vw;
}
.nav-laft{
  display: flex;
}

.nav-laft p {
  margin: 5px;
}
</style>
