<template>
  <div class="img-and-item-name">
    <div class="image-container">
      <img :src="dynamicUrl" :alt="`Image for ${merchItem.merchandiseItem.name}`"/>
    </div>
    <h4>{{ merchItem.merchandiseItem.name }}</h4>
  </div>
  <div class="price-quantity-drop-item">
    <p class="price">${{ merchItem.merchandiseItem.price }}</p>
    <p class="quantity">{{ merchItem.quantity }}</p>
    <button
      class="close-button"
      @click="deleteItemFromCart(merchItem.merchandiseItem.id)"
    >
      <v-icon class="v-icon" icon="fa-solid fa-xmark" />
    </button>
  </div>
</template>

<script lang="ts">
import { PropType } from 'vue'
import { useUserCartAndDataStore } from '../stores/userCartAndData'
import { MerchandiseItemWithQuantityInterface } from '../GLOBALS'

export default (await import('vue')).defineComponent({
  name: 'CartSidebarChild',
  props: {
    merchItem: {
      type: Object as PropType<MerchandiseItemWithQuantityInterface>,
      required: true,
    },
  },
  setup(props) {
    const dynamicUrl = new URL(`../assets/images/${props.merchItem.merchandiseItem.image}`, import.meta.url).href
    return {
      dynamicUrl
    }
  },
  computed: {
    userCartAndData() {
      return useUserCartAndDataStore()
    },
  },
  methods: {
    deleteItemFromCart(id: number) {
      this.userCartAndData.removeItemById(id)
    },
  },
})
</script>
