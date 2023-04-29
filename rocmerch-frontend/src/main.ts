import { createApp } from 'vue'
import './styles/homepage.scss'
import App from './App.vue'

import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import {
  faCartShopping,
  faMagnifyingGlass,
  faShapes,
} from '@fortawesome/free-solid-svg-icons'

library.add(faCartShopping, faMagnifyingGlass, faShapes)

createApp(App).component('v-icon', FontAwesomeIcon).mount('#app')
