<template>
  <div>
    <div
      class="landingLine"
      :style="{ backgroundImage: 'url(' + require(`@/assets/landing/landing_line.png`) + ')', 'background-repeat': 'no-repeat', 'background-size': 'contain', 'background-position': 'center' }"
    >
      <!-- 1번 메인 페이지, 애니메이션 예정 -->
      <section class="first">
        <RandingSlide class="first-img" />
      </section>
      <!-- 2번 메인 페이지 -->
      <section class="second">
        <div class="second-one-img text" data-aos="fade-up">
          <img src="@/assets/landing/landingSubwayFire.png" alt="" />
          <br />
          지하철 화재상황
        </div>
        <div class="second-two-img text" data-aos="fade-down">
          <img src="@/assets/landing/landingExit.png" alt="" />
          <br />
          제한시간은 5분!
        </div>
      </section>
      <!-- 3번 메인 페이지 -->
      <section class="third">
        <div class="third-one-img" data-aos="fade-up-right">
          <img src="@/assets/landing/landingTrainFire.png" alt="" />
        </div>
        <div class="third-two-img text" data-aos="zoom-in-up">
          <img src="@/assets/landing/landingStudy.png" alt="" />
          <br />
          화재 안전 교육도 함께
        </div>
        <div class="third-one-img text" data-aos="fade-up-left">
          <img src="@/assets/landing/landingFireExtinguisher.png" alt="" />
        </div>
      </section>
      <!-- 4번 메인 페이지, 시작하기 -->
      <section class="fourth">
        <div class="fourth-two-img text" data-aos="fade-down-right">
          <img src="@/assets/landing/landingSaffy.png" alt="" />
          <br />
          CURI@US
        </div>
        <div class="fourth-one-img text" data-aos="fade-down-left">
          <img src="@/assets/landing/landingStart.png" alt="" />
          <br />
          시작하기
        </div>
      </section>
      <div class="wrap">
        <button class="button" @click="start()">시작하기</button>
      </div>
    </div>
  </div>
</template>

<script>
import { useAccountStore } from '@/stores/accounts';
import router from '@/router';
import { ref } from 'vue';
import RandingSlide from '@/views/randing/RandingSlide.vue';

export default {
  components: { RandingSlide },
  setup() {
    const account = ref(useAccountStore());
    const start = () => {
      if (account.value.isLoggedIn) {
        router.push({ name: 'MainView' });
      } else {
        router.push({ name: 'LoginView' });
      }
    };

    return {
      start,
    };
  },
};
</script>

<style scoped>
@font-face {
  font-family: 'BMJUA_ttf';
  src: url(../../assets/BMJUA_ttf.ttf);
  font-weight: normal;
  font-style: normal;
}

img {
  height: 25vh;
}
section {
  height: 100vh;
  width: 100vw;
  display: flex;
  flex-direction: column;
}

section.first {
  justify-items: center;
}

section.second {
  align-items: stretch;
}

.second-one-img {
  margin-left: 10vw;
  align-self: flex-start;
}

.second-two-img {
  margin-right: 10vw;
  align-self: flex-end;
}

.third-one-img {
  margin-left: 15vw;
  align-self: flex-start;
}

.third-two-img {
  margin-right: 15vw;
  align-self: flex-end;
}

.fourth-one-img {
  margin-left: 15vw;
  align-self: flex-start;
}

.fourth-two-img {
  margin-right: 15vw;
  align-self: flex-end;
}

.third {
  align-items: stretch;
}

.fourth {
  align-items: stretch;
}

.text {
  font-family: 'BMJUA_ttf';
  font-style: normal;
  font-weight: 400;
  font-size: 3rem;
  text-align: center;
}

.fourth-text {
  margin-right: 10vw;
}

.first-img {
  align-self: center;
  margin-top: 15vh;
  height: 100vh;
  width: 100vw;
}

.second-img {
  margin-top: 20vh;
  height: 60vh;
}

.third-img {
  height: 60vh;
}

.fourth-img {
  height: 70vh;
}

/* 버튼 CSS */
.wrap {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.button {
  width: 180px;
  height: 60px;
  font-family: 'BMJUA_ttf';
  font-size: 20px;
  text-transform: uppercase;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: #000;
  background-color: #fff;
  border: none;
  border-radius: 45px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
  margin-bottom: 10vh;
}

.button:hover {
  background-color: #2ee59d;
  box-shadow: 0px 15px 20px rgba(46, 229, 157, 0.4);
  color: #fff;
  transform: translateY(-7px);
}
</style>
