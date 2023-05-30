<template>
  <div
    :class="[
      'notification',
      'invalid-input',
      'red-glow',
      'strong',
      checkIfOpenAndSetStyles ? 'open' : '',
    ]"
  >
    <h2>
      Looks like we're missing some more information! Please fill in all the areas marked red below.
    </h2>
  </div>
</template>

<script lang="ts">
export default (await import('vue')).defineComponent({
  name: 'AddedToCart',
  props: {
    isOpen: { type: Boolean, required: true },
  },
  computed: {
    checkIfOpenAndSetStyles() {
      return this.isOpen ? true : false
    },
  },
})
</script>
