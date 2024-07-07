import axios from "axios";

const BASE_URL = "http://localhost:8080/api";

export const axiosInstance = axios.create({
  baseURL: BASE_URL,
  timeout: 1000,
  headers: {
    "Content-Type": "application/json",
  }
})