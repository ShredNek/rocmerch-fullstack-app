<template>
  <section>
    <Header />
    <div class="shopping-section">
      <div v-if="Object.keys(item).length === 0" class="item-card-container">
        <LoadingSpinner />
      </div>
      <div v-else class="individual-merchandise-item">
        <AddedToCart
          :itemName="item.name"
          :itemQuantity="itemQuantity"
          :isOpen="isOpen"
        />
        <div class="image-container">
          <img
            src="../assets/images/RipplesOfChange.jpg"
            :alt="`img for ${item.name}`"
          />
        </div>
        <div class="item-details-container">
          <h2 class="section-header">{{ item.name }}</h2>
          <p>{{ item.itemDescription }}</p>
          <h3 class="price">${{ item.price }}</h3>
          <div class="quantity-and-cart">
            <div class="quantity">
              <button class="quantity-adjust" @click="itemQuantity++">+</button>
              <p>{{ itemQuantity }}</p>
              <button
                class="quantity-adjust"
                @click="itemQuantity ? itemQuantity-- : null"
              >
                -
              </button>
            </div>
            <ReactiveQuantityButton
              :quantity="itemQuantity"
              :onClickBubble="addToCart"
              :buttonText="'ADD TO CART'"
            />
          </div>
        </div>
      </div>
    </div>
    <CopyrightFooter />
    <FeedbackBubble />
  </section>
</template>

<script lang="ts">
import { useCategoryItemsStore } from '../stores/categoryItems'
import { useUserCartAndDataStore } from '../stores/userCartAndData'
import Header from '../components/Header.vue'
import CopyrightFooter from '../components/CopyrightFooter.vue'
import FeedbackBubble from '../components/FeedbackBubble.vue'
import MerchandiseItem from '../components/MerchandiseItem.vue'
import LoadingSpinner from '../components/LoadingSpinner.vue'
import AddedToCart from '../components/AddedToCart.vue'
import ReactiveQuantityButton from '../components/ReactiveQuantityButton.vue'
import {
  MerchandiseItemInterface,
  MerchandiseItemWithQuantityInterface,
  uppercaseFirstLetter,
  NOTIFICATION_DURATION
} from '../GLOBALS'
import axios from 'axios'

export default (await import('vue')).defineComponent({
  name: 'ItemsByCategory',
  components: {
    Header,
    CopyrightFooter,
    FeedbackBubble,
    MerchandiseItem,
    LoadingSpinner,
    AddedToCart,
    ReactiveQuantityButton,
  },
  data() {
    return {
      item: {} as MerchandiseItemInterface,
      itemQuantity: 1,
      itemWithQuantity: {} as MerchandiseItemWithQuantityInterface,
      isOpen: false,
    }
  },
  async mounted() {
    await this.getSelectedItem()
    this.item.name = uppercaseFirstLetter(this.item.name)
  },
  methods: {
    addToCart() {
      const userItemsAndDataStore = useUserCartAndDataStore()

      let currItem = this.itemWithQuantity
      currItem.quantity = this.itemQuantity
      currItem.merchandiseItem = this.item

      const newItem = {
        quantity: this.itemQuantity,
        merchandiseItem: this.item,
      }

      const isThere = userItemsAndDataStore.checkIfItemPresent(newItem)

      isThere
        ? userItemsAndDataStore.addQuantityToItem(newItem)
        : userItemsAndDataStore.storeOneItem(newItem)

      this.activateAddedToCartNotification()
    },
    activateAddedToCartNotification() {
      this.isOpen = true

      setTimeout(() => (this.isOpen = false), NOTIFICATION_DURATION)
    },
    async getSelectedItem() {
      const currentItemId = Number(this.$route.params.id)
      let item = this.loadItemFromLocalStore(currentItemId)

      if (item === undefined || item === null) {
        await this.loadItemFromDatabase(currentItemId)
      } else {
        this.item = item
      }
    },
    loadItemFromLocalStore(currId: number) {
      const categoryItemsStore = useCategoryItemsStore()
      return categoryItemsStore.categoryItems.find((i) => i.id === currId)
    },
    async loadItemFromDatabase(currId: number) {
      const url = `http://localhost:8080/items/get-by-category/${this.$route.params.category}`
      const response = await axios.get(url)

      this.item = response.data.find(
        (i: MerchandiseItemInterface) => i.id === currId
      )
    },
  },
  watch: {
    $route() {
      this.getSelectedItem()
    }
  }
})
</script>
