<template>
  <header class="profile-container">
    <div class="profile-card">
      <div class="profile-box">
        <div class="title">
          <div v-if="!account.profile.imageSrc" class="img-box">
            <img src="@/assets/기본프로필.jpg" alt="">
          </div>
          <div v-if="account.profile.imageSrc" class="img-box">
            <img :src="`${account.profile.imageSrc}`" alt="">
          </div>
          <div class="text-box">
            <span class="title-size">안전 교육 이수증</span>
          </div>
        </div>
        <div>
          <div class="name-box">
            <div class="text-box">
              <span class="text-size">이름 : </span>
            </div>
            <div class="text-box">
              <span class="text-size">{{account.profile.nickname}}</span>
            </div>
          </div>
          <div class="name-box">
            <div class="text-box">
              <span class="text-size">email : </span>
            </div>
            <div class="text-box">
              <span class="text-size">{{account.profile.email}}</span>
            </div>
          </div>
        </div>
        <div class="footer-box">
          <img class="logo" src="@/assets/curius_logos/curius_logo_2.png" alt="">
          <img class="model" src="@/assets/medel1.png" alt="">
        </div>
        <div class="delete">
          <button class="delete_user" @click="deleteUser()">회원 탈퇴</button>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
import { useAccountStore } from "@/stores/accounts";
import Swal from "sweetalert2/src/sweetalert2.js";
export default {
  setup(){
    const account = useAccountStore();
    account.fetchProfile();
    const deleteUser = () => {
      Swal.fire({
        title: 'CURI@US',
        text: "회원탈퇴 하시겠습니까?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
      }).then((result) => {
        if (result.isConfirmed) {
          account.userDelete()
        }
      })
    }
    return {
      account,
      deleteUser
    };
  }
};
</script>

<style>
@font-face {
    font-family: 'BMJUA_ttf';
    src: url(../../assets/BMJUA_ttf.ttf);
    font-weight: normal;
    font-style: normal;
}
.profile-container {
  display: flex;
  justify-content: space-evenly;
  margin-top: 100px;

}
.profileimg-box {
  margin-top: 75px;
  width: 384px;
  height: 388px;
  background: #94C893;
  box-shadow: 10px 15px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.profile-card {
  width: 870px;
  height: 538px;
  background: #94C893;
  box-shadow: 10px 15px 4px rgba(0, 0, 0, 0.25);
  border-radius: 25px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.profile-box {
  width: 700px;
  height: 440px;
  text-align: start;
  display: flex;
  justify-content: space-between;
  flex-flow: column;
}
.img-box {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.53);
  box-shadow: 10px 15px 4px rgba(0, 0, 0, 0.25);
  display: flex;
  justify-content: center;
  align-items: center;
}
.img-box img {
  border-radius: 50%;
  width: 200px;
  height: 200px;
}
.model{
  width: 80px;
  height: 80px;
  border-radius: 50%;
  /* background: #94C893; */
  box-shadow: 10px 15px 4px rgba(0, 0, 0, 0.25);
  margin: auto;
}
.footer-box{
  display: flex;
}
.name-box {
  display: flex;
  margin: 15px 0px;
}
.text-size {
  font-size: 28px;
  font-family: 'BMJUA_ttf';
}
.title-size {
  font-size: 36px;
  font-style: bold;
  font-family: 'BMJUA_ttf';
}
.text-box{
  width: 350px;
}

.logo {
  width: 280px;
  height: 135px;
  margin: auto;
}
.title {
  display: flex;
  justify-content: space-between;
}
.delete{
  display: flex;
  justify-content: flex-end;
}
.delete_user{
  text-align: center;
  width: 80px;
  border-radius: 5px;
  background: red;
  color: white;
}
</style>