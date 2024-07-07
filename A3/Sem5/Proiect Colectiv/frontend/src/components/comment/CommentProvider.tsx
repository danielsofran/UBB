import React, { useContext, useEffect, useState } from "react";
import { AuthContext } from "../util/AuthProvider";
import { commentService } from "../../service/comments";
import { userService } from "../../service/users";
import PropTypes from "prop-types";
import { Comment } from "../../model/comment";



interface CommentState {
    comments?: Comment[];
    addComment?: (comment: Comment) => void;
    deleteComment?: (commentId: number) => void;
    fetching: boolean,
    fetchingError?: Error | null,
}

const initialState: CommentState = {
    fetching: false
}

interface CommentProviderProps {
    children: PropTypes.ReactNodeLike;
    id: number;
}

export const CommentContext = React.createContext<CommentState>(initialState);

export default function CommentProvider({ children, id }: CommentProviderProps) {
    const [state, setState] = useState(initialState);
    const { comments, fetching, fetchingError } = state;
    
    const authState = useContext(AuthContext);


    const addComment = async (newComment: Comment) => {
        try {
          // Perform logic to add the new comment
          await commentService.post(newComment, authState.account?.token);
    
          // Fetch the updated comments after a new comment is added
          getCommentsEffect();
        } catch (error) {
          console.error('Error adding comment:', error);
        }
      };

      const deleteComment = async (commentId: number) => {
        try {
          await commentService.delete(commentId, authState.account?.token);
    
          getCommentsEffect();
        } catch (error) {
          console.error('Error adding comment:', error);
        }
      };

    const getCommentsEffect = () => {
        let canceled = false;
        fetchComments();

        return () => {
            canceled = true;
        }

        async function fetchComments() {
            try {
                setState({ ...state, fetching: true });
                let comments = await commentService.getCauseComments(id, authState.account?.token);
                const users = await userService.get(authState.account?.token);

                comments = comments.map(comment => ({
                    ...comment,
                    userName: users.find(user => user.id === comment.userID)?.username,
                }));

                if (!canceled) {
                    setState({ ...state, comments, fetching: false });
                }
            } catch (error) {
                setState({ ...state, fetchingError: error as Error, fetching: false });
            }

        }

    }


    useEffect(getCommentsEffect, []);

    const value = { comments, fetching, fetchingError, addComment, deleteComment };

    return (
        <CommentContext.Provider value={value}>
            {children}
        </CommentContext.Provider>
    )

}