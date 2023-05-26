<template>
  <button
    :class="['red-glow', 'animated', 'add-to-cart', quantity === 0 ? 'inactive' : '']"
    @click="onClickBubble"
  >
    {{ buttonText }}
  </button>
</template>

<script lang="ts">
export default (await import('vue')).defineComponent({
  name: 'ReactiveQuantityButton',
  props: {
    quantity: {
      type: Number,
      required: true,
    },
    onClickBubble: {
      type: Function,
      required: false,
    },
    buttonText: {
      type: String,
      required: true,
    },
  },
})
</script>
