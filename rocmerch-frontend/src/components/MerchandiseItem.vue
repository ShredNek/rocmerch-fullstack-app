<template>
  <router-link :to="individualItemRoute">
    <div :key="item.id" class="item-card">
      <div class="image-container">
        <img :alt="`Image of ${item.name}`" :src="newItemImgPath" />
      </div>
      <h3 class="individual-item">{{ cappedItemName }}</h3>
      <h2 class="price">${{ item.price }}</h2>
    </div>
  </router-link>
</template>

<script lang="ts">
import {
  MerchandiseItemInterface,
  uppercaseFirstLetter,
  generateDynamicUrl,
} from '../GLOBALS'

export default {
  name: 'MerchandiseItem',
  props: {
    item: { type: Object as () => MerchandiseItemInterface, required: true },
  },
  setup(props) {
    const newItemImgPath = generateDynamicUrl(props.item, import.meta.url)

    return {
      newItemImgPath,
    }
  },
  data() {
    return {
      cappedItemName: '',
      individualItemRoute: '',
    }
  },
  mounted() {
    this.cappedItemName = uppercaseFirstLetter(this.item.name)
    this.individualItemRoute = `/item/${this.item.category}/${this.item.id}`
  },
  methods: {
    handleDynamicUrl(item: MerchandiseItemInterface) {
      return generateDynamicUrl(item, import.meta.url)
    },
  },
}
</script>
