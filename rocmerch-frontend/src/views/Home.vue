<template>
  <section>
    <Header />
    <Visualiser />
    <MerchandiseItems />
    <CopyrightFooter />
    <FeedbackBubble />
  </section>
</template>

<script lang="ts">
import Header from '../components/Header.vue'
import Visualiser from '../components/Visualiser.vue'
import MerchandiseItems from '../components/MerchandiseItems.vue'
import CopyrightFooter from '../components/CopyrightFooter.vue'
import FeedbackBubble from '../components/FeedbackBubble.vue'
import {getAllItems} from "../GLOBALS"

export default (await import('vue')).defineComponent({
  name: 'Home',
  components: {
    Header,
    Visualiser,
    MerchandiseItems,
    CopyrightFooter,
    FeedbackBubble,
  },
  async mounted() {
    const res = await getAllItems()
    if (res) {
      console.log(res.data)
      console.log("Dan... I think you just did it!")
    }
  } 
})
</script>
