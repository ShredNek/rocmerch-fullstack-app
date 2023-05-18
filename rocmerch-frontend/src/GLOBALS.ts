export interface MerchandiseItemInterface {
  id: number
  name: string
  itemDescription: string
  price: number
  category: string
}

export interface MerchandiseItemWithQuantityInterface {
  quantity: number
  merchandiseItem: MerchandiseItemInterface
}

export interface MerchandiseOrderInterface {
  totalPrice: number
  nameForOrder: string
  emailForOrder: string
  merchItemsInCart: MerchandiseItemWithQuantityInterface[]
}

export const uppercaseFirstLetter = (word: string | string[]) => {
  return !Array.isArray(word)
    ? word.toUpperCase().slice(0, 1).concat(word.slice(1))
    : 'ARRAY DETECTED. CONTACT DANIELLEEMUISC98@GMAIL.COM TO RESOLVE THIS BUG'
}

export const removeHyphen = (word: string) => {
  return word.replace('-', ' ')
}
