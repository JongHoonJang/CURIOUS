<template>
  <carousel :autoplay="5000" :wrap-around="true" :itemsToShow="1">
    <slide v-for="slide in slides" :key="slide">
      <img class="slide-img" :src="require(`@/assets/landing/${slide.imgUrl}`)" alt="" />
    </slide>

    <template #addons> </template>
  </carousel>
</template>

<script>
import 'vue3-carousel/dist/carousel.css';
import { Carousel, Slide } from 'vue3-carousel';

export default {
  components: {
    Carousel,
    Slide,
  },
  setup() {
    const slides = [
      { id: '1', imgUrl: 'landingSlide1.png' },
      { id: '2', imgUrl: 'landingSlide2.png' },
      { id: '3', imgUrl: 'landingSlide3.png' },
      { id: '4', imgUrl: 'landingSlide4.png' },
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
}
</style>
