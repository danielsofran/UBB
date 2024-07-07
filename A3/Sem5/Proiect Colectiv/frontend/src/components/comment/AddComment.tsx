import React, {useContext, useRef, useState} from 'react';
import {Form, Button, Alert} from 'react-bootstrap';
import { AuthContext } from '../util/AuthProvider';
import { Comment } from '../../model/comment';
import { CommentContext } from './CommentProvider';

interface AddCommentFormProps {
    causeId: number;
}

const AddCommentForm: React.FC<AddCommentFormProps> = ({ causeId }) => {
    const authState = useContext(AuthContext);
    const { addComment } = useContext(CommentContext);
    const [comment, setComment] = useState<Comment>({
        id: -1,
        userID: authState.account?.id || -1,
        causeID: causeId,
        date: new Date(),
        message: '',
    });
    const [error, setError] = useState<string>('');
    const timeout = useRef<any>(null)

    const showError = (message: string) => {
        setError(message);
        if(timeout.current) clearTimeout(timeout.current);
        timeout.current = setTimeout(() => setError(''), 5000);
    }

    const onSubmit = async () => {
        if (comment.message.length === 0) {
            showError('Comment cannot be empty.');
            return;
        }

        if (comment.userID === -1) {
            showError('You must be logged in to comment.');
            return;
        }

        try {
            addComment?.(comment);
            setComment({
                id: -1,
                userID: authState.account?.id || -1,
                causeID: causeId,
                date: new Date(),
                message: '',
            });
        } catch (err) {
            showError(err.message)
        }
    };

    const handleCommentChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
        setComment({ ...comment, message: e.target.value });
    };

    return (
      <>
        {error.length > 0 &&
          <Alert variant="danger" className="mt-2" onClose={() => setError('')} dismissible>
            <Alert.Heading>Error</Alert.Heading>
            <p>{error}</p>
          </Alert>
        }
        <Form className="mt-4 p-4 border rounded shadow-sm">
            <Form.Group controlId="commentTextarea">
                <Form.Label>Add a Comment</Form.Label>
                <Form.Control
                    as="textarea"
                    rows={3}
                    placeholder="Type your comment here..."
                    style={{ resize: 'none' }}
                    value={comment.message}
                    onChange={handleCommentChange}
                />
            </Form.Group>
            <Button variant="primary" className="mt-2" onClick={onSubmit}>
                Add Comment
            </Button>
        </Form>
      </>
    );
};

export default AddCommentForm;
