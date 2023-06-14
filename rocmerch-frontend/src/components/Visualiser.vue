<template>
  <section id="visualiser">
    <div :class="['video-overlay', videoIsLoaded ? 'revealed' : 'hidden']">
      <h2>Throw the stone.<br/>There will be ripples of change<br/>in the pond of life...</h2>
    </div>
    <video src="../assets/videos/VisualiserEdited_D1.mov" autoplay="true" loop="true" muted="true" :onloadeddata="handleVideoIsLoaded"/>
  </section>
</template>

<script lang="ts">
export default {
  name: "Visualiser",
  data() {
    return {
      videoIsLoaded: false
    }
  },
  methods: {
    handleVideoIsLoaded() {
      this.videoIsLoaded = true
    }
  }
}
</script>