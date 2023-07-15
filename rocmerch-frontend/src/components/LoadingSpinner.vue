<template>
  <div class="loading-parent">
      <div class="loading-spinner">
        <div class="lds-default">
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
          <div></div>
        </div>
      </div>
    <p class="loading-notice">
      Searching the database... <br /><br />
      Considering this is a student project, the database has probably spun down, and
      will take 3 mins to load your results <br /><br />Thank you for your patience
    </p>
  </div>
</template>

<script lang="ts">
export default (await import('vue')).defineComponent({
  name: 'LoadingSpinner',
})
</script>
