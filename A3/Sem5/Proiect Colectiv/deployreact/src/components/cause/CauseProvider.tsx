import {Cause} from "../../model/cause";
import React, {useContext, useEffect, useState} from "react";
import {causeService} from "../../service/causes";
import PropTypes from "prop-types";
import {userService} from "../../service/users";
import {AuthContext} from "../util/AuthProvider";

interface CausesState {
  causes?: Cause[],
  fetching: boolean,
  fetchingError?: Error | null
}

const initialState: CausesState = {
  fetching: false
}

interface CauseProviderProps {
  children: PropTypes.ReactNodeLike
}

export const CauseContext = React.createContext<CausesState>(initialState);

export default function CauseProvider({children}: CauseProviderProps) {
  const [state, setState] = useState(initialState);
  const {causes, fetching, fetchingError} = state;
  const value = {causes, fetching, fetchingError};
  const authState = useContext(AuthContext);


  const getCausesEffect = () => {
    let canceled = false;

    fetchCauses();

    return () => {
      canceled = true;
    }

    async function fetchCauses() {
      try {
        setState({...state, fetching: true});

        let causes = await causeService.get(authState.account?.token);
        const users = await userService.get(authState.account?.token);

        causes = causes.map(cause => ({
          ...cause,
          userName: users.find(user => user.id === cause.userId)?.username,
        }));


        if (!canceled) {
          setState({...state, causes, fetching: false});
        }
      } catch (error) {
        setState({...state, fetchingError: error as Error, fetching: false});
      }

    }

  }

  // eslint-disable-next-line react-hooks/exhaustive-deps
  useEffect(getCausesEffect, []);

  return (
    <CauseContext.Provider value={value}>
      {children}
    </CauseContext.Provider>
  )


}