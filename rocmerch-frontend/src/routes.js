import Home from './views/Home.vue'
import ItemsByCategory from './views/ItemsByCategory.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/item/:category',
    name: 'itemCategory',
    component: ItemsByCategory,
  },
]

export default routes

/*

HOW TO LAYOUT WEBSITE


/ = home

/item/{category} = category layout

/item/{id} = individual item layout


*/
