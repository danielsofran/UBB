import {Service} from "./base";
import {Reaction, ReactionStats, ReactionType} from "../model/reaction";
import {axiosInstance} from "../api/axiosInstance";

interface ReactionRequestBody {
  reactionType: ReactionType;
}

class ReactionService extends Service<Reaction> {
  constructor() {
    super("/"); // Unused because the routes are inconsistent
  }

  async getReactionsForCause(causeId: number, token?: string): Promise<ReactionStats> {
    try {
      const response = await axiosInstance.get(`/causes/${causeId}/reactions`,
        this.createTokenHeader(token));

      return response.data.map((data: Record<string, never>) => this.deserializer(data));
    } catch (e) {
      console.error(e, `/causes/${causeId}/reactions`);
      throw e;
    }
  }

  async addReactionToCause(causeId: number, type: ReactionType, token?: string): Promise<ReactionStats> {
    try {
      const requestBody: ReactionRequestBody = {
        reactionType: type,
      };

      await axiosInstance.post(`/causes/${causeId}/reaction`, requestBody, this.createTokenHeader(token));
      return this.getReactionsForCause(causeId, token);
    } catch (e) {
      console.error(e, `/causes/${causeId}/reaction`);
      throw e;
    }
  }

  async updateReactionForCause(causeId: number, type: ReactionType, token?: string): Promise<ReactionStats> {
    try {
      const requestBody: ReactionRequestBody = {
        reactionType: type,
      };

      await axiosInstance.put(`/causes/${causeId}/reaction`, requestBody, this.createTokenHeader(token));
      return this.getReactionsForCause(causeId, token);
    } catch (e) {
      console.error(e, `/causes/${causeId}/reaction`);
      throw e;
    }
  }

  async deleteReactionFromCause(causeId: number, token?: string): Promise<ReactionStats> {
    try {
      await axiosInstance.delete(`/causes/${causeId}/reaction`, this.createTokenHeader(token));
      return this.getReactionsForCause(causeId, token);
    } catch (e) {
      console.error(e, `/causes/${causeId}/reaction`);
      throw e;
    }
  }

  async getReactionsForComment(commentId: number, token?: string): Promise<ReactionStats> {
    try {
      const response = await axiosInstance.get(`/causes/comments/${commentId}/reactions`,
        this.createTokenHeader(token));

      return response.data.map((data: Record<string, never>) => this.deserializer(data));
    } catch (e) {
      console.error(e, `/causes/comments/${commentId}/reactions`);
      throw e;
    }
  }

  async addReactionToComment(commentId: number, type: ReactionType, token?: string): Promise<ReactionStats> {
    try {
      const requestBody: ReactionRequestBody = {
        reactionType: type,
      };

      await axiosInstance.post(`/comments/${commentId}/reaction`, requestBody, this.createTokenHeader(token));
      return this.getReactionsForComment(commentId, token);
    } catch (e) {
      console.error(e, `/comments/${commentId}/reaction`);
      throw e;
    }
  }

  async updateReactionForComment(commentId: number, type: ReactionType, token?: string): Promise<ReactionStats> {
    try {
      const requestBody: ReactionRequestBody = {
        reactionType: type,
      };

      await axiosInstance.put(`/comments/${commentId}/reaction`, requestBody, this.createTokenHeader(token));
      return this.getReactionsForComment(commentId, token);
    } catch (e) {
      console.error(e, `/comments/${commentId}/reaction`);
      throw e;
    }
  }

  async deleteReactionFromComment(commentId: number, token?: string): Promise<ReactionStats> {
    try {
      await axiosInstance.delete(`/comments/${commentId}/reaction`, this.createTokenHeader(token));
      return this.getReactionsForComment(commentId, token);
    } catch (e) {
      console.error(e, `/comments/${commentId}/reaction`);
      throw e;
    }
  }
}

export const reactionService = new ReactionService();
