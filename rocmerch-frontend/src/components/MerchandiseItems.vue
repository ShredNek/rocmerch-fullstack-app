<script lang="ts">
import MerchandiseCategory from './MerchandiseCategory.vue'
import PickImage from '../assets/images/merchCategories/BlackPicks.jpeg'
import ToteImage from '../assets/images/merchCategories/BlackToteBag.jpg'
import ShirtImage from '../assets/images/merchCategories/BlackTshirt.png'
import VinylImage from '../assets/images/merchCategories/BlackVinyl.webp'
export default {
  created() {
    console.log(PickImage)
  },
  name: 'MerchandiseItems',
  components: { MerchandiseCategory },
  props: [PickImage],
  data() {
    return {
      pickCategory: PickImage,
      accessoriesCategory: ToteImage,
      clothesCategory: ShirtImage,
      recordingsCategory: VinylImage,
    }
  },
}
</script>

<template>
  <section id="merchandise-items">
    <h2>Shop by category</h2>
    <div id="categories">
      <MerchandiseCategory
        category="Clothes"
        :imageForCategory="clothesCategory"
        :altForCategory="'Black Picks for Sale'"
      />
      <MerchandiseCategory
        category="Accessories"
        :imageForCategory="accessoriesCategory"
        :altForCategory="'Black Picks for Sale'"
      />
      <MerchandiseCategory
        category="Physical Recordings"
        :imageForCategory="recordingsCategory"
        :altForCategory="'Black Picks for Sale'"
      />
      <MerchandiseCategory
        category="Music Accessories"
        :imageForCategory="pickCategory"
        :altForCategory="'Black Picks for Sale'"
      />
    </div>
  </section>
</template>
