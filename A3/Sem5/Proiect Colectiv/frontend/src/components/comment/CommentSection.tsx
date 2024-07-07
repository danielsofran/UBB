import React, { useContext } from 'react';
import { Alert } from 'react-bootstrap';
import { CommentContext } from './CommentProvider';
import Loader from '../core/Loader';
import CommentList from './CommentList';

interface CommentSectionProps {
  causeAuthorId: number;
}

const CommentSection: React.FC<CommentSectionProps> = ({ causeAuthorId }) => {
  const { comments, fetching, fetchingError } = useContext(CommentContext);

  return (
    <>
      {fetching && <Loader text="Fetching comments" />}
      {fetchingError && (
        <Alert variant="danger">
          <Alert.Heading>Something went wrong while fetching comments</Alert.Heading>
        </Alert>
      )}
      {comments && (
        <CommentList causeAuthorId={causeAuthorId} comments={comments} />
      )}
    </>
  );
};

export default CommentSection;
