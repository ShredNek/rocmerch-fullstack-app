<template>
  <div class="item">
    <div>
      <div class="image-container">
        <img :src="dynamicUrl" :alt="`image for ${item?.name}`" />
      </div>
      <p>{{ item?.name }}</p>
    </div>
    <h3 >${{ item?.price }}</h3>
  </div>
</template>

<script lang="ts">
import {MerchandiseItemInterface} from '../GLOBALS'
import {PropType} from 'vue'

export default (await import('vue')).defineComponent({
  name: "SearchResultDropdownChild",
  props: {
    item: {
      type: Object as PropType<MerchandiseItemInterface>, required: true
    }
  },
  setup(props) {
    const dynamicUrl = new URL(`../assets/images/${props.item?.image}`, import.meta.url).href
    return {
      dynamicUrl
    }
  },
})
</script>