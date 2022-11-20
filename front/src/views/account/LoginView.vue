<template>
  <header class="login-btn">
    <a :href="`${naver}`">
      <img src="https://static.nid.naver.com/oauth/big_g.PNG?version=js-2.0.1">
    </a>
    <a :href="`${kakao}`">
      <img src="@/assets/kakao_login_large_narrow.png">
    </a>
    <a :href="`${google}`">
      <img src="@/assets/btn_google_signin_light_normal_web.png">
    </a>
  </header>
</template>

<script>
export default {
  setup(){
    const naver = process.env.VUE_APP_NAVER_LOGIN;
    const kakao = process.env.VUE_APP_KAKAO_LOGIN;
    const google = process.env.VUE_APP_GOOGLE_LOGIN;
    return {
      naver,
      kakao,
      google
    }
  }
};
</script>

<style>
.login-btn {
  height: 800px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-flow: column;
}
.login-btn img{
  width: 191px;
  height: 46px;
  margin: 10px;
}
</style>