<template>
  <div class="merchandise-category">
    <h3>{{ category }}</h3>
    <div class="image-container">
      <img
        type="image"
        :src="imageForCategory"
        :altForCategory="altForCategory"
      />
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: 'MerchandiseCategory',
  props: { category: String, imageForCategory: String, altForCategory: String },
}
</script>
