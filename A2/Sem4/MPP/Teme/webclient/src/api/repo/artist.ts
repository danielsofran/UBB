import {Repository} from "./repository";
import {Artist} from "../../models/artist";
import {axiosInstance} from "../axios";
import {AxiosResponse} from "axios";

export class ArtistRepository implements Repository<Artist>
{
    getAll(): Promise<Artist[]> {
        return axiosInstance.get<Artist[]>(`/artists`)
            .then(response => Artist.deserializeArray(response.data));
    }

    getById(id: number): Promise<Artist> {
        return axiosInstance.get<Artist>(`/artists/${id}`)
            .then(response => Artist.deserialize(response.data));
    }

    create(item: Artist): Promise<Artist> {
        return axiosInstance.post<Artist>(`/artists`, item)
            .then(response => Artist.deserialize(response.data));
    }

    update(item: Artist): Promise<Artist> {
        return axiosInstance.put<Artist>(`/artists/${item.id}`, item)
            .then(response => Artist.deserialize(response.data));
    }

    delete(id: number): Promise<AxiosResponse<any, any>> {
        return axiosInstance.delete(`/artists/${id}`);
    }
}