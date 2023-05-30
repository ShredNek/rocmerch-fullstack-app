<template>
  <h1>Ripples Of Change - Official Merch Checkout</h1>
  <div class="breadcrumbs">
    <div class="">
      <p>User</p>
      <p>></p>
    </div>
    <div>
      <p>Payment</p>
      <p>></p>
    </div>
    <div>
      <p>Finish</p>
    </div>
  </div>
  <h2>User Details</h2>
  <form action="" ref="form">
    <div class="item-container">
      <div class="form-item">
        <label for="first-name">First Name</label>
        <input id="first-name" name="first-name" placeholder="John" required />
      </div>
      <div class="form-item">
        <label for="last-name">Last Name</label>
        <input id="last-name" name="last-name" placeholder="Doe" required />
      </div>
    </div>
    <div class="item-container">
      <div class="form-item">
        <label for="company-name">Company Name</label>
        <input
          id="company-name"
          name="company-name"
          placeholder="Alphanumeric characters only"
        />
      </div>
    </div>
    <div class="item-container">
      <div class="form-item">
        <label for="shipping-address">Shipping Address</label>
        <input
          id="shipping-address"
          name="shipping-address"
          placeholder="42 Wallaby Way, Sydney NSW, Australia"
          required
        />
      </div>
    </div>
    <div class="item-container">
      <div class="form-item">
        <label for="phone">Phone</label>
        <input id="phone" name="phone" placeholder="1234 567 890" required />
      </div>
      <div class="form-item">
        <label for="email-address">Email Address</label>
        <input
          id="email-address"
          name="email-address"
          placeholder="johndoe@gmail.com"
          required
        />
      </div>
    </div>
    <div class="item-container">
      <div class="form-item">
        <label for="shipping-notes">Shipping notes</label>
        <textarea
          id="shipping-notes"
          name="shipping-notes"
          placeholder="Notes for shipping, e.g. leave with lobby staff"
        />
      </div>
    </div>
    <button class="link red-glow animated" @click.prevent="handleClick">
      NEXT
    </button>
  </form>
</template>

<script lang="ts">
import { useRouter } from 'vue-router'
import { NOTIFICATION_DURATION, isValidEmail } from '../GLOBALS'

export default (await import('vue')).defineComponent({
  name: 'UserDetails',
  emits: ['invalid-input-event', 'close-self-event', 'create-order-event'],
  setup() {
    const router = useRouter()
    return {
      router
    }
  },
  methods: {
    checkFormDetailsValidity(
      deets: Array<HTMLInputElement | HTMLTextAreaElement>
    ) {
      const lengthOfPhoneNumber = 10
      let isValid = true

      deets.forEach((e) => {
        if (e.hasAttribute('required')) {
          if (e.value.length === 0 || e.value.trim() === '') {
            isValid = false
            return
          }
        }

        if (e.name === 'phone') {
          const processedVal = e.value.replace(/ /g, '')
          if (processedVal.length !== lengthOfPhoneNumber) {
            console.log('yer number sucks')
            isValid = false
            return
          }
        }

        if (e.name === 'email-address') {
          if (!isValidEmail(e.value)) {
            console.log('yer email sucks')
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
        this.createOrder()
        this.router.push('/checkout/payment')
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
    createOrder() {
      const form = this.$refs.form as HTMLFormElement
      const formControls = Array.from(
        form.querySelectorAll('[required]')
      ) as Array<HTMLInputElement | HTMLTextAreaElement>

      let orderFormValues: { [key: string]: string } = {}

      formControls.forEach((e) => {
        orderFormValues[e.name] = e.value
      })

      this.$emit('create-order-event', orderFormValues)
    },
  },
})
</script>
