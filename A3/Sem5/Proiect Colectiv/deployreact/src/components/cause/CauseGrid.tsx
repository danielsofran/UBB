import {Alert, Row} from "react-bootstrap";
import CauseCard from "./CauseCard";
import {Cause} from "../../model/cause";

interface CauseGridProps {
  causes: Cause[]
}

export default function CauseGrid({causes}: CauseGridProps) {
  return (
    <Row>
      {causes.length === 0 &&
          <Alert variant="info">
              <Alert.Heading>You haven't added any causes so far.</Alert.Heading>
          </Alert>
      }
      {causes && causes.map(cause => <CauseCard key={cause.id} cause={cause}/>)}
    </Row>
  );
}