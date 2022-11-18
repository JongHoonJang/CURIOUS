<template>
  <div class="meta-box">
    <div style="width: 960px; height: 600px;">
      <img class="keyset" src="@/assets/mainpage/image8.jpg" alt="">
      <div class="lds-facebook">                   
        <div>
        </div>
        <div>
        </div>
        <div>
        </div>
      </div>
      <canvas id="canvas" ref="canvasRef" style="width: 960px; height: 600px;" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import UnityWebgl from 'unity-webgl';

const canvasRef = ref(null);
let unityComponent = ref(null);
onMounted(() => {
  if (!unityComponent.value) {
    unityComponent.value = new UnityWebgl(canvasRef.value, {
      loaderUrl:
        'Build/curious.loader.js',
      dataUrl:
        'Build/curious.data.unityweb',
      frameworkUrl:
        'Build/curious.framework.js.unityweb',
      codeUrl:
        'Build/curious.wasm.unityweb',
    });
  }
});
</script>

<style>
.meta-box {
  position: absolute;
  top: calc(50% - 300px);
  left: calc(50% - 500px);
}
.lds-facebook {
  position: absolute;
}
#canvas {
  position: relative;
  z-index: 1;
}
.lds-facebook {
  display: inline-block;
  position: absolute;
  width: 64px;
  height: 64px;
  top: 70%;
  left: 47%;
}
.lds-facebook div {
  display: inline-block;
  position: absolute;
  left: 6px;
  width: 13px;
  background: #42b883;
  animation: lds-facebook 1.2s cubic-bezier(0, 0.5, 0.5, 1) infinite;
}
.lds-facebook div:nth-child(1) {
  left: 6px;
  animation-delay: -0.24s;
}
.lds-facebook div:nth-child(2) {
  left: 26px;
  animation-delay: -0.12s;
}
.lds-facebook div:nth-child(3) {
  left: 45px;
  animation-delay: 0;
}
@keyframes lds-facebook {
  0% {
    top: 6px;
    height: 51px;
  }
  50%, 100% {
    top: 19px;
    height: 26px;
  }
}
.keyset {
  position: absolute;
  top: calc(50% - 200px);
  left: calc(50% - 350px);
}
</style>