<template>
  <nav id="page-header">
    <h1>Ripples of Change - Official Merchandise</h1>
    <ul id="shopping-utilities">
      <li class="categories medium-screen">
        <button>Categories</button>
      </li>
      <li class="categories small-screen">
        <v-icon class="v-icon" icon="fa-solid fa-shapes" />
      </li>
      <li class="search-bar">
        <input name="search-bar" type="text" />
        <v-icon class="v-icon" icon="fa-solid fa-magnifying-glass" />
      </li>
      <li class="cart">
        <button>
          <v-icon class="v-icon" icon="fa-solid fa-cart-shopping" />
        </button>
      </li>
    </ul>
  </nav>
</template>

<script lang="ts">
export default {
  name: 'Header',
}
</script>