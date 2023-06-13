import axios from 'axios'
import { useUserCartAndDataStore } from './stores/userCartAndData'
export interface MerchandiseItemInterface {
  id: number
  name: string
  itemDescription: string
  price: number
  category: string
  image: string
}

export interface MerchandiseItemWithQuantityInterface {
  quantity: number
  merchandiseItem: MerchandiseItemInterface
}

export interface MerchandiseOrderInterface {
  totalPrice: number
  nameForOrder: string
  emailForOrder: string
  addressForOrder: string
  phoneForOrder: string
  merchItemsInCart: MerchandiseItemWithQuantityInterface[]
}

const config = {
  withCredentials: true,
}

export const NOTIFICATION_DURATION = 3000

export const uppercaseFirstLetter = (word: string | string[]) => {
  return !Array.isArray(word)
    ? word.toUpperCase().slice(0, 1).concat(word.slice(1))
    : 'ARRAY DETECTED. CONTACT DANIELLEEMUISC98@GMAIL.COM TO RESOLVE THIS BUG'
}

export const removeHyphen = (word: string) => {
  return word.replace('-', ' ')
}

export const isValidEmail = (email: string) => {
  const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
  return emailRegex.test(email)
}

export async function getAllItems() {
  try {
    const url = `${import.meta.env.VITE_BACKEND_SERVER_ENDPOINT}/items/all`
    return await axios.get(url)
  } catch (e) {
    console.log(e)
  }
}

export async function getAllItemsThatMatchSearch(query: string) {
  if (query.length === 0) return
  try {
    const url = `${
      import.meta.env.VITE_BACKEND_SERVER_ENDPOINT
    }/items/get-by-name/${query}`
    return await axios.get(url)
  } catch (e) {
    console.error(e)
  }
}

export function generateDynamicUrl(
  item: MerchandiseItemInterface,
  currUrl: string
) {
  return new URL(`../assets/images/items/${item.image}`, currUrl).href
}

export function extractNumberFromString(string: string) {
  const regex = /\d+(\.\d{1,7})?/
  const matches = string.match(regex)

  if (matches && matches.length > 0) {
    const numberString = matches[0]
    const number = parseFloat(numberString)
    return number
  }

  return null
}

export async function sendOrderAndReturnOrderId() {
  const order = useUserCartAndDataStore().ENTIRE_ORDER
  try {
    const idResponse = await axios.post(
      `${import.meta.env.VITE_BACKEND_SERVER_ENDPOINT}/orders/new`,
      order
    )
    return extractNumberFromString(idResponse.data)
  } catch (error) {
    console.error(error)
  }
}

export async function sendEmail(orderId: string | number) {
  try {
    await axios.post(
      `${
        import.meta.env.VITE_BACKEND_SERVER_ENDPOINT
      }/orders/send-email-for-order-id/${orderId}`
    )
    console.log(`email sent to id ${orderId}!`)
  } catch (error) {
    console.error(error)
  }
}

export async function sendFeedback(feedback: string) {
  try {
    await axios.post(
      `${import.meta.env.VITE_BACKEND_SERVER_ENDPOINT}/feedback/new`,
      {
        feedback: feedback,
      }
    )
  } catch (e) {
    console.error(e)
  }
}

export async function getCategoryOfItems(category: string | string[]) {
  const url = `${
    import.meta.env.VITE_BACKEND_SERVER_ENDPOINT
  }/items/get-by-category/${category}`
  const response = await axios.get(url, config)
  return response.data
}
