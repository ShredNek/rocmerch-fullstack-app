<template>
  <section>
    <Header />
    <div class="shopping-section">
      <div
        v-if="Object.keys(merchItem).length === 0"
        class="item-card-container"
      >
        <LoadingSpinner />
      </div>
      <div v-else class="individual-merchandise-item">
        <AddedToCart
          :itemName="merchItem.name"
          :itemQuantity="itemQuantity"
          :isOpen="isOpen"
        />
        <div class="image-container">
          <img :src="imageUrl" :alt="`img for ${merchItem.name}`" />
        </div>
        <div class="item-details-container">
          <h2 class="section-header">{{ merchItem.name }}</h2>
          <p>{{ merchItem.itemDescription }}</p>
          <h3 class="price">${{ merchItem.price }}</h3>
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
  NOTIFICATION_DURATION,
  getItemById,
} from '../GLOBALS'

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
      merchItem: {} as MerchandiseItemInterface,
      itemQuantity: 1,
      itemWithQuantity: {} as MerchandiseItemWithQuantityInterface,
      isOpen: false,
    }
  },
  // async setup() {
  //   await this.getSelectedItem()
  //   this.merchItem.name = uppercaseFirstLetter(this.merchItem.name)
  // }
  // ,
  async mounted() {
    await this.getSelectedItem()
    this.merchItem.name = uppercaseFirstLetter(this.merchItem.name)
  },
  methods: {
    addToCart() {
      const userItemsAndDataStore = useUserCartAndDataStore()

      let currItem = this.itemWithQuantity
      currItem.quantity = this.itemQuantity
      currItem.merchandiseItem = this.merchItem

      const newItem = {
        quantity: this.itemQuantity,
        merchandiseItem: this.merchItem,
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
        this.merchItem = item
      }
    },
    loadItemFromLocalStore(currId: number) {
      const categoryItemsStore = useCategoryItemsStore()
      return categoryItemsStore.categoryItems.find((i) => i.id === currId)
    },
    async loadItemFromDatabase(currId: number) {
      this.merchItem = await getItemById(currId)
    },
  },
  computed: {
    imageUrl() {
      return new URL(
        `../assets/images/${this.merchItem.image}`,
        import.meta.url
      ).href
    },
  },
  watch: {
    $route() {
      this.merchItem = {} as MerchandiseItemInterface
      this.getSelectedItem()
    },
  },
})
</script>
