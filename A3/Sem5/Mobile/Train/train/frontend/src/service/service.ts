import {axiosInstance} from "./axiosInstance";

export class MenuItem {
  code: number
  name: string
  quantity: number = 0
}

export const createOrder = async (order: MenuItem) => {
  return await axiosInstance.post("/item", order)
}

