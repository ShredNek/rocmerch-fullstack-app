import { createApp } from 'vue'
import { createRouter, createWebHistory, RouterLink } from 'vue-router'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import './styles/homepage.scss'
import App from './App.vue'
import routes from './routes.js'

import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import {
  faCartShopping,
  faMagnifyingGlass,
  faShapes,
  faXmark,
  faPlus,
  faMinus,
} from '@fortawesome/free-solid-svg-icons'

library.add(
  faCartShopping,
  faMagnifyingGlass,
  faShapes,
  faXmark,
  faPlus,
  faMinus
)

const router = createRouter({
  history: createWebHistory('/'),
  routes,
})

const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)

createApp(App)
  .use(router)
  .use(pinia)
  .component('v-icon', FontAwesomeIcon)
  .component('router-link', RouterLink)
  .mount('#app')
