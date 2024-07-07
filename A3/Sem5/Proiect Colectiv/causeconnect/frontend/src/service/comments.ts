import { Service } from "./base";
import { Comment } from "../model/comment";

class CommentService extends Service<Comment>{
    constructor() {
        super('/comments')
    }

    async getCauseComments(id: number, token?: string): Promise<Comment[]> {
        try {
            const requestInit: RequestInit = {
                mode: "cors",
                headers: {
                    Authorization: `Bearer ${token}`,
                }
            };
            const request = new Request(`http://localhost:8080/api/causes/comments/${id}`, requestInit);
            const response = await fetch(request);
            return await response.json();
        }
        catch (e) {
            console.error(`Error at GET /causes/comments/${id}`);
            throw e;
        }
    }
}

export const commentService = new CommentService();