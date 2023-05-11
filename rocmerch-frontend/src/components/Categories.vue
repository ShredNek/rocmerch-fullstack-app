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
import Header from './Header.vue'
import Visualiser from './Visualiser.vue'
import MerchandiseItems from './MerchandiseItems.vue'
import CopyrightFooter from './CopyrightFooter.vue'
import FeedbackBubble from './FeedbackBubble.vue'

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
