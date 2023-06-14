<template>
  <router-link :to="validRoute">
    <div class="item-card">
      <h3>{{ category }}</h3>
      <div class="image-container">
        <img
          type="image"
          :src="imageForCategory"
          :altForCategory="altForCategory"
          loading="lazy"
          />
        </div>
    </div>
  </router-link>
</template>

<script lang="ts">
export default {
  name: 'MerchandiseCategory',
  props: { category: String, imageForCategory: String, altForCategory: String },
  computed: {
    validRoute() {
      const converted = this.category
        ?.replace(/(\w+)\s+(\w+)/g, '$1-$2')
        .toLowerCase()
      return `/item/${converted}`
    },
  },
}
</script>
