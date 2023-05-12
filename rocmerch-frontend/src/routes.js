import Home from './views/Home.vue'
import ItemsByCategory from './views/ItemsByCategory.vue'
import ItemsByID from './views/ItemsById.vue'

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
  {
    path: '/item/:category/:id',
    name: 'itemId',
    component: ItemsByID,
  },
]

export default routes

/*

HOW TO LAYOUT WEBSITE


/ = home

/item/{category} = category layout

/item/{category}/{id} = individual item layout





OLD









/item/{category} = category layout

/item/{id} = individual item layout


*/
