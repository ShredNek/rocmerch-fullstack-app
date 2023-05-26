import axios from 'axios'
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

export const NOTIFICATION_DURATION = 3000

export const isValidEmail = (email: string) => {
  const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
  return emailRegex.test(email)
}

export async function getAllItems() {
  try {
    const url = `http://localhost:8080/items/all`
    return await axios.get(url)
  } catch (e) {
    console.log(e)
  }
}

export async function getAllItemsThatMatchSearch(query: string) {
  if (query.length === 0) return
  try {
    const url = `http://localhost:8080/items/get-by-name/${query}`
    return await axios.get(url)
  } catch (e) {
    console.log(e)
  }
}
