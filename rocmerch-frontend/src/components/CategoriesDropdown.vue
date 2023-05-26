<template>
  <nav id="categories-dropdown" :class="['dropdown', isOpen ? 'open' : null]">
    <ul ref="categories-dropdown" >
      <router-link to="/item/clothing">
        <li class="list-item">Clothing</li>
      </router-link>
      <router-link to="/item/accessories">
        <li class="list-item">Accessories</li>
      </router-link>
      <router-link to="/item/music">
        <li class="list-item">Music</li>
      </router-link>
      <router-link to="/item/music-accessories">
        <li class="list-item">Music Accessories</li>
      </router-link>
    </ul>
  </nav>
</template>

<script lang="ts">
export default (await import('vue')).defineComponent({
  name: 'CategoriesDropdown',
  props: {
    isOpen: {
      type: Boolean, required: true
    }
  }
})
</script>
