import CauseGrid from "../components/cause/CauseGrid.tsx";
import {useContext, useEffect, useState} from "react";
import {AuthContext} from "../components/util/AuthProvider.tsx";
import {isAxiosError} from "axios";
import {Alert} from "react-bootstrap";
import {Navigate} from 'react-router-dom'
import {causeService} from "../service/causes.ts";
import {Cause} from "../model/cause.ts";

export default function MyCauses() {
  const [userCauses, setUserCauses] = useState([] as Cause[]);
  const {account} = useContext(AuthContext);

  useEffect(() => {
    fetchUserCauses();
  }, []);

  return (
    <>
      <h2>My Causes</h2>
      {!account || !account.token &&
          <>
              <Alert variant="danger">
                  <Alert.Heading>.You must be logged in to see your causes.</Alert.Heading>
              </Alert>
              <Navigate to="/login"/>
          </>
      }
      <CauseGrid causes={userCauses}/>
    </>
  );

  async function fetchUserCauses() {
    if (!account || !account.token)
      return;

    try {
      setUserCauses(await causeService.getUserCauses(account.token));
    } catch (error) {
      if (isAxiosError(error)) {
        console.error(`Error: got response code ${error.code} at GET /causes/myCauses`);
        return;
      }

      console.error(`Error at GET /api/causes/myCauses`);
      throw error;
    }
  }

}