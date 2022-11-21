<template>
  <div class="Page">
    <div class="Nav"></div>
    <div class="All">
      <div class="FirstImg">
        <p class="Text">작동법</p>
        <img src="@/assets/mainpage/Using.png" alt="" class="Image" />
      </div>
      <div class="SecondImg">
        <p class="Text">진행 방법</p>
        <HandleImage class="Image_1" />
      </div>
      <div class="ThirdImg">
        <p class="Text">시뮬레이션 정보</p>
        <MainImage class="Image_1" />
      </div>
    </div>
    <div class="Button">
      <button class="Join" @click="onClickRedirect()">입장하기</button>
    </div>
  </div>
</template>

<script>
import MainImage from "./MainImage.vue";
import HandleImage from "./HandleImage.vue";
import router from "@/router";
export default {
  components: { MainImage, HandleImage },
  methods: {
    onClickRedirect: function () {
      let route = router.resolve({ path: "/metaverse" });
      window.open(route.href);
    },
  },
};
</script>

<style>
@font-face {
  font-family: "BMJUA_ttf";
  src: url(../../assets/BMJUA_ttf.ttf);
  font-weight: normal;
  font-style: normal;
}
html {
  height: 100vh;
  width: 100vw;
}
body {
  margin: 0px;
}
.Nav {
  width: 100vw;
  height: 8vh;

  display: flex;
  justify-content: space-between;
}
.Image {
  width: 30vw;
  height: 50vh;
  border-radius: 20px;
  border-style: solid;
}
.Image_1 {
  width: 30vw;
  height: 50vh;
}
.All {
  height: 80vh;
  width: 100vw;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.Text {
  text-align: start;
  font-size: 25px;
  font-family: "BMJUA_ttf";
  overflow: hidden;
}
.Join {
  width: 14vw;
  height: 8vh;
  border-style: none;
  border-radius: 30px;
  font-family: "BMJUA_ttf";
  font-style: normal;
  box-shadow: 10px 15px 4px rgba(0, 0, 0, 0.25);
  font-size: 1.5em;
  overflow: hidden;
}
.Button {
  display: flex;
  justify-content: center;
}
</style>
