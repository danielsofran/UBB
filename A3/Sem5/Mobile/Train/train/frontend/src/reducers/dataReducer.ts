import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {axiosInstance} from "../service/axiosInstance";
import {getDataFromStorage, resetStorage, updateDataInStorage} from "../service/dataStorage";

const endpoint = "/message";

const initialState = {
  data: [],
}

export const dataSlice = createSlice({
  name: "data",
  initialState,
  reducers: {
    setData: (state, action: PayloadAction<any[]>) => {
      resetStorage(action.payload)
      state.data = action.payload;
    },
    updateItem: (state, action: PayloadAction<any>) => {
      const index = state.data.findIndex(item => item.code === action.payload.code);
      updateDataInStorage(action.payload.code, action.payload)
      state.data[index] = action.payload;
    },
    addItem: (state, action: PayloadAction<any>) => {
      state.data.push(action.payload);
    },
    deleteItem: (state, action: PayloadAction<any>) => {
      state.data = state.data.filter(item => item.code !== action.payload.code);
    }
  }
})


export const {setData, updateItem, deleteItem, addItem} = dataSlice.actions
export default dataSlice.reducer
export const dataSelector = (state) => state.dataReducer.data