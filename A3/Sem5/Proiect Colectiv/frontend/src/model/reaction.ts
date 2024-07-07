export type ReactionType = "LOVE" | "LIKE" | "SAD" | "ANGRY"

export interface Reaction {
  type: ReactionType;
  count: number;
  userReacted: boolean;
}

export type ReactionStats = Reaction[];
