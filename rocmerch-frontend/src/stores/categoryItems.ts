import { defineStore } from 'pinia'
import { MerchandiseItemInterface } from '../GLOBALS'

export const useCategoryItemsStore = defineStore('categoryItems', {
  state: () => {
    return { categoryItems: [] as MerchandiseItemInterface[] }
  },
  actions: {
    storeItems(data: MerchandiseItemInterface[]) {
      this.categoryItems = data
    },
  },
})
