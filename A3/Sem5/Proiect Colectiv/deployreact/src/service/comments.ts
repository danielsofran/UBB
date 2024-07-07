import { Service } from "./base";
import { Comment } from "../model/comment";
import {axiosInstance} from "../api/axiosInstance";
import axios, {AxiosRequestConfig} from "axios";

class CommentService extends Service<Comment>{
    constructor() {
        super('/comments')
    }

    async getCauseComments(id: number, token?: string): Promise<Comment[]> {
        try {
            const axiosConfig = this.createTokenHeader(token) as AxiosRequestConfig | undefined;
            const response = await axiosInstance.get(`/causes/comments/${id}`, axiosConfig);
            return response.data.map((data: Record<string, never>) => this.deserializer(data));
        }
        catch (e) {
            console.error(`Error at GET /causes/comments/${id}`);
            throw e;
        }
    }
}

export const commentService = new CommentService();