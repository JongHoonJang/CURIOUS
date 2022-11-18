<template>
  <div class="meta-box">
    <div style="width: 960px; height: 600px; border: 1px solid #f00">
      <canvas id="canvas" ref="canvasRef" width="1200" height="750" style="width: 960px; height: 600px;" />
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
  position: absolute;
  top: calc(50% - 300px);
  left: calc(50% - 500px);
}

</style>