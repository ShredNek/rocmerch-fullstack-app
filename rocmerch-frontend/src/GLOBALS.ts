export interface MerchandiseItemInterface {
  id: Number
  name: String
  itemDescription: String
  price: Number
  category: String
}

export const uppercaseFirstLetter = (word: String | String[]) => {
  return !Array.isArray(word)
    ? word.toUpperCase().slice(0, 1).concat(word.slice(1))
    : 'ARRAY DETECTED. CONTACT DANIELLEEMUISC98@GMAIL.COM TO RESOLVE THIS BUG'
}

export const removeHyphen = (word: String) => {
  return word.replace('-', ' ')
}
