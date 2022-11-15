<template>
  <div>
    <nav class="nav-bar">
      <div class="nav-bar-logo">
        <img @click="main()" class="nav-bar-logo-img" src="@/assets/curius_logos/curius_logo_1.png" alt="" />
      </div>
      <div class="nav-bar-login">
        <div class="nav-laft">
          <a href="/profile" v-if="account.isLoggedIn">마이페이지</a>
          <a v-if="account.isLoggedIn" @click="logout()">로그아웃</a>
        </div>
        <a v-if="!account.isLoggedIn" @click="login()">로그인</a>
      </div>
    </nav>
  </div>
</template>

<script>
import { ref } from "vue";
import { useAccountStore } from "@/stores/accounts";
import router from "@/router";
import Swal from "sweetalert2/src/sweetalert2.js";
export default {
  setup() {
    const account = ref(useAccountStore());
    const main = () => {
      if(account.value.isLoggedIn){
        router.push({name:"MainView"})
      }else{
        router.push({name:"RandingView"})
      }
    };
    const login = () => {
      router.push({name: "LoginView"})
    };
    const logout = () => {
      Swal.fire({
        title: 'CURI@US',
        text: "로그아웃 하시겠습니까?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
      }).then((result) => {
        if (result.isConfirmed) {
          account.value.logout()
        }
      })
    };
    return {
      account,
      login,
      logout,
      main
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

.nav-laft a {
  margin: 5px;
  text-decoration: none;
  color: black;
}
.nav-laft a:hover {
  color: gold;
}
</style>
