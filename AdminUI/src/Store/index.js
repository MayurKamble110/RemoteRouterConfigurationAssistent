import { configureStore , createSlice } from '@reduxjs/toolkit';

const initialDeviceState = {
    deviceClicked : 0
}

const initialAddDeviceState = {
    isOpenModal : false ,
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

const addDeviceSLice = createSlice(
    {
        name : 'addDevice',
        initialState : initialAddDeviceState,
        reducers : {
            setOpenModal : function(state){
                state.isOpenModal = true;
            },
            setCloseModal : function(state){
                state.isOpenModal = false;
            }
        }
    }
)

const store = configureStore({
    reducer : {
        device : deviceSlice.reducer,
        addDevice : addDeviceSLice.reducer
    }
});

export default store ;

export const deviceActions = deviceSlice.actions ;
export const addDeviceActions = addDeviceSLice.actions ;