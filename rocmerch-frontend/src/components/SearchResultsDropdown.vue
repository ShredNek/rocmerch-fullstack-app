<template>
  <div
    id="search-bar-dropdown"
    :class="['search-bar-dropdown', 'dropdown', isOpen ? 'open' : '']"
  >
    <div class="items">
      <LoadingSpinner
        v-if="searchResults && searchResults.length === 0"
        class="loading"
      />
      <router-link
        v-else-if="searchResults && searchResults.length >= 1"
        v-for="item in searchResults.slice(0, 5)"
        :key="item.merchandiseItem.id"
        :to="`/item/${item.merchandiseItem.category}/${item.merchandiseItem.id}`"
      >
        <OrderSummaryItem
          :item="item"
          :itemImage="handleDynamicUrl(item.merchandiseItem)"
          :itemIndividualPrice="item.merchandiseItem.price"
          :itemName="item.merchandiseItem.name"
        />
      </router-link>
    </div>
  </div>
</template>

<script lang="ts">
import OrderSummaryItem from '../components/OrderSummaryItem.vue'
import LoadingSpinner from './LoadingSpinner.vue'
import { PropType } from 'vue'
import { generateDynamicUrl, MerchandiseItemInterface, MerchandiseItemWithQuantityInterface } from '../GLOBALS'

export default (await import('vue')).defineComponent({
  name: 'SearchResultsDropdown',
  components: {
    OrderSummaryItem,
    LoadingSpinner,
  },
  props: {
    isOpen: { type: Boolean, required: true },
    searchResults: {
      type: [] as PropType<MerchandiseItemWithQuantityInterface[]>,
      required: false,
    },
  },
  methods: {
    handleDynamicUrl(item: MerchandiseItemInterface) {
      return generateDynamicUrl(item, import.meta.url)
    },
  },
  watch: {
    searchResults(n) {
      console.log(n)
      console.clear()
    },
  },
  mounted() {
    console.clear()
  },
})
</script>
