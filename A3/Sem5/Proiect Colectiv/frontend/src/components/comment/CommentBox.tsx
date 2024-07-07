import React, {useContext, useEffect, useState} from 'react';
import {Dropdown} from 'react-bootstrap';
import {Comment} from '../../model/comment';
import {AuthContext} from '../util/AuthProvider';
import {CommentContext} from './CommentProvider';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faUserPen} from "@fortawesome/free-solid-svg-icons";
import {ReactionStats, ReactionType} from "../../model/reaction.ts";
import {reactionService} from "../../service/reactions.ts";
import {ReactionBar} from "../reaction/ReactionBar.tsx";

interface CommentBoxProps {
    causeAuthorId: number;
    comment: Comment;
}

const formatDateForDisplay = (dateString) => {
    const options = {
        weekday: 'long',
        day: 'numeric',
        month: 'numeric',
        year: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
        hour12: false,
    };

    return new Date(dateString).toLocaleString('en-GB', options as Intl.DateTimeFormatOptions);
};

const CommentBox: React.FC<CommentBoxProps> = ({ causeAuthorId, comment }) => {
    const authState = useContext(AuthContext);
    const { deleteComment } = useContext(CommentContext);
    const [reactionStats, setReactionStats] = useState<ReactionStats>();
    const isUserComment = authState.account?.id === comment.userID;

    useEffect(() => {
        reactionService.getReactionsForComment(comment.id, authState.account?.token)
          .then((reactionStats: ReactionStats) => {
              setReactionStats(reactionStats);
          });
    }, [comment.id, authState.account?.token]);

    const handleDeleteComment = () => {
        deleteComment?.(comment.id);
    };

    const handleReactionChanges = (reaction: ReactionType | null, update?: boolean) => {
        if (reaction) {
            if (update) {
                reactionService.updateReactionForComment(comment.id, reaction, authState.account?.token)
                  .then(reactionStats => setReactionStats(reactionStats));
            } else {
                reactionService.addReactionToComment(comment.id, reaction, authState.account?.token)
                  .then(reactionStats => setReactionStats(reactionStats));
            }
        } else {
            reactionService.deleteReactionFromComment(comment.id, authState.account?.token)
              .then(reactionStats => setReactionStats(reactionStats));
        }
    };

    return (
        <div className={causeAuthorId === comment.userID ? "card border-primary mb-3" : "card mb-3"}>
            <div className="card-body">
                <div className="d-flex justify-content-between align-items-center">
                    <h5 className="card-title">
                        {comment.userName}
                        {' '}
                        {causeAuthorId === comment.userID &&
                            <FontAwesomeIcon icon={faUserPen} />
                        }
                    </h5>
                    {
                        isUserComment && (
                            <Dropdown>
                                <Dropdown.Toggle variant="secondary" id="dropdown-basic">
                                    <span>&#8942;</span>
                                </Dropdown.Toggle>
                                <Dropdown.Menu>
                                    <Dropdown.Item onClick={handleDeleteComment}>
                                        Delete Comment
                                    </Dropdown.Item>
                                </Dropdown.Menu>
                            </Dropdown>
                        )
                    }
                </div>
                <p className="card-text">{comment.message}</p>
                <p className="card-text">
                    <small className="text-muted">{formatDateForDisplay(comment.date)}</small>
                </p>
                {reactionStats &&
                  <div>
                      <ReactionBar
                        reactions={reactionStats}
                        interactive={authState.account?.token !== undefined}
                        onReact={handleReactionChanges}
                      />
                  </div>
                }
            </div>
        </div>
    );
};

export default CommentBox;
