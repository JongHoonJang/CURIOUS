<template>
  <header class="profile-container">
    <div class="profileimg-box">
      <div class="img-box">
        <img src="@/assets/기본프로필.jpg" alt="">
      </div>
    </div>
    <div class="profile-card">
      <div class="profile-box">
        <div class="name-box">
          <div class="text-box">
            <span class="text-size">이름 : </span>
          </div>
          <div class="text-box">
            <span class="text-size">SSAFY</span>
          </div>
        </div>
        <div class="name-box">
          <div class="text-box">
            <span class="text-size">닉네임 : </span>
          </div>
          <div class="text-box">
            <span class="text-size">SSAFY</span>
          </div>
        </div>
        <p class="text-size">얻은 트로피</p>
        <div class="medal-list">
          <div class="medal-box">
            <div class="medel"><img src="@/assets/medel1.png" alt=""></div>
            <p>달성조건 1</p>
          </div>
          <div class="medal-box">
            <div class="medel"><img src="@/assets/medel1.png" alt=""></div>
            <p>달성조건 1</p>
          </div>
          <div class="medal-box">
            <div class="medel"></div>
            <p>달성조건 1</p>
          </div>
          <div class="medal-box">
            <div class="medel"></div>
            <p>달성조건 1</p>
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
export default {

}
</script>

<style>
.profile-container {
  display: flex;
  justify-content: space-evenly;

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
  height: 338px;
  text-align: start;
  display: flex;
  flex-flow: column;
}
.img-box {
  width: 330px;
  height: 334px;
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
.medel{
  width: 150px;
  height: 150px;
  border-radius: 50%;
  background: #EDEDED;
  box-shadow: 10px 15px 4px rgba(0, 0, 0, 0.25);
}
.medel img {
  width: 150px;
  height: 150px;
  border-radius: 50%;
}
.medal-list {
  text-align: center;
  display: flex;
  justify-content: space-between;
}

.name-box {
  display: flex;
  margin: 15px 0px;
}
.text-size {
  font-size: 28px;
}

.text-box{
  width: 350px;
}
</style>