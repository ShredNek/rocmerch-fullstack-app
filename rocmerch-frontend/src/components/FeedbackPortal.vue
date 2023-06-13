<template>
  <div
    :class="{
      'feedback-portal open': isOpen,
      'feedback-portal': isOpen == false,
    }"
  >
    <h3>Is there something we can improve on?</h3>
    <textarea
      v-model="feedback"
      placeholder="Your feedback goes straight to the developers of this website, so be as descriptive as you can!"
    />
    <button v-on:click="handleClick">Send feedback</button>
  </div>
</template>

<script lang="ts">
import {sendFeedback} from "../GLOBALS"
export default {
  data() {
    return {
      feedback: '',
    }
  },
  name: 'FeedbackPortal',
  props: { isOpen: Boolean },
  emits: ["close-portal"],
  methods: {
    async handleClick(e: Event) {
      e.preventDefault
      this.$emit("close-portal")
      await sendFeedback(this.feedback)
    },
  },
}
</script>
