<template>
  <header class="login-btn">
    <div id="naver_id_login" @click="naver_login">
      <img src="https://static.nid.naver.com/oauth/big_g.PNG?version=js-2.0.1">
    </div>
    <div id="kakao_id_login" @click="account.kakaoLogin">
      <img src="@/assets/kakao_login_large_narrow.png" alt="">
    </div>
    <div @click="google_login">
      <img src="@/assets/btn_google_signin_light_normal_web.png" alt="">
    </div>
    <a :href="`${google}`" >
      <img src="@/assets/btn_google_signin_light_normal_web.png" alt="">
    </a>
  </header>
</template>

<script>
import { useAccountStore } from "@/stores/accounts";
// import router from '@/router';
export default {
  setup(){
    const google = process.env.VUE_APP_GOOGLE_LOGIN
    const account = useAccountStore()
    const naver_login = () => {
      var naver_id_login = new window.naver_id_login(process.env.VUE_APP_NAVER_CLIENTID, process.env.VUE_APP_NAVER_URL);
      var state = naver_id_login.getUniqState();
      naver_id_login.setButton("green", 3, 46);
      naver_id_login.setState(state);
      naver_id_login.setPopup();
      naver_id_login.init_naver_id_login();

    }
    const google_login = () => {
      var xmlHttp = new XMLHttpRequest()

      xmlHttp.onreadystatechange = function () {
        if (this.status == 200 && this.readyState == this.DONE) {
          console.log(xmlHttp)
        }
      }

      xmlHttp.open('GET', process.env.VUE_APP_GOOGLE_LOGIN, true)

      xmlHttp.send()
    }


    // const google_login = () => {
    //   window.gapi.signin2.render('my-signin2', {
    //     scope: 'profile email',
    //     width: 240,
    //     height: 50,
    //     longtitle: true,
    //     theme: 'dark',
    //     onsuccess: onSuccess,
    //     onfailure: onFailure,
    //   });
    //   // setTimeout(function () {
    //   //   document.querySelector('.abcRioButton').click();
    //   // }, 1500)
    // }
    // const onSuccess = async(googleUser) => {

    //   const user_join_type = "g"
    //   const googleEmail = googleUser.getBasicProfile().pu
    //   console.log(googleEmail)

    //   const res = await fetch(process.env.VUE_APP_GOOGLE_URL, {
    //     method: "get",
    //     headers: {
    //       "Content-Type": "application/json",
    //     },
    //     body: JSON.stringify({
    //       email: `${googleEmail}`,
    //       user_join_type: user_join_type
    //     }),
    //   })
    //   console.log(await res.data)
    //   const data = await res.json();
    //   console.log(data)
    //   this.checkSnSLogin(data, googleEmail,user_join_type);
    // }
    // //구글 로그인 콜백함수 (실패)
    // const onFailure = (error) => {
    //   // eslint-disable-next-line
    //   console.log(error);
    // }
    return {
      naver_login,
      google_login,
      // googleLogin,
      account,
      google,
    }
  }
}
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