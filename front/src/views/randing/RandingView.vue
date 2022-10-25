<template>
  <div>
    <div class="main">
      <img class="main-img" src="@/assets/landing/dumpMain.png" alt="" />
    </div>
    <div class="sub">
      <div class="sub1 part">
        <img data-aos="fade-down-right" class="sub1-img" src="@/assets/landing/landingSub1.png" alt="" />
        <div class="text">
          메타버스
          <br />
          안전 교육
        </div>
      </div>
      <div class="sub2 part">
        <img data-aos="fade-down-left" class="sub2-img" src="@/assets/landing/landingSub2.png" alt="" />
        <div class="text">
          퀴즈로
          <br />
          학습과 체크
        </div>
      </div>
      <div class="sub3 part">
        <img data-aos="zoom-in" class="sub3-img" src="@/assets/landing/landingSub3.png" alt="" />
        <div class="text">멋진 추억</div>
      </div>
      <div class="wrap">
        <button class="button" @click="start()">시작하기</button>
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
export default {
  setup() {
    const start = () => {
      router.push({ name: "MainView" });
    };

    return {
      start,
    };
  },
};
</script>

<style>
.main {
  margin-top: 8vh;
}

.main-img {
  height: 90vh;
  width: 100vw;
}

.text {
  font-family: "BM HANNA_TTF";
  font-style: normal;
  font-weight: 400;
  font-size: 4rem;
}
.part {
  width: 100vw;
  height: 60vh;
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.sub1 {
  flex-direction: row;
}

.sub1-img {
  height: 100%;
}

.sub2 {
  flex-direction: row-reverse;
}

.sub2-img {
  height: 100%;
}

.sub3-img {
  height: 100%;
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
