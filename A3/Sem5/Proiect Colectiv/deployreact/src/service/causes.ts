import {Service} from "./base";
import {Cause, CausePhoto} from "../model/cause";
import {axiosInstance, axiosInstanceFormData, BASE_URL} from "../api/axiosInstance";
import axios, {AxiosRequestConfig} from "axios";

class CauseService extends Service<Cause> {
  constructor() {
    super('/causes');
  }

  protected deserializer(data: Record<string, never>): Cause {
    const cause = data as unknown as Cause;
    return {
      ...cause,
      deadline: new Date(cause.deadline),
      date: new Date(cause.date),
    };
  }

  async getUserCauses(token?: string): Promise<Cause[]> {
    try {
      const axiosConfig = this.createTokenHeader(token) as AxiosRequestConfig | undefined;
      const response = await axiosInstance.get(`/causes/myCauses`, axiosConfig);
      return response.data.map((data: Record<string, never>) => this.deserializer(data));
    } catch (e) {
      console.error(`Error at GET /causes/myCauses`);
      throw e;
    }
  }

  async donateToCause(causeId: number, donatedSum: number, token?: string): Promise<Cause> {
    try {
      const axiosConfig = this.createTokenHeader(token) as AxiosRequestConfig | undefined;
      const response = await axiosInstance.post(`/causes/donate/${causeId}?donatedSum=${donatedSum}`, {}, axiosConfig);
      return this.deserializer(response.data);
    } catch (e) {
      console.error(`Error at POST /causes/donate/${causeId}`);
      throw e;
    }
  }

  async getCausePhotos(id: number, token?: string): Promise<CausePhoto[]> {
    try {
      const requestInit: RequestInit = {
        mode: "cors",
        headers: {
          Authorization: `Bearer ${token}`,
        }
      };
      const request = new Request(`${BASE_URL}/causes/photos/${id}`, requestInit);
      const response = await fetch(request);
      return await response.json();
    } catch (e) {
      console.error(`Error at GET /causes/photos/${id}`);
      throw e;
    }
  }

  getPhotoUrl(photo: CausePhoto): string {
    if (!photo.id) throw new Error("Photo id is undefined");
    return `${axiosInstance.defaults.baseURL}/photos/${photo.id}`;
  }

  async uploadCausePhotos(id: number, files: string[], token?: string): Promise<CausePhoto> {
    const formData = new FormData();
    for (const file of files) {
      const index = files.indexOf(file);
      const fileName = `image${index}.jpg`;
      const response = await fetch(file);
      const blob = await response.blob();
      formData.append('files', blob, fileName);
    }
    try {
      const axiosConfig = this.createTokenHeader(token) as AxiosRequestConfig | undefined;
      const response = await axiosInstanceFormData.post(`/causes/photos/${id}`, formData, axiosConfig);
      return response.data;
    } catch (e) {
      console.error(`Error at POST /causes/photos/${id}`, files);
      throw e;
    }
  }
}


export const causeService = new CauseService();