import {axiosInstance} from "../api/axiosInstance";
import {AxiosRequestConfig} from "axios";

/**
 * Abstract class for services
 * @param T the type of the data
 * @param url the url of the service, it will be appended to the base url of the axios instance
 *
 * Simplifies the use of axios to make requests for CRUD operations
 * Avoids code duplication by using generics
 * Allows to override the serializer and deserializer functions to convert the data to and from the format used by the server
 * Adds error handling by automatically logging the error along with the url and throwing it again, for custom error handling
 */
export abstract class Service<T> {
  protected url: string;

  protected serializer(data: T): Record<string, never> {
    return data as Record<string, never>
  }
  protected deserializer(data: Record<string, never>): T {
    return data as T
  }

  protected constructor(url: string) {
    this.url = url;
  }

  protected createTokenHeader(token?: string): AxiosRequestConfig | undefined {
    if (token) {
      return  {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    }
  }

  async get(token?: string): Promise<T[]> {
    try {
      const axiosConfig = this.createTokenHeader(token);
      const response = await axiosInstance.get(this.url, axiosConfig);
      return response.data.map((data: Record<string, never>) => this.deserializer(data));
    } catch (e) {
      console.error(e, this.url);
      throw e;
    }
  }

  async getOne(id: number, token?: string): Promise<T> {
    try {
      const axiosConfig = this.createTokenHeader(token);
      const response = await axiosInstance.get(`${this.url}/${id}`, axiosConfig);
      return this.deserializer(response.data);
    } catch (e) {
      console.error(`Error at GET ${this.url}/${id}`);
      throw e;
    }
  }

  async post(data: T, token?: string): Promise<number> {
    try {
      const axiosConfig = this.createTokenHeader(token);
      const response = await axiosInstance.post(this.url, this.serializer(data), axiosConfig);
      return response.data;
    } catch (e) {
      console.error(`Error at POST ${this.url}`, data)
      throw e;
    }
  }

  async put(id: number, data: T, token?: string): Promise<number> {
    try {
      const axiosConfig = this.createTokenHeader(token);
      const response = await axiosInstance.put(`${this.url}/${id}`, this.serializer(data), axiosConfig);
      return response.data;
    } catch (e) {
      console.error(`Error at PUT ${this.url}/${id}`, data);
      throw e;
    }
  }

  async delete(id: number, token?: string): Promise<void> {
    try {
      const axiosConfig = this.createTokenHeader(token);
      await axiosInstance.delete(`${this.url}/${id}`, axiosConfig);
    } catch (e) {
      console.error(`Error at DELETE ${this.url}/${id}`);
      throw e;
    }
  }
}