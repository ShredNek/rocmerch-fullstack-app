<template>
  <div class="item">
    <div>
      <div class="image-container">
        <img :src="itemImage" :alt="`image for ${itemName}`" />
      </div>
      <p>{{ itemName }}</p>
    </div>
    <p v-if="itemQuantity">x{{ itemQuantity }}</p>
    <h3 v-if="!editAndRemoveSelfControls">${{ itemIndividualPrice }}</h3>
    <h3 v-else-if="itemQuantity && editAndRemoveSelfControls">
      ${{ (itemIndividualPrice * itemQuantity).toFixed(2) }}
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
    itemName: { type: String, required: true },
    itemImage: { type: String, required: true },
    itemIndividualPrice: { type: Number, required: true },
    itemQuantity: { type: Number, required: false },
    editAndRemoveSelfControls: { type: Boolean, required: false },
    item: {
      type: Object as PropType<MerchandiseItemWithQuantityInterface>,
      required: false,
    },
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
