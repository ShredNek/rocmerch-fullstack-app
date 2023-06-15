<template>
  <div class="item">
    <div>
      <div class="image-container">
        <img :src="dynamicUrl" :alt="`image for ${item?.merchandiseItem.name}`" />
      </div>
      <p>{{ item?.merchandiseItem.name }}</p>
    </div>
    <p v-if="item?.quantity">x{{ item?.quantity }}</p>
    <h3 v-if="!editAndRemoveSelfControls">${{ item?.merchandiseItem.price }}</h3>
    <h3 v-else-if="item?.quantity && editAndRemoveSelfControls">
      ${{ (item?.merchandiseItem.price * item?.quantity).toFixed(2) }}
    </h3>
    <div class="adjust-item" v-if="editAndRemoveSelfControls">
      <button @click="increaseQuantityOfItem">
        <v-icon class="v-icon" icon="fa-solid fa-plus" />
      </button>
      <button @click="removeItem">
        <v-icon class="v-icon" icon="fa-solid fa-xmark" />
      </button>
      <button @click="decreaseQuantityOfItem">
        <v-icon class="v-icon" icon="fa-solid fa-minus" />
      </button>
    </div>
  </div>
</template>

<script lang="ts">
import { useUserCartAndDataStore } from '../stores/userCartAndData'
import { MerchandiseItemWithQuantityInterface } from '../GLOBALS'
import { PropType } from 'vue'

export default (await import('vue')).defineComponent({
  name: 'OrderSummaryItem',
  props: {
    editAndRemoveSelfControls: { type: Boolean, required: false },
    item: {
      type: Object as PropType<MerchandiseItemWithQuantityInterface>,
      required: false,
    },
  },
  setup(props) {
    const dynamicUrl = new URL(`../assets/images/${props.item?.merchandiseItem.image}`, import.meta.url).href
    return {
      dynamicUrl
    }
  },
  computed: {
    userCartInstance() {
      return useUserCartAndDataStore()
    },
  },
  methods: {
    increaseQuantityOfItem() {
      this.item ? (this.item.quantity += 1) : null
    },
    decreaseQuantityOfItem() {
      this.item && this.item.quantity !== 1 ? (this.item.quantity -= 1) : null
    },
    removeItem() {
      this.item
        ? this.userCartInstance.removeItemById(this.item.merchandiseItem.id)
        : null
    },
  },
})
</script>
