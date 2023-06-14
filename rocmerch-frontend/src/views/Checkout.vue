<template>
  <!-- <InvalidInput :isOpen="isUserInputInvalid" /> -->
  <section id="checkout">
    <div v-if="changingParam === 'user'" class="user details">
      <UserDetails
        @invalid-input-event="isUserInputInvalid = true"
        @close-self-event="isUserInputInvalid = false"
        @create-order-event="assignOrder"
      />
    </div>
    <div v-else-if="changingParam === 'payment'" class="user details">
      <PaymentDetails
        @invalid-input-event="isUserInputInvalid = true"
        @close-self-event="isUserInputInvalid = false"
        @send-email-event="sendOrderAndEmail"
      />
    </div>
    <div v-else-if="changingParam === 'finish'" class="user details">
      <FinishDetails />
    </div>
    <div class="cart details">
      <h2>Order Summary</h2>
      <div class="items">
        <OrderSummaryItemVue
          v-for="item in reactiveCartData.merchItemsInCart"
          :key="item.merchandiseItem.id"
          :itemImage="handleDynamicUrl(item.merchandiseItem)"
          :itemIndividualPrice="item.merchandiseItem.price"
          :itemName="item.merchandiseItem.name"
          :itemQuantity="item.quantity"
        />
      </div>
      <div class="subtotal">
        <h3>Subtotal</h3>
        <h3>${{ reactiveCartData.totalPrice }}</h3>
      </div>
      <div class="shipping">
        <div>
          <h3>Shipping</h3>
          <h4>(Standard shipping)</h4>
        </div>
        <h3>FREE</h3>
      </div>
      <div class="total">
        <h3>Total</h3>
        <h2>${{ reactiveCartData.totalPrice }}</h2>
      </div>
      <router-link to="/">
        <button class="resume red-glow animated">Resume Shopping</button>
      </router-link>
      <router-link to="/edit-cart">
        <button class="edit">Edit Cart</button>
      </router-link>
    </div>
  </section>
</template>

<script lang="ts">
import UserDetails from '../components/UserDetails.vue'
import PaymentDetails from '../components/PaymentDetails.vue'
import OrderSummaryItemVue from '../components/OrderSummaryItem.vue'
import InvalidInput from '../components/InvalidInput.vue'
import FinishDetails from '../components/FinishDetails.vue'
import { useUserCartAndDataStore } from '../stores/userCartAndData'
import {
  MerchandiseItemInterface,
  MerchandiseOrderInterface,
  generateDynamicUrl,
  sendOrderAndReturnOrderId,
  sendEmail
} from '../GLOBALS'

export default (await import('vue')).defineComponent({
  name: 'Checkout',
  components: {
    UserDetails,
    PaymentDetails,
    OrderSummaryItemVue,
    InvalidInput,
    FinishDetails,
  },
  mounted() {
    this.userCartAndData.totalPrice = this.userCartAndData.calculateTotalPrice()
  },
  data() {
    return { isUserInputInvalid: false}
  },
  computed: {
    userCartAndData() {
      return useUserCartAndDataStore()
    },
    changingParam() {
      return this.$route.params.step as string
    },
    reactiveCartData() {
      return this.userCartAndData
    },
  },
  methods: {
    handleDynamicUrl(item: MerchandiseItemInterface) {
      return generateDynamicUrl(item, import.meta.url)
    },
    assignOrder(userDetails: { [key: string]: string }) {
      const computedNameForOrder: string =
        userDetails['first-name'] + ' ' + userDetails['last-name']
      const newOrder: MerchandiseOrderInterface = {
        nameForOrder: computedNameForOrder,
        addressForOrder: userDetails['shipping-address'],
        phoneForOrder: userDetails['phone'],
        emailForOrder: userDetails['email-address'],
        totalPrice: this.reactiveCartData.totalPrice,
        merchItemsInCart: this.reactiveCartData.merchItemsInCart,
      }

      this.userCartAndData.storeEntireOrder(newOrder)
    },
    async sendOrderAndEmail() {
      const orderId = await sendOrderAndReturnOrderId()
      this.userCartAndData.wipeAllState()
      orderId ? sendEmail(orderId) : console.log("Order Id is null. Wtf?")
    }
  },
})
</script>
