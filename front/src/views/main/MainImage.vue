<template>
    <carousel :autoplay='5000' :wrap-around='true' :itemsToShow='1'>
      <slide v-for='slide in slides' :key='slide'>
        <img class='slide-img' :src='require(`@/assets/mainpage/${slide.imgUrl}`)' alt='' />
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
        { id: '1', imgUrl: 'image1.png' },
        { id: '2', imgUrl: 'image2.png' },
        { id: '3', imgUrl: 'image3.png' },
        { id: '4', imgUrl: 'image4.jpg' },
        { id: '5', imgUrl: 'image5.jpg' },
        { id: '6', imgUrl: 'image6.jpg' },
        { id: '7', imgUrl: 'image7.jpg' },
        { id: '8', imgUrl: 'image8.jpg' },
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
  