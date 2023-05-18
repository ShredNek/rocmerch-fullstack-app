import { defineStore } from 'pinia'
import { MerchandiseItemInterface } from '../GLOBALS'

export const useCategoryItemsStore = defineStore('categoryItems', {
  state: () => {
    return { categoryItems: [] as MerchandiseItemInterface[] }
  },
  persist: true,
  actions: {
    storeItems(data: MerchandiseItemInterface[]) {
      this.categoryItems = data
    },
  },
})

/*

1st store: save category of items

*/
