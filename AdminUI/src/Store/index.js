import { configureStore , createSlice } from '@reduxjs/toolkit';

const initialDeviceState = {
    deviceId : 0 ,
    deviceName : ''
}


const deviceSlice = createSlice(
    {
        name : 'device',
        initialState : initialDeviceState ,
        reducers : {
            clickedDevice(state , action){
                state.deviceId = action.payload.id;
                state.deviceName = action.payload.name;
            }
        }
    }
)


const store = configureStore({
    reducer : {
        device : deviceSlice.reducer,
    }
});

export default store ;

export const deviceActions = deviceSlice.actions ;