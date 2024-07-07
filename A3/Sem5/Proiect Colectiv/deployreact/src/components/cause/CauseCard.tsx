import {Badge, Card, Col, ProgressBar} from "react-bootstrap";
import {Cause} from "../../model/cause";
import {useContext, useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {User} from "../../model/user";
import {userService} from "../../service/users";
import {causeService} from "../../service/causes";
import {AuthContext} from "../util/AuthProvider";
import {ReactionStats, ReactionType} from "../../model/reaction";
import {reactionService} from "../../service/reactions";
import {ReactionBar} from "../reaction/ReactionBar";

export interface CauseCardProps {
  cause: Cause;
}

export default function CauseCard({cause}: CauseCardProps) {
  const navigate = useNavigate();
  const [imageUrl, setImageUrl] = useState<string>("");
  const [user, setUser] = useState<User>();
  const [reactionStats, setReactionStats] = useState<ReactionStats>();
  const progress = (cause.moneyObtained / cause.moneyTarget) * 100;
  const authState = useContext(AuthContext);

  useEffect(() => {
    causeService.getCausePhotos(cause.id!, authState.account?.token)
    .then(photos => {
      setImageUrl(causeService.getPhotoUrl(photos[0]))
    });
    userService.getOne(cause.userId, authState.account?.token)
    .then((user: User) => {
      setUser(user);
    });

    reactionService.getReactionsForCause(cause.id!, authState.account?.token)
      .then((reactionStats: ReactionStats) => {
        setReactionStats(reactionStats);
      });
  }, [cause.id, cause.userId, authState.account?.token]);

  const handleReactionChanges = (reaction: ReactionType | null, update?: boolean) => {
    if (reaction) {
      if (update) {
        reactionService.updateReactionForCause(cause.id!, reaction, authState.account?.token)
          .then(reactionStats => setReactionStats(reactionStats));
      } else {
        reactionService.addReactionToCause(cause.id!, reaction, authState.account?.token)
          .then(reactionStats => setReactionStats(reactionStats));
      }
    } else {
      reactionService.deleteReactionFromCause(cause.id!, authState.account?.token)
        .then(reactionStats => setReactionStats(reactionStats));
    }
  };

  return (
    <Col key={cause.id} md={4} className="mb-4">
      <Card className="shadow" onClick={() => navigate(`/cause/${cause.id}`)}>
        <Card.Img variant="top" src={imageUrl}
                  style={{height: '250px', objectFit: 'cover', objectPosition: 'center'}}
        />
        <Card.Body>
          <div className="d-flex justify-content-between align-items-start">
            <Card.Title>{cause.name}</Card.Title>
            <p className="text-muted">{cause.location}</p>
          </div>
          <div className="d-flex justify-content-between align-items-start mb-3">
            <Card.Subtitle className="text-muted">Posted by {user?.username || "Unknown"}</Card.Subtitle>
            <Badge bg="primary">{cause.causeType}</Badge>
          </div>
          <Card.Text>{cause.description}</Card.Text>
          <div className="mt-3">
            <p>
              <strong>Date:</strong> {cause.date.toLocaleDateString()}
            </p>
            <p>
              <strong>Deadline:</strong> {cause.deadline.toLocaleDateString()}
            </p>
          </div>
          <ProgressBar now={progress} className="mt-3"/>
          <p className="mt-2">
            <strong>Amount Raised:</strong> ${cause.moneyObtained} of ${cause.moneyTarget}
          </p>
          {reactionStats &&
              <ReactionBar
                reactions={reactionStats}
                interactive={authState.account?.token !== undefined}
                onReact={handleReactionChanges}
              />
          }
        </Card.Body>
      </Card>
    </Col>
  );
}