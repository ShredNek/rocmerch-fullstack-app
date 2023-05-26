<template>
  <li class="search-bar">
    <input
      name="search-bar"
      class="search-bar-button-dropdown"
      type="text"
      v-model="searchInput"
    />
    <v-icon class="v-icon" icon="fa-solid fa-magnifying-glass" />
  </li>
</template>

<script lang="ts">
import {
  getAllItemsThatMatchSearch,
  MerchandiseItemInterface,
} from '../GLOBALS'
import { AxiosResponse } from 'axios'

export default (await import('vue')).defineComponent({
  name: 'SearchBar',
  emits: ['items-loading', 'items-loaded'],
  data() {
    return {
      searchInput: '',
      allItems: {} as AxiosResponse<MerchandiseItemInterface, unknown>,
      debounce: this.createDebounce(),
      isSearching: false,
    }
  },
  methods: {
    async setAllItems() {
      const response = await getAllItemsThatMatchSearch(this.searchInput)
      response ? (this.allItems = response) : null
      this.handleItemsWhenFinishedLoading()
    },
    createDebounce() {
      let timeout: NodeJS.Timeout
      return function (fnc: any, delayMs: any) {
        clearTimeout(timeout)
        timeout = setTimeout(() => {
          fnc()
        }, delayMs || 500)
      }
    },
    handleItemsWhenFinishedLoading() {
      this.isSearching = false
      this.$emit('items-loaded', this.allItems?.data)
    },
    handleItemsToLoad() {
      this.isSearching = true
      this.$emit('items-loading')
    },
  },
  watch: {
    searchInput() {
      this.handleItemsToLoad()
      this.debounce(() => this.setAllItems(), 750)
    },
  },
})
</script>
