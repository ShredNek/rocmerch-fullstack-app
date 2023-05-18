<template>
  <nav id="page-header">
    <h1>Ripples of Change - Official Merchandise</h1>
    <ul id="shopping-utilities">
      <li class="categories medium-screen">
        <button>Categories</button>
      </li>
      <li class="categories small-screen">
        <v-icon class="v-icon" icon="fa-solid fa-shapes" />
      </li>
      <li class="search-bar">
        <input name="search-bar" type="text" />
        <v-icon class="v-icon" icon="fa-solid fa-magnifying-glass" />
      </li>
      <li class="cart">
        <button @click="openCartSidebar">
          <v-icon class="v-icon" icon="fa-solid fa-cart-shopping" />
        </button>
      </li>
    </ul>
  </nav>
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
          <button class="edit">Edit Cart</button>
          <ReactiveQuantityButton
              :quantity="reactiveTotalPrice"
              :buttonText="'Pay Now'"
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

export default {
  name: 'Header',
  components: {
    ReactiveQuantityButton
  },
  data() {
    return {
      currentCart: {} as MerchandiseOrderInterface,
    }
  },
  computed: {
    reactiveListOfCarts() {
      const userCartAndData = useUserCartAndDataStore()
      this.currentCart.totalPrice = userCartAndData.calculateTotalPrice()
      return userCartAndData.merchItemsInCart
    },
    reactiveTotalPrice() {
      const userCartAndData = useUserCartAndDataStore()
      return userCartAndData.calculateTotalPrice()
    },
  },
  methods: {
    openCartSidebar() {
      const userCartAndData = useUserCartAndDataStore()

      this.currentCart.merchItemsInCart = userCartAndData.merchItemsInCart
      this.currentCart.totalPrice = userCartAndData.calculateTotalPrice()

      const sidebar = this.$refs.cartSidebar as HTMLDialogElement
      sidebar.classList.remove('close')
      sidebar.showModal()
    },
    onSidebarAnimationEnd() {
      const sidebar = this.$refs.cartSidebar as HTMLDialogElement
      sidebar.removeEventListener('animationend', this.onSidebarAnimationEnd)
      sidebar.close()
    },
    closeCartSidebar() {
      const sidebar = this.$refs.cartSidebar as HTMLDialogElement
      sidebar.classList.add('close')
      sidebar.addEventListener('animationend', this.onSidebarAnimationEnd)
    },
    deleteItemFromCart(id: number) {
      const userCartAndData = useUserCartAndDataStore()
      userCartAndData.removeItemById(id)
    },
  },
}
</script>
