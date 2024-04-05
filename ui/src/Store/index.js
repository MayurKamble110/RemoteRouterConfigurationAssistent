import { configureStore , createSlice } from '@reduxjs/toolkit';

const initialDeviceState = {
    deviceClicked : 0
}

const deviceSlice = createSlice(
    {
        name : 'device',
        initialState : initialDeviceState ,
        reducers : {
            clickedDevice(state , action){
                state.deviceClicked = action.payload.id;
            }
        }
    }
)

const store = configureStore({
    reducer : {
        device : deviceSlice.reducer
    }
});

export default store ;

export const deviceActions = deviceSlice.actions ;