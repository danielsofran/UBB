import {configureStore} from "@reduxjs/toolkit";
import {TypedUseSelectorHook, useDispatch, useSelector} from "react-redux";
import selectedItemReducer from "./reducers/selectedItemReducer";
import dataReducer from "./reducers/dataReducer";
import accountReducer from "./reducers/accountReducer";

export const store = configureStore({
  reducer: {
    selectedItemReducer,
    dataReducer,
    accountReducer
  },
  middleware: (getDefaultMiddleware) => getDefaultMiddleware({
    serializableCheck: false,
  })
})

export type AppDispatch = typeof store.dispatch
export type RootState = ReturnType<typeof store.getState>

export const useAppDispatch = () => useDispatch<AppDispatch>()
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector