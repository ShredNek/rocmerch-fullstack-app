<template>
  <section>
    <Header />
    <div class="shopping-section">
      <div class="individual-merchandise-item">
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
              <button class="quantity-adjust" @click="itemQuantity--">-</button>
            </div>
            <button class="red-glow add-to-cart">ADD TO CART</button>
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
import Header from '../components/Header.vue'
import CopyrightFooter from '../components/CopyrightFooter.vue'
import FeedbackBubble from '../components/FeedbackBubble.vue'
import MerchandiseItem from '../components/MerchandiseItem.vue'
import { MerchandiseItemInterface } from '../GLOBALS'

export default (await import('vue')).defineComponent({
  name: 'ItemsByCategory',
  components: {
    Header,
    CopyrightFooter,
    FeedbackBubble,
    MerchandiseItem,
  },
  data() {
    return {
      item: {} as MerchandiseItemInterface,
      itemQuantity: 1,
    }
  },
  mounted() {
    const categoryItemsStore = useCategoryItemsStore()
    const currentItemId = Number(this.$route.params.id)

    categoryItemsStore.categoryItems.forEach((i) => {
      if (i.id === currentItemId) this.item = i
    })
  },
})
</script>
