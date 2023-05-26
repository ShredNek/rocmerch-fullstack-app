<template>
  <section class="shopping-section">
    <h2 class="section-header">Shop by category</h2>
    <div id="categories" class="item-card-container">
      <MerchandiseCategory
        category="Clothing"
        :imageForCategory="clothesCategory"
        :altForCategory="'Black Picks for Sale'"
      />
      <MerchandiseCategory
        category="Accessories"
        :imageForCategory="accessoriesCategory"
        :altForCategory="'Black Picks for Sale'"
      />
      <MerchandiseCategory
        category="Music"
        :imageForCategory="MusicCategory"
        :altForCategory="'Black Picks for Sale'"
      />
      <MerchandiseCategory
        category="Music Accessories"
        :imageForCategory="musicAccessoriesCategory"
        :altForCategory="'Black Picks for Sale'"
      />
    </div>
  </section>
</template>

<script lang="ts">
import MerchandiseCategory from './MerchandiseCategory.vue'
import PickImage from '../assets/images/merchCategories/BlackPicks.jpeg'
import ToteImage from '../assets/images/merchCategories/BlackToteBag.jpg'
import ShirtImage from '../assets/images/merchCategories/BlackTshirt.png'
import VinylImage from '../assets/images/merchCategories/BlackVinyl.webp'
export default {
  name: 'MerchandiseItems',
  components: { MerchandiseCategory },
  props: [PickImage],
  data() {
    return {
      musicAccessoriesCategory: PickImage,
      accessoriesCategory: ToteImage,
      clothesCategory: ShirtImage,
      MusicCategory: VinylImage,
    }
  },
}
</script>
