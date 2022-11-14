<template>
  <div class="meta-box">
    <div style="width: 1000px; height: 600px; border: 1px solid #f00">
      <canvas id="canvas" ref="canvasRef" style="width: 100%; height: 100%" />
    </div>
  </div>
  <button class="red-btn" @click="close()">나가기</button>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import UnityWebgl from 'unity-webgl';

const canvasRef = ref(null);
let unityComponent = null;
const close = () => {
  // self.opener = self;
  // window.close();
  window.open('','_self').close(); 
}
onMounted(() => {
  if (!unityComponent) {
    unityComponent = new UnityWebgl(canvasRef.value, {
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
onBeforeUnmount(() => {
  if (unityComponent) {
    unityComponent.destroy();
    unityComponent = null;
  }
});
</script>

<style>
.meta-box {
  display: flex;
  justify-content: center;
  margin-top: 50px;
}

.red-btn {
  width: 92px;
  height: 36px;
  margin-left: 16px;
  background: radial-gradient(95% 60% at 50% 75%, #d60000 0%, #ff2020 100%);
  border: 1px solid #fd5454;
  box-shadow: 0px 8px 20px -8px #ff1111, inset 0px 1px 8px -4px #FFFFFF;
  border-radius: 12px;
  color: white;
  font-size: 16px;
  line-height: 22px;
  font-weight: 600;
  letter-spacing: .02em;
  transition: all .2s ease;
  -webkit-tap-highlight-color: rgba(255,255,255,0);
}

.red-btn:hover {
  transform: scale(1.2);
}
</style>