<template>
  <div class='Page'>
    <div class='Nav'>

    </div>
    <div class='All'>
      <div class='Title'>
        <h1 class='TitleText'>지하철 사고</h1>
        <MainImage class='Image'/>
      </div>
      <div class='Detail'>
        <div class='DetailBox'>
          <!-- <button class='Create'>방 생성하기</button> -->
          <form class='InputArea'>
            <!-- <input type='text' class='Input' placeholder='코드를 입력하세요.' />
            <button class='Join'>입장하기</button> -->
            <button class='Join' @click='onClickRedirect()'>입장하기</button>
            
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MainImage from './MainImage.vue';
import router from '@/router';
export default {
  components: { MainImage },
  methods: {
    onClickRedirect: function () {   
        let route = router.resolve({ path: '/metaverse'});
        window.open(route.href);   
      }
  },


}
</script>

<style>
@font-face {
    font-family: 'BMJUA_ttf';
    src: url(../../assets/BMJUA_ttf.ttf);
    font-weight: normal;
    font-style: normal;
}
html {
  height: 100vh;
  width: 100vw;
}
body{
  margin: 0px;
}
.Nav {
  width: 100vw;
  height: 8vh;

  display: flex;
  justify-content: space-between;
}
.Image {
  width: 41.5vw;
  height: 50vh;
}
.All {
  height: 92vh;
  width: 100vw;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.Title {
  width: 50vw;
  height: 70vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.TitleText {
  text-align: start;
  width: 41.5vw;
  margin-left: 6vw;
  font-family: 'BMJUA_ttf';
  overflow: hidden;
}
.Detail {
  width: 50vw;
  height: 80vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
.DetailBox {
  width: 40vw;
  height: 65vh;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 40px;

  background-color: #94C893;
}
.Create {
  position: absolute;
  width: 35vw;
  height: 15vh;
  border-style: none;
  box-shadow: 10px 15px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
  top: 10vh;
  font-family: 'BMJUA_ttf';
  font-style: normal;
  font-size: 3rem;
  overflow: hidden;
}
.Input {
  width: 35vw;
  height: 15vh;
  border-style: none;
  box-shadow: 10px 15px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
  position: relative;
  text-align: center;
  font-family: 'BMJUA_ttf';
  font-style: normal;
  font-size: 3rem;
  display: flex;
  align-items: center;
  text-align: center;
  margin-bottom: 2vh;
  overflow: hidden;
}
.Join {
  width: 14vw;
  height: 8vh;
  border-style: none;
  border-radius: 30px;
  font-family: 'BMJUA_ttf';
  font-style: normal;
  box-shadow: 10px 15px 4px rgba(0, 0, 0, 0.25);
  font-size: 1.5em;
  overflow: hidden;
}
.InputArea {
  position: absolute;
  bottom: 5vh;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

</style>
