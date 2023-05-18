<template>
  <section id="checkout">
    <div class="user details">
      <h1>Ripples Of Change - Official Merch Checkout</h1>
      <div class="breadcrumbs">
        <p>User</p>
        <div>></div>
        <p>Payment</p>
        <div>></div>
      </div>
      <h2>User Details</h2>
      <form action="POST">
        <div class="item-container">
          <div class="form-item">
            <label for="first-name">First Name</label>
            <input name="first-name" placeholder="John" />
          </div>
          <div class="form-item">
            <label for="last-name">Last Name</label>
            <input name="last-name" placeholder="Doe" />
          </div>
        </div>
        <div class="item-container">
          <div class="form-item">
            <label for="company-name">Company Name</label>
            <input
              name="company-name"
              placeholder="Alphanumeric characters only"
            />
          </div>
        </div>
        <div class="item-container">
          <div class="form-item">
            <label for="shipping-address">Shipping Address</label>
            <input
              name="shipping-address"
              placeholder="42 Wallaby Way, Sydney NSW, Australia"
            />
          </div>
        </div>
        <div class="item-container">
          <div class="form-item">
            <label for="phone">Phone</label>
            <input name="phone" placeholder="1234 567 890" />
          </div>
          <div class="form-item">
            <label for="email-address">Email Address</label>
            <input name="email-address" placeholder="johndoe@gmail.com" />
          </div>
        </div>
        <div class="item-container">
          <div class="form-item">
            <label for="shipping-notes">Shipping notes</label>
            <textarea
              name="shipping-notes"
              placeholder="Notes for shipping, e.g. leave with lobby staff"
            />
          </div>
        </div>
      </form>
    </div>
    <div class="cart details">
      <h2>Order Summary</h2>
      <div class="items"></div>
      <div class="subtotal">
        <h3>Subtotal</h3>
        <h3>$100</h3>
      </div>
      <div class="shipping">
        <div>
          <h3>Shipping</h3>
          <h4>Standard shipping</h4>
        </div>
        <h3>FREE</h3>
      </div>
      <div class="total">
        <h3>Total</h3>
        <h2>$100</h2>
      </div>
    </div>
  </section>
</template>

<script lang="ts">
export default (await import('vue')).defineComponent({
  name: 'Checkout',
})
</script>
