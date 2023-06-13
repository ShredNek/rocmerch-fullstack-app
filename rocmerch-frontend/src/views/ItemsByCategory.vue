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
import {
  MerchandiseItemInterface,
  uppercaseFirstLetter,
  removeHyphen,
  getCategoryOfItems,
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
    this.items = await getCategoryOfItems(this.$route.params.category)
    this.storeCategoryOfItems()
    this.validCategoryName = removeHyphen(
      uppercaseFirstLetter(this.$route.params.category)
    )
  },
  methods: {
    storeCategoryOfItems() {
      const store = useCategoryItemsStore()
      store.storeItems(this.items)
    },
  },
  watch: {
    async $route() {
      this.storeCategoryOfItems()
      this.validCategoryName = removeHyphen(
      uppercaseFirstLetter(this.$route.params.category)
    )
      this.items = []
      this.items = await getCategoryOfItems(this.$route.params.category)
    },
  },
})
</script>
