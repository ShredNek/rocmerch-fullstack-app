<template>
  <section >
    <Header />
    <Visualiser />
    <div id="page-not-found">
      <h1>
        The page you have routed either could not be found, or it doesn't exist.
      </h1>
      <h2>
        If you need any further assistance, feel free to reach out at
        danielleemusic98@gmail.com :)
      </h2>
    </div>
    <CopyrightFooter />
    <FeedbackBubble />
  </section>
</template>

<script lang="ts">
import Header from '../components/Header.vue'
import Visualiser from '../components/Visualiser.vue'
import CopyrightFooter from '../components/CopyrightFooter.vue'
import FeedbackBubble from '../components/FeedbackBubble.vue'
export default (await import('vue')).defineComponent({
  name: 'PageNotFound',
  components: { Header, Visualiser, CopyrightFooter, FeedbackBubble },
})
</script>
