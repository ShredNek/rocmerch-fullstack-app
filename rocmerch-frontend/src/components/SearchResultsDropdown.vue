<template>
  <div
    id="search-bar-dropdown"
    :class="['search-bar-dropdown', 'dropdown', isOpen ? 'open' : '']"
  >
    <div class="items">
      <div v-if="searchError || (searchResults && searchResults.length === 0)">
        <LoadingSpinner class="loading" />
        <p class="loading-notice">Searching the database... <br/> the first call may take up to 20 seconds.</p>
      </div>
      <router-link
        v-else-if="searchResults && searchResults.length >= 1"
        v-for="item in searchResults"
        :key="item.id"
        :to="`/item/${item.category}/${item.id}`"
      >
        <OrderSummaryItem
          :itemImage="item.image"
          :itemIndividualPrice="item.price"
          :itemName="item.name"
        />
      </router-link>
    </div>
  </div>
</template>

<script lang="ts">
import OrderSummaryItem from '../components/OrderSummaryItem.vue'
import LoadingSpinner from './LoadingSpinner.vue'
import { PropType } from 'vue'
import { MerchandiseItemInterface } from '../GLOBALS'

export default (await import('vue')).defineComponent({
  name: 'SearchResultsDropdown',
  components: {
    OrderSummaryItem,
    LoadingSpinner,
  },
  data() {
    return {
      searchError: false,
    }
  },
  props: {
    isOpen: { type: Boolean, required: true },
    searchResults: {
      type: [] as PropType<MerchandiseItemInterface[]>,
      required: false,
    },
  },
  
  watch: {
    searchResults(n) {
      console.log(n)
      if (n === undefined) {
        this.searchError = true
      }
      this.searchError = false
      console.clear()
    },
  },
  mounted() {
    console.clear()
  },
})
</script>
