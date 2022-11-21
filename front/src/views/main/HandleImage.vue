<template>
  <carousel :autoplay="5000" :wrap-around="true" :itemsToShow="0.8">
    <slide v-for="slide in slides" :key="slide">
      <img class="slide-img" :src="require(`@/assets/mainpage/${slide.imgUrl}`)" alt="" />
    </slide>
    <template #addons> </template>
  </carousel>
</template>

<script>
import "vue3-carousel/dist/carousel.css";
import { Carousel, Slide } from "vue3-carousel";

export default {
  components: {
    Carousel,
    Slide,
  },
  setup() {
    const slides = [
      { id: "1", imgUrl: "handle1.png" },
      { id: "2", imgUrl: "handle2.png" },
      { id: "3", imgUrl: "handle3.png" },
      { id: "4", imgUrl: "handle4.png" },
      { id: "5", imgUrl: "handle5.png" },
      { id: "6", imgUrl: "handle6.png" },
      { id: "7", imgUrl: "handle7.png" },
      { id: "8", imgUrl: "handle8.png" },
      { id: "9", imgUrl: "handle9.png" },
    ];
    return {
      slides,
    };
  },
};
</script>

<style scoped>
.slide-img {
  widows: 100%;
  border-radius: 30px;
}
</style>
