<template>
<div class="meta-box">
  <div style="width: 1000px; height: 600px; border: 1px solid #f00">
    <canvas id="canvas" ref="canvasRef" style="width: 100%; height: 100%" />
  </div>
</div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import UnityWebgl from 'unity-webgl';

const canvasRef = ref(null);
let unityComponent = null;

onMounted(() => {
  if (!unityComponent) {
    unityComponent = new UnityWebgl(canvasRef.value, {
      loaderUrl:
        'Build/unity_webgl.loader.js',
      dataUrl:
        'Build/unity_webgl.data.unityweb',
      frameworkUrl:
        'Build/unity_webgl.framework.js.unityweb',
      codeUrl:
        'Build/unity_webgl.wasm.unityweb',
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
  margin-top: 100px;
}
</style>