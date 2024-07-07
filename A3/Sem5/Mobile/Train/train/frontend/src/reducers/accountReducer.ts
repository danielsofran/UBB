import {createSlice} from "@reduxjs/toolkit";
import {setAccountToStorage} from "../service/dataStorageAccount";

type AccountState = {
  id?: string;
}

const initialState = {
  id: undefined,
} as AccountState

export const accountSlice = createSlice({
  name: "account",
  initialState,
  reducers: {
    setAccount: (state, action) => {
      setAccountToStorage(action.payload)
      state.id = action.payload
    }
  }
})

export const {setAccount} = accountSlice.actions
export default accountSlice.reducer
export const accountSelector = (state) => state.accountReducer.id