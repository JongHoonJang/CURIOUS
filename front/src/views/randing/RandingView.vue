<template>
  <div>
    <div
      class="landingLine"
      :style="{ backgroundImage: 'url(' + require(`@/assets/landing/landing_line.png`) + ')', 'background-repeat': 'no-repeat', 'background-size': 'contain', 'background-position': 'center' }"
    >
      <!-- 1번 메인 페이지, 애니메이션 예정 -->
      <section class="first">
        <img class="first-img" src="@/assets/landing/dump_main.png" alt="" />
      </section>
      <!-- 2번 메인 페이지 -->
      <section class="second">
        <img data-aos="fade-down-right" class="second-img" src="@/assets/landing/landingSub1.png" alt="" />
        <div class="text" data-aos="zoom-in">
          메타버스
          <br />
          안전 교육
        </div>
      </section>
      <!-- 3번 메인 페이지 -->
      <section class="third">
        <img data-aos="fade-down-left" class="third-img" src="@/assets/landing/landingSub2.png" alt="" />
        <div class="text" data-aos="zoom-in">
          퀴즈로
          <br />
          학습과 체크
        </div>
      </section>
      <!-- 4번 메인 페이지, 시작하기 -->
      <section class="fourth">
        <img data-aos="zoom-in" class="fourth-img" src="@/assets/landing/landingSub3.png" alt="" />
        <div class="text fourth-text" data-aos="zoom-in">멋진 추억</div>
      </section>
      <div class="wrap">
        <button class="button" @click="start()">시작하기</button>
      </div>
    </div>
  </div>
</template>

<script>
import { useAccountStore } from "@/stores/accounts";
import router from "@/router"
import { ref } from 'vue'
export default {
  setup() {
    const account = ref(useAccountStore());
    const start = () => {
      if(account.value.isLoggedIn){
        router.push({ name: "MainView" });
      }else {
        router.push({name: "LoginView"})
      }
    };

    return {
      start,
    };
  },
};
</script>

<style scoped>
section {
  height: 100vh;
  width: 100vw;
  /* border-top: 2px dashed rgba(0, 0, 0, 0.5); */
  display: flex;
  align-items: center;
  justify-content: space-around;
}

.third {
  flex-direction: row-reverse;
}

.fourth {
  justify-content: space-evenly;
}

.text {
  font-family: "BM HANNA_TTF";
  font-style: normal;
  font-weight: 400;
  font-size: 4rem;
}

.fourth-text {
  margin-right: 10vw;
}

.first-img {
  margin-top: 5vh;
  height: 90vh;
}

.second-img {
  margin-top: 10vh;
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
  width: 140px;
  height: 45px;
  font-family: "Roboto", sans-serif;
  font-size: 11px;
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
}

.button:hover {
  background-color: #2ee59d;
  box-shadow: 0px 15px 20px rgba(46, 229, 157, 0.4);
  color: #fff;
  transform: translateY(-7px);
}
</style>
