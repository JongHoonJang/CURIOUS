<template>
  <div>
  </div>
</template>

<script>
import { useRoute } from 'vue-router';
import { ref } from 'vue';
import { useAccountStore } from '@/stores/accounts';
export default {
  setup(){
    const account = useAccountStore();
    const route = ref(useRoute());
    account.fetchLogin('GOOGLE',route.value.query.code);
  }
};
</script>

<style>

</style>