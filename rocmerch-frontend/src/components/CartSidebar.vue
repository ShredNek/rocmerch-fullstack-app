<template>
  <dialog ref="cartSidebar" id="cart-sidebar" class="close">
    <div class="transparent-row">
      <button class="close-button" @click="closeCartSidebar">
        <v-icon class="v-icon" icon="fa-solid fa-xmark" />
      </button>
      <h2>Close cart</h2>
    </div>
    <div class="cart-item-container">
      <div class="cart-items">
        <div
          v-if="
            currentCart.merchItemsInCart === undefined ||
            currentCart.merchItemsInCart.length === 0
          "
        >
          <div class="item empty-cart-msg"><h4>(your cart is empty)</h4></div>
        </div>
        <div v-else-if="currentCart.merchItemsInCart.length > 0">
          <div
            v-for="miin in currentCart.merchItemsInCart"
            :key="miin.merchandiseItem.id"
            class="item"
          >
            <div class="img-and-item-name">
              <div class="image-container">
                <img src="../assets/images/Heart_Draft2.jpg" alt="" />
              </div>
              <h4>{{ miin.merchandiseItem.name }}</h4>
            </div>
            <div class="price-quantity-drop-item">
              <p class="price">${{ miin.merchandiseItem.price }}</p>
              <p class="quantity">{{ miin.quantity }}</p>
              <button
                class="close-button"
                @click="deleteItemFromCart(miin.merchandiseItem.id)"
              >
                <v-icon class="v-icon" icon="fa-solid fa-xmark" />
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="cart-details">
        <h3>Cart total</h3>
        <h4>${{ reactiveTotalPrice }}</h4>
        <div class="button-row">
          <router-link to="/edit-cart">
            <button class="edit">Edit Cart</button>
          </router-link>
          <ReactiveQuantityButton
            :quantity="reactiveTotalPrice"
            :buttonText="'Pay Now'"
            :onClickBubble="handlePayNowClick"
          />
        </div>
      </div>
    </div>
  </dialog>
</template>

<script lang="ts">
import { MerchandiseOrderInterface } from '../GLOBALS'
import { useUserCartAndDataStore } from '../stores/userCartAndData'
import ReactiveQuantityButton from './ReactiveQuantityButton.vue'
import { useRouter } from 'vue-router'

export default (await import('vue')).defineComponent({
  namne: 'CartSidebar',
  components: {
    ReactiveQuantityButton,
  },
  props: {
    isOpen: { type: Boolean, required: true },
  },
  setup() {
    const router = useRouter()
    return { router }
  },
  data() {
    return {
      currentCart: {} as MerchandiseOrderInterface,
    }
  },
  computed: {
    userCartAndData() {
      return useUserCartAndDataStore()
    },
    reactiveListOfCarts() {
      this.currentCart.totalPrice = this.userCartAndData.calculateTotalPrice()
      return this.userCartAndData.merchItemsInCart
    },
    reactiveTotalPrice() {
      return this.userCartAndData.calculateTotalPrice()
    },
  },
  methods: {
    openCartSidebar() {
      this.currentCart.merchItemsInCart = this.userCartAndData.merchItemsInCart
      this.currentCart.totalPrice = this.userCartAndData.calculateTotalPrice()

      const sidebar = this.$refs.cartSidebar as HTMLDialogElement
      sidebar.classList.remove('close')
      sidebar.showModal()
    },
    changeModalOpenState() {
      this.isOpen ? this.openCartSidebar() : null
    },
    onSidebarAnimationEnd() {
      const sidebar = this.$refs.cartSidebar as HTMLDialogElement
      sidebar.removeEventListener('animationend', this.onSidebarAnimationEnd)
      sidebar.close()
    },
    closeCartSidebar() {
      this.$emit('cart-close-event')
      const sidebar = this.$refs.cartSidebar as HTMLDialogElement
      sidebar.classList.add('close')
      sidebar.addEventListener('animationend', this.onSidebarAnimationEnd)
    },
    deleteItemFromCart(id: number) {
      this.userCartAndData.removeItemById(id)
    },
    handlePayNowClick() {
      this.router.push('/checkout/user')
    },
  },
  watch: {
    isOpen: {
      handler: function () {
        this.changeModalOpenState()
      },
    },
  },
})
</script>
