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

export default (await import('vue')).defineComponent({
  name: 'Home',
  components: {
    Header,
    Visualiser,
    MerchandiseItems,
    CopyrightFooter,
    FeedbackBubble,
  },
})
</script>
