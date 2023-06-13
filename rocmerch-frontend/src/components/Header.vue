<template>
  <nav id="page-header">
    <router-link to="/">
      <h1>Ripples of Change - Official Merchandise</h1>
    </router-link>
    <ul id="shopping-utilities">
      <li class="categories medium-screen">
        <button @click="isCategoriesDropdownOpen = true" id="dropdown-button">
          Categories
        </button>
      </li>
      <li class="categories small-screen">
        <button @click="isCategoriesDropdownOpen = true" id="dropdown-button">
          <v-icon class="v-icon" icon="fa-solid fa-shapes" />
        </button>
      </li>
      <CategoriesDropdown :isOpen="isCategoriesDropdownOpen" />
      <SearchBar
        @click="isSearchBarOpen = true"
        @items-loading="handleLoadingItems"
        @items-loaded="handleLoadedItems"
      />
      <SearchResultsDropdown
        :isOpen="isSearchBarOpen"
        :searchResults="itemsReturned"
      />
      <li class="cart">
        <button @click="isCartSidebarOpen = true">
          <v-icon class="v-icon" icon="fa-solid fa-cart-shopping" />
        </button>
      </li>
    </ul>
  </nav>
  <CartSidebarVue
    :isOpen="isCartSidebarOpen"
    @cart-close-event="isCartSidebarOpen = false"
  />
</template>

<script lang="ts">
import { MerchandiseItemWithQuantityInterface} from '../GLOBALS'
import CartSidebarVue from './CartSidebar.vue'
import CategoriesDropdown from './CategoriesDropdown.vue'
import SearchBar from './SearchBar.vue'
import SearchResultsDropdown from './SearchResultsDropdown.vue'

export default {
  name: 'Header',
  emits: ['cart-close-event'],
  components: {
    CartSidebarVue,
    CategoriesDropdown,
    SearchBar,
    SearchResultsDropdown,
  },
  data() {
    return {
      isCartSidebarOpen: false,
      isCategoriesDropdownOpen: false,
      isSearchBarOpen: false,
      categoriesDropdownString: 'categories-dropdown',
      searchBarDropdownString: 'search-bar-dropdown',
      searchBarButtonDropdownString: 'search-bar-button-dropdown',
      buttonDropdownString: 'dropdown-button',
      itemsReturned: [] as MerchandiseItemWithQuantityInterface[],
    }
  },
  mounted() {
    this.addGlobalClickListeners()
  },
  unmounted() {
    this.removeGlobalClickListeners()
  },
  methods: {
    handleGlobalClickForCategoriesDropdown(e: Event) {
      const clickedElement = e.target as HTMLDivElement
      const clickedElementClassList = clickedElement.classList

      if (
        (clickedElement !== null &&
          clickedElementClassList[0] === this.categoriesDropdownString) ||
        clickedElement.id === this.buttonDropdownString
      ) {
        return
      }

      this.isCategoriesDropdownOpen = false
    },
    handleGlobalClickForSearchBarDropdown(e: Event) {
      const clickedElement = e.target as HTMLDivElement
      const clickedElementClassList = clickedElement.classList

      if (
        clickedElement !== null &&
        clickedElementClassList[0] === this.searchBarDropdownString
      ) {
        return
      }

      this.isSearchBarOpen = false
    },
    addGlobalClickListeners() {
      const body = this.$root?.$refs.main as HTMLBodyElement | undefined
      if (body) {
        body.addEventListener(
          'click',
          this.handleGlobalClickForCategoriesDropdown
        )
        body.addEventListener(
          'click',
          this.handleGlobalClickForSearchBarDropdown
        )
      }
    },
    removeGlobalClickListeners() {
      const body = this.$root?.$refs.main as HTMLBodyElement | undefined
      if (body) {
        body.removeEventListener(
          'click',
          this.handleGlobalClickForCategoriesDropdown
        )
        body.removeEventListener(
          'click',
          this.handleGlobalClickForSearchBarDropdown
        )
      }
    },
    handleLoadingItems() {
      this.itemsReturned = [] as MerchandiseItemWithQuantityInterface[]
      this.isSearchBarOpen = true
    },
    handleLoadedItems(items: MerchandiseItemWithQuantityInterface[]) {
      this.itemsReturned = items as MerchandiseItemWithQuantityInterface[]
      this.isSearchBarOpen = true
    },
  },
}
</script>
