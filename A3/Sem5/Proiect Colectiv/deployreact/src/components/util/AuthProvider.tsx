import React, {useState} from "react";
import {User} from "../../model/user";
import axios from "axios";

// It the account is `undefined`, it means no one is logged in
export interface AuthState {
  account?: User,
  setAccount: (accountData?: User) => void
}

// The default `setAccount` function does nothing, and is replaced in AuthProvider
export const AuthContext = React.createContext<AuthState>({
  setAccount: () => {}
});

const loadAccount = () => {
  const accountStr = sessionStorage.getItem("account");
  if (accountStr) {
    const account = JSON.parse(accountStr);
    console.log(axios.defaults.headers.common);
    return account;
  }
  return undefined;
}

export default function AuthProvider({children} : {children: React.ReactNode}) {
  const [account, setAccount] = useState<User|undefined>(loadAccount());

  const saveToSessionStorage = (account: User) => {
    if (account?.token)
      sessionStorage.setItem("account", JSON.stringify(account));
  }

  const state = {
    account,
    setAccount: (account?: User) => {
      if(account)
        saveToSessionStorage(account);
      else
        sessionStorage.removeItem("account");
      setAccount(account);
    }
  };

  return (
    <AuthContext.Provider value={state}>
      {children}
    </AuthContext.Provider>
  )
}
