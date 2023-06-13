import Home from './views/Home.vue'
import ItemsByCategory from './views/ItemsByCategory.vue'
import ItemsByID from './views/ItemsById.vue'
import Checkout from './views/Checkout.vue'
import EditCart from './views/EditCart.vue'
import PageNotFound from './views/PageNotFound.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
  },
  {
    path: '/item/:category',
    name: 'itemCategory',
    component: ItemsByCategory,
  },
  {
    path: '/item/:category/:id',
    name: 'itemId',
    component: ItemsByID,
  },
  {
    path: '/checkout/:step',
    name: 'checkout',
    component: Checkout,
  },
  {
    path: '/edit-cart',
    name: 'editCart',
    component: EditCart,
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'pageNotFound',
    component: PageNotFound,
  },
]

export default routes

/*

HOW TO LAYOUT WEBSITE


/ = home

/item/{category} = category layout

/item/{category}/{id} = individual item layout

/item/checkout = individual item layout


*/
