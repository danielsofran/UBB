import {useParams} from 'react-router-dom'
import {useContext, useEffect, useState} from "react";
import {userService} from "../service/users";
import {User} from "../model/user";
import {Button, Badge, ProgressBar} from "react-bootstrap";
import {causeService} from "../service/causes";
import {Cause as CauseModel} from "../model/cause";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPencil, faTrash} from "@fortawesome/free-solid-svg-icons";
import {useNavigate} from "react-router-dom";
import AppCarousel from "../components/core/AppCarousel";
import {AuthContext} from "../components/util/AuthProvider";
import Comments from '../components/comment/Comments';
import DonationModal from "../components/cause/DonationModal";
import {ReactionStats, ReactionType} from "../model/reaction";
import {reactionService} from "../service/reactions";
import {ReactionBar} from "../components/reaction/ReactionBar";
import {REDIRECT_PREFIX} from "../api/axiosInstance";

export default function Cause() {
  const navigate = useNavigate();
  const id = useParams().id as unknown as number;
  const [cause, setCause] = useState<CauseModel>(new CauseModel());
  const [sources, setSources] = useState<string[]>([]);
  const [user, setUser] = useState<User>();
  const [showDonationModal, setShowDonationModal] = useState(false);
  const [reactionStats, setReactionStats] = useState<ReactionStats>();
  const progress = (cause.moneyObtained / cause.moneyTarget) * 100;
  const authState = useContext(AuthContext);


  useEffect(() => {
    causeService.getCausePhotos(id, authState.account?.token)
      .then(photos => {
        setSources(photos.map(causeService.getPhotoUrl))
      });
    causeService.getOne(id, authState.account?.token)
      .then(cause => {
        setCause(cause)
        userService.getOne(cause.userId, authState.account?.token).then((user: User) => {
          setUser(user);
        });
      });

    reactionService.getReactionsForCause(id, authState.account?.token)
      .then((reactionStats: ReactionStats) => {
        setReactionStats(reactionStats);
      });
  }, [id, authState.account?.token])

  const deleteCause = async () => {
    causeService.delete(id, authState.account?.token).then(() => {
      navigate('/profile/my-causes')
    }).catch(err => {
      alert(err)
    });
  };

  const handleReactionChanges = (reaction: ReactionType | null, update?: boolean) => {
    if (reaction) {
      if (update) {
        reactionService.updateReactionForCause(id, reaction, authState.account?.token)
          .then(reactionStats => setReactionStats(reactionStats));
      } else {
        reactionService.addReactionToCause(id, reaction, authState.account?.token)
          .then(reactionStats => setReactionStats(reactionStats));
      }
    } else {
      reactionService.deleteReactionFromCause(id, authState.account?.token)
        .then(reactionStats => setReactionStats(reactionStats));
    }
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6">
          <AppCarousel sources={sources} setSources={setSources} editable={false}/>
        </div>
        <div className="col-md-6">
          <div className="d-flex justify-content-between align-items-start">
            <h1>{cause.name || ''}</h1>
            <p className="text-muted">{cause.location}</p>
          </div>
          <div className="d-flex justify-content-between align-items-start mb-3">
            <p className="text-muted">Posted by {user?.username || "Unknown"}</p>
            <h4><Badge bg="primary">{cause.causeType || ''}</Badge></h4>
          </div>
          <p>{cause.description || ''}</p>
          <div className="mt-3">
            <p>
              <strong>Date:</strong> {cause.date?.toLocaleDateString() || ''}
            </p>
            <p>
              <strong>Deadline:</strong> {cause.deadline?.toLocaleDateString()}
            </p>
          </div>
          <div className="mt-2">
            <ProgressBar now={progress} className="mt-3"/>
            <strong>Amount Raised:</strong> ${cause.moneyObtained >= 0 ? cause.moneyObtained : ''} of
            ${cause.moneyTarget >= 0 ? cause.moneyTarget : ''}
          </div>
          {reactionStats &&
            <div>
              <ReactionBar
                reactions={reactionStats}
                interactive={authState.account?.token !== undefined}
                onReact={handleReactionChanges}
              />
            </div>
          }
          {authState.account?.id === cause.userId &&
              <div className="d-flex justify-content-around align-items-center mb-3">
                  <Button variant="danger" className="mt-3" onClick={deleteCause}>
                      <FontAwesomeIcon icon={faTrash} className="pe-1"/>
                      Delete
                  </Button>
                  <Button variant="primary" className="mt-3" onClick={() => navigate(`/cause/${cause.id}/edit`)}>
                      <FontAwesomeIcon icon={faPencil} className="pe-1"/>
                      Edit
                  </Button>
              </div>
          }
          {authState.account != undefined && authState.account?.id !== cause.userId &&
              <div className="d-flex justify-content-center align-items-center mb-3">
                  <Button variant="primary" className="mt-3 w-100" onClick={() => setShowDonationModal(true)}>
                      Donate
                  </Button>
              </div>
          }
        </div>
      </div>

      <DonationModal show={showDonationModal} setShow={setShowDonationModal} causeId={id} setCause={setCause}/>

      <div className='comments-container'>
        <div>
          <Comments causeId={id} causeAuthorId={cause.userId} />
        </div>
      </div>
    </div>
  )
}