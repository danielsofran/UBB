import React from 'react';
import CommentBox from './CommentBox';
import { Comment } from '../../model/comment';
import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';

interface CommentListProps {
  causeAuthorId: number;
  comments: Comment[];
}

const CommentList: React.FC<CommentListProps> = ({ causeAuthorId, comments }) => {
  if (comments.length === 0) {
    return (
      <Card className="mt-4 shadow">
        <Card.Body>
          <Card.Text>No comments available.</Card.Text>
        </Card.Body>
      </Card>
    );
  }

  return (
    <ListGroup className="mt-4">
      {comments.map(comment => (
        <ListGroup.Item key={comment.id} className="shadow">
          <CommentBox causeAuthorId={causeAuthorId} comment={comment} />
        </ListGroup.Item>
      ))}
    </ListGroup>
  );
};

export default CommentList;
