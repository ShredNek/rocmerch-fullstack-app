<template>
  <h1>Ripples Of Change - Official Merch Checkout</h1>
  <div class="breadcrumbs">
    <router-link class="filled" to="/checkout/user">
      <p>User</p>
      <p>></p>
    </router-link>
    <router-link class="filled" to="/checkout/payment">
      <p>Payment</p>
      <p>></p>
    </router-link>
    <div>
      <p>Finish</p>
    </div>
  </div>
  <h2>Finish</h2>
  <div class="thank-you-message">
    <p class="header">Your order has been processed - please check your email account to see your digital invoice!</p>
    <img :src="receivedVector" alt="">
    <p>
      Thank you for "shopping" and touring my app! This took me about 2 months
      to make, and I'm grateful that you took the time to look around and have a
      play with what I've created :)
    </p>
    <p>
      If you feel so kind, feel free to send me an email at
      danielleemusic98@gmail.com for any changes I could make to the app
    </p>
    <h2>Thank you!</h2>
  </div>
  <button class="link red-glow animated" @click.prevent="handleClick">
      HOME
    </button>
</template>

<script lang="ts">
import { useRouter } from 'vue-router'
import ReceivedVector from '../assets/images/Received.jpg' 

export default (await import('vue')).defineComponent({
  name: 'PaymentDetails',
  emits: ['invalid-input-event', 'close-self-event'],
  setup() {
    const router = useRouter()
    const receivedVector = ReceivedVector
    return {
      router,
      receivedVector
    }
  },
  methods: {
    handleClick() {
      this.router.push('/')
    },
  },
})
</script>
