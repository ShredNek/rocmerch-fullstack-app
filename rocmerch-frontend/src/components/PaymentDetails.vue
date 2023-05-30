<template>
  <h1>Ripples Of Change - Official Merch Checkout</h1>
  <div class="breadcrumbs">
    <router-link class="filled" to="/checkout/user">
      <p>User</p>
      <p>></p>
    </router-link>
    <div>
      <p>Payment</p>
      <p>></p>
    </div>
    <div>
      <p>Finish</p>
    </div>
  </div>
  <h2>Payment Details</h2>
  <form action="" ref="form">
    <div class="item-container">
      <div class="form-item">
        <label for="card-number">Card Number</label>
        <input
          id="card-number"
          name="card-number"
          placeholder="•••• •••• •••• ••••"
          maxlength="16"
          required
        />
      </div>
    </div>
    <div class="item-container">
      <div class="form-item">
        <label for="expiration">Expiration (MM/YY)</label>
        <input
          id="expiration"
          name="expiration"
          :placeholder="`MM / YY, eg 09/${date}`"
          maxlength="5"
          required
        />
      </div>
      <div class="form-item">
        <label for="card-security-code">Card Security Code</label>
        <input
          id="card-security-code"
          name="card-security-code"
          placeholder="CSC, e.g. 123"
          maxlength="3"
          required
        />
      </div>
    </div>
    <button class="link red-glow animated" @click.prevent="handleClick">
      PAY NOW
    </button>
  </form>
  <p class="disclaimer">
    NOTE: The data you enter here can - and should - be fake; None of it will be
    saved to the database, cause y'know, that would make me a criminal for
    taking bank credit card details and then not following through with any
    shipment of my hypothetical stock. Anyways, continue through!
  </p>
</template>

<script lang="ts">
import { NOTIFICATION_DURATION } from '../GLOBALS'
import { useRouter } from 'vue-router'

export default (await import('vue')).defineComponent({
  name: 'PaymentDetails',
  emits: ['invalid-input-event', 'close-self-event', 'send-email-event'],

  setup() {
    const router = useRouter()
    let date = new Date().getFullYear().toString().substring(2)
    return {
      router,
      date,
    }
  },
  methods: {
    checkFormDetailsValidity(
      deets: Array<HTMLInputElement | HTMLTextAreaElement>
    ) {
      let isValid = true

      deets.forEach((e) => {
        if (e.hasAttribute('required')) {
          if (e.value.length !== e.maxLength) {
            isValid = false
            return
          }
        }

        if (e.name === 'expiration') {
          if (e.value.charAt(2) !== '/') {
            console.log('gaddeem')
            isValid = false
            return
          }

          const userYearInput = Number(e.value.substring(3))

          if (userYearInput < Number(this.date)) {
            console.log('use a valid year ya gronk')
            isValid = false
            return
          }
        }
      })
      return isValid
    },
    handleClick() {
      const form = this.$refs.form as HTMLFormElement
      const formControls = Array.from(
        form.querySelectorAll('input, textarea')
      ) as Array<HTMLInputElement | HTMLTextAreaElement>

      if (this.checkFormDetailsValidity(formControls)) {
        this.$emit("send-email-event")
        this.router.push('/checkout/finish')
      } else {
        this.handleInvalidDetails()
      }
    },
    highlightRequiredAreas() {
      const form = this.$refs.form as HTMLFormElement
      const formControls = Array.from(
        form.querySelectorAll('[required]')
      ) as Array<HTMLInputElement | HTMLTextAreaElement>

      formControls.forEach((e) => {
        e.classList.add('warning', 'red-glow')

        setTimeout(
          () => e.classList.remove('warning', 'red-glow'),
          NOTIFICATION_DURATION
        )
      })
    },
    handleInvalidDetails() {
      this.$emit('invalid-input-event')
      this.highlightRequiredAreas()

      setTimeout(() => {
        this.$emit('close-self-event')
      }, NOTIFICATION_DURATION)
    },
  },
})
</script>
