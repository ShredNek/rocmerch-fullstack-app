import { defineStore } from 'pinia'
import {
  MerchandiseItemWithQuantityInterface,
  MerchandiseOrderInterface,
} from '../GLOBALS'

export const useUserCartAndDataStore = defineStore('userCartAndData', {
  state: () => {
    return {
      merchItemsInCart: [] as MerchandiseItemWithQuantityInterface[],
      emailForOrder: '',
      nameForOrder: '',
      totalPrice: 0,
      ENTIRE_ORDER: {} as MerchandiseOrderInterface,
    }
  },
  persist: true,
  actions: {
    storeOneItem(i: MerchandiseItemWithQuantityInterface) {
      const isThere = this.merchItemsInCart.find(
        (item) => item.merchandiseItem.id == i.merchandiseItem.id
      )
      isThere ? null : this.merchItemsInCart.push(i)
    },
    removeItemById(passedId: number) {
      const indexToRemove = this.merchItemsInCart.findIndex(
        (item) => item.merchandiseItem.id === passedId
      )

      if (indexToRemove !== -1) {
        this.merchItemsInCart.splice(indexToRemove, 1)
        return `Removed object with ${passedId}`
      } else {
        return `Could not find object with id of ${passedId}`
      }
    },
    checkIfItemPresent(i: MerchandiseItemWithQuantityInterface) {
      const isThere = this.merchItemsInCart.find(
        (item) => item.merchandiseItem.id == i.merchandiseItem.id
      )
      if (isThere) {
        // console.log(`item of ${i.merchandiseItem.id} is present already`)
        return true
      } else {
        // console.log(`item of ${i.merchandiseItem.id} isn't here`)
        return false
      }
    },
    getItemById(id: number) {
      return this.merchItemsInCart.forEach((item) => {
        if (item.merchandiseItem.id === id) {
          return item.merchandiseItem
        }
      })
    },
    addQuantityToItem(i: MerchandiseItemWithQuantityInterface) {
      const matchingItem = this.merchItemsInCart.find(
        (item) => item.merchandiseItem.id === i.merchandiseItem.id
      )

      if (matchingItem) {
        const newQuantity = matchingItem.quantity + i.quantity
        matchingItem.quantity = newQuantity

        return `The object ${matchingItem.merchandiseItem.id} now has a quantity of ${newQuantity}`
      } else {
        console.log(
          `Could not find item with the same id of ${i.merchandiseItem.id}. Please push the item first.`
        )
      }
    },
    storeUserEmail(email: string) {
      this.emailForOrder = email
    },
    storeUserName(name: string) {
      this.nameForOrder = name
    },
    calculateTotalPrice() {
      const total = this.merchItemsInCart.reduce(
        (accumulator, currMerchItem) => {
          const itemTotal =
            currMerchItem.merchandiseItem.price * currMerchItem.quantity
          return accumulator + itemTotal
        },
        0
      )

      return parseFloat(total.toFixed(2)) // Convert to string with 2 decimal places
    },
    storeEntireOrder(order: MerchandiseOrderInterface) {
      this.ENTIRE_ORDER = order
    },
    wipeAllState() {
      this.merchItemsInCart = [] as MerchandiseItemWithQuantityInterface[]
      this.emailForOrder = ''
      this.nameForOrder = ''
      this.totalPrice = 0
      this.ENTIRE_ORDER = {} as MerchandiseOrderInterface
    },
  },
})
