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
import PickImage from '../assets/images/ROC_BlackPicks.jpg'
import ToteImage from '../assets/images/ROC_BlackToteBag.jpg'
import ShirtImage from '../assets/images/ROC_BlackTshirt.jpg'
import VinylImage from '../assets/images/ROC_BlackVinyl.jpg'
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
