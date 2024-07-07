import AddCommentForm from "./AddComment";
import CommentProvider from "./CommentProvider";
import CommentSection from "./CommentSection";

export default function Comments({ causeId, causeAuthorId }: { causeId: number, causeAuthorId: number }) {
  return (
    <>
      <CommentProvider id={causeId}>
        <AddCommentForm causeId={causeId} />
        <CommentSection causeAuthorId={causeAuthorId} />
      </CommentProvider>
    </>
  );
}
