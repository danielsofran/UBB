import {useContext} from "react";
import {CauseContext} from "../components/cause/CauseProvider";
import {Alert} from "react-bootstrap";
import Loader from "../components/core/Loader";
import CauseGrid from "../components/cause/CauseGrid";
import {AuthContext} from "../components/util/AuthProvider";

export default function GeneralCauses() {
  const {causes, fetching, fetchingError} = useContext(CauseContext);
  const { account } = useContext(AuthContext);

  return (
    <>
      {fetching && <Loader text="Fetching causes"/>}
      {fetchingError &&
          <Alert variant="danger">
              <Alert.Heading>Something went wrong while fetching causes</Alert.Heading>
          </Alert>
      }
      {causes && <CauseGrid causes={account ? causes.filter(cause => cause.userId != account.id) : causes}/>}
    </>
  );
}