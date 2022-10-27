<template>
  <header class="login-btn">
    <div id="naver_id_login" @click="naver_login">
      <img src="https://static.nid.naver.com/oauth/big_g.PNG?version=js-2.0.1">
    </div>
    <img @click="kakao_login" src="@/assets/kakao_login_large_narrow.png" alt="">
    <img @click="google_login" src="@/assets/btn_google_signin_light_normal_web.png" alt="">
    <div id="my-signin2" style="display: none"></div>
  </header>
</template>

<script>
export default {
  setup(){
    const naver_login = () => {
      var naver_id_login = new window.naver_id_login(process.env.VUE_APP_NAVER_CLIENTID,process.env.VUE_APP_NAVER_URL);
      var state = naver_id_login.getUniqState();
      naver_id_login.setButton("green", 3, 46);
      naver_id_login.setDomain("http:localhost:8080/login");
      naver_id_login.setState(state);
      naver_id_login.setPopup();
      naver_id_login.init_naver_id_login();
    }
    const kakao_login = () => {
      window.Kakao.Auth.login({
        scope: 'profile_nickname, account_email',
        success: getKakaoAccount
      })
    }
    const getKakaoAccount = () => {
      window.Kakao.API.request({
        url: process.env.VUE_APP_KAKAO_URL,
        success: res => {
          const kakao_account = res.kakao_account;
          const nickname = kakao_account.nickname;
          const email = kakao_account.email;
          console.log(nickname);
          console.log(email);
          alert("로그인 성공!");
        },
        fail : error => {
          console.log(error)
        }
      })
    }
    const google_login = () => {
      var self = this;

      window.gapi.signin2.render('my-signin2', {
        scope: 'profile email',
        width: 240,
        height: 50,
        longtitle: true,
        theme: 'dark',
        onsuccess:  function GoogleLoginSuccess (googleUser) {
                    const googleEmail =  googleUser.getBasicProfile().getEmail();
                    if (googleEmail !== 'undefined') {
                      console.log(googleEmail);
                    }
                  },
        onfailure:  function GoogleLoginFailure(error){
                      console.log(error);
                    },
      });

      setTimeout(function () {
        if (!self.googleLoginCheck) {
          const auth = window.gapi.auth2.getAuthInstance();
          auth.isSignedIn.get();
          document.querySelector('.abcRioButton').click();
        }
      }, 1500)
    }
    return {
      naver_login,
      kakao_login,
      google_login,

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