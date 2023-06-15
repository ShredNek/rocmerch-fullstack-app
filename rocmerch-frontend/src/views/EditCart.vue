<template>
  <Header />
  <Visualiser />
  <section id="edit-cart">
    <h1>Edit Cart</h1>
    <div v-if="userCart.merchItemsInCart.length === 0">
      <p class="warning">(Your cart appears to be empty)</p>
    </div>
    <div v-else class="items edit-cart-background-and-display">
      <OrderSummaryItem
        v-for="item in userCart.merchItemsInCart"
        :key="item.merchandiseItem.id"
        :item="item"
        :editAndRemoveSelfControls="true"
      />
    </div>
    <div class="pay-and-total">
      <router-link class="pay-now" to="/checkout/user">
        <ReactiveQuantityButton
          :quantity="totalPrice"
          :buttonText="'Pay Now'"
        />
      </router-link>
      <div class="total-price" v-if="totalPrice !== 0">
        <h2>Total price:</h2>
        <h2>${{ totalPrice }}</h2>
      </div>
    </div>
  </section>
  <CopyrightFooter />
</template>

<script lang="ts">
import Header from '../components/Header.vue'
import Visualiser from '../components/Visualiser.vue'
import CopyrightFooter from '../components/CopyrightFooter.vue'
import LoadingSpinner from '../components/LoadingSpinner.vue'
import OrderSummaryItem from '../components/OrderSummaryItem.vue'
import ReactiveQuantityButton from '../components/ReactiveQuantityButton.vue'
import { useUserCartAndDataStore } from '../stores/userCartAndData'
import {MerchandiseItemInterface, generateUrlAtBuild} from '../GLOBALS'

export default (await import('vue')).defineComponent({
  name: 'EditCart',
  components: {
    Header,
    Visualiser,
    CopyrightFooter,
    LoadingSpinner,
    OrderSummaryItem,
    ReactiveQuantityButton,
  },
  setup() {
    const userCartAndData = useUserCartAndDataStore()
    return { userCartAndData }
  },
  computed: {
    userCart() {
      return this.userCartAndData
    },
    totalPrice() {
      return this.userCartAndData.calculateTotalPrice()
    },
  },
  methods: {
    handleDynamicUrl(item: MerchandiseItemInterface) {
      return generateUrlAtBuild(item.image,import.meta.url)
    },
  }
})
</script>
