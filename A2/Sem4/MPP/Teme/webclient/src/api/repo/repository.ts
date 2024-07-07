import {AxiosResponse} from "axios";

export interface Repository<T> {
    getAll(): Promise<T[]>;
    getById(id: number): Promise<T>;
    create(item: T): Promise<T>;
    update(item: T): Promise<T>;
    delete(id: number): Promise<AxiosResponse<any, any>>;
}