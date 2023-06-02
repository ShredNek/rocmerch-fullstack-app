<template>
  <button
    :class="['red-glow', 'animated', 'add-to-cart', quantity === 0 ? 'inactive' : '']"
    @click="onClickBubble"
  >
    {{ buttonText }}
  </button>
</template>

<script lang="ts">
import { defineComponent, PropType } from 'vue';

export default defineComponent({
  name: 'ReactiveQuantityButton',
  props: {
    quantity: {
      type: Number,
      required: true,
    },
    onClickBubble: {
      type: Function as PropType<((payload: MouseEvent) => void)>,
      required: false,
    },
    buttonText: {
      type: String,
      required: true,
    },
  },
})
</script>
