<template>
  <div
    :class="[
      'notification',
      'added-to-cart',
      'red-glow',
      'strong',
      checkIfOpenAndSetStyles ? 'open' : '',
    ]"
  >
    <h2>
      Successfully added {{ quantityProtectedWhileOpen }}
      {{ calculateIfPluralProtectedWhileOpen }} to your cart!
    </h2>
  </div>
</template>

<script lang="ts">
export default (await import('vue')).defineComponent({
  name: 'AddedToCart',
  data() {
    return {
      itemNameCheckedForPlural: '',
      setQuantity: 0,
    }
  },
  props: {
    itemQuantity: { type: Number, required: true },
    itemName: { type: String, required: true },
    isOpen: { type: Boolean, required: true },
  },
  mounted() {
    this.itemNameCheckedForPlural = this.itemName
  },
  computed: {
    calculateIfPluralProtectedWhileOpen() {
      if (this.isOpen) return this.itemNameCheckedForPlural
      // checks if the first string of the sentence is 'single'
      if (this.itemName.split(' ')[0] === 'Single') {
        this.itemNameCheckedForPlural = 'track'
        if (this.itemQuantity > 1) {
          this.itemNameCheckedForPlural =
            this.itemNameCheckedForPlural.concat('s')
          return this.itemNameCheckedForPlural
        }
        return this.itemNameCheckedForPlural
      }

      if (this.itemName !== undefined && this.itemQuantity > 1) {
        this.itemNameCheckedForPlural = this.itemName.concat('s')
        return this.itemNameCheckedForPlural
      }

      this.itemNameCheckedForPlural = this.itemName
      return this.itemNameCheckedForPlural
    },
    quantityProtectedWhileOpen() {
      if (this.isOpen) {
        let quantityShown = this.setQuantity
        return quantityShown
      }
      this.setQuantity = this.itemQuantity
      return this.setQuantity
    },
    checkIfOpenAndSetStyles() {
      return this.isOpen ? true : false
    },
  },
})
</script>
