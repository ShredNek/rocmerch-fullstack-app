<template>
  <section>
    <Header />
    <Visualiser />
    <div class="shopping-section">
      <h2 class="section-header">{{ validCategoryName }}</h2>
      <div v-if="items.length === 0" class="item-card-container">
        <LoadingSpinner />
      </div>
      <div v-else class="item-card-container">
        <div v-for="item in items" :key="item.id">
          <MerchandiseItem :item="item" />
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
import Visualiser from '../components/Visualiser.vue'
import CopyrightFooter from '../components/CopyrightFooter.vue'
import FeedbackBubble from '../components/FeedbackBubble.vue'
import MerchandiseItem from '../components/MerchandiseItem.vue'
import LoadingSpinner from '../components/LoadingSpinner.vue'
import axios from 'axios'
import {
  MerchandiseItemInterface,
  uppercaseFirstLetter,
  removeHyphen,
} from '../GLOBALS'

export default (await import('vue')).defineComponent({
  name: 'ItemsByCategory',
  components: {
    Header,
    Visualiser,
    CopyrightFooter,
    FeedbackBubble,
    MerchandiseItem,
    LoadingSpinner,
  },
  data() {
    return {
      items: [] as MerchandiseItemInterface[],
      validCategoryName: '',
    }
  },
  async mounted() {
    this.getCategoryOfItems()
    this.validCategoryName = removeHyphen(
      uppercaseFirstLetter(this.$route.params.category)
    )
  },
  methods: {
    async getCategoryOfItems() {
      try {
        const url = `http://localhost:8080/items/get-by-category/${this.$route.params.category}`
        const response = await axios.get(url)
        this.items = response.data
        
        const store = useCategoryItemsStore()
        store.storeItems(this.items)
      } catch (e) {
        console.log(e)
      }
    },
  },
})
</script>
