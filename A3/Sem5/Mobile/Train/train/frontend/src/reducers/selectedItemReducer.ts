import {createSlice, PayloadAction} from "@reduxjs/toolkit";

type SelectedItemState = {
    selectedItem: any;
}

const initialState = {
    selectedItem: undefined
} as SelectedItemState

export const selectedItemSlice = createSlice({
    name: "selectedItem",
    initialState,
    reducers: {
      setSelectedItem: (state, action: PayloadAction<any>) => {
        state.selectedItem = action.payload;
      }
    }
})

export const {setSelectedItem} = selectedItemSlice.actions
export default selectedItemSlice.reducer
export const selectedItemSelector = (state) => {
  // try to parse the selectedItem as json
  try {
    return JSON.parse(state.selectedItemReducer.selectedItem);
  }
  // if it fails, return the selectedItem as is
  catch {
    return state.selectedItemReducer.selectedItem;
  }
}
