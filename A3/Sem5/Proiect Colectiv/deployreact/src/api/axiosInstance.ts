import axios from "axios";

export const BASE_URL = "https://muschetarii.azurewebsites.net/api";

export const REDIRECT_PREFIX = "/ReactDeploy";

export const axiosInstance = axios.create({
  baseURL: BASE_URL,
  timeout: 7000,
  headers: {
    "Content-Type": "application/json",
  }
})

export const axiosInstanceFormData = axios.create({
  baseURL: BASE_URL,
  timeout: 7000,
  headers: {
    "Content-Type": "multipart/form-data",
  }
})