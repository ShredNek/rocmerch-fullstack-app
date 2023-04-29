<template>
  <button class="feedback-bubble" v-on:click="clickedState = !clickedState">
    <p>Feedback</p>
  </button>
  <FeedbackPortal :isOpen="clickedState"/>
</template>

<script lang="ts">
import FeedbackPortal from './FeedbackPortal.vue'

export default {
  name: 'FeedbackBubble',
  components: { FeedbackPortal },
  data() {
    return {
      clickedState: false,
    }
  },
}
</script>
