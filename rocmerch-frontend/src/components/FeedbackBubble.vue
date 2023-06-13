<template>
  <button class="feedback-bubble red-glow" v-on:click="clickedState = !clickedState" >
    <p>Feedback</p>
  </button>
  <FeedbackPortal :isOpen="clickedState" @close-portal="clickedState = false"/>
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
  }
}
</script>
