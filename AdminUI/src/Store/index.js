import { configureStore, createSlice } from '@reduxjs/toolkit';
import { combineReducers } from 'redux';
import { persistReducer, persistStore } from 'redux-persist'
import storage from 'redux-persist/lib/storage';


const initialDeviceState = {
    deviceId: null,
    deviceName: ''
}

const deviceSlice = createSlice(
    {
        name: 'device',
        initialState: initialDeviceState,
        reducers: {
            clickedDevice(state, action) {
                state.deviceId = action.payload.id;
                state.deviceName = action.payload.name;
            }
        }
    }
)

const rootReducer = combineReducers({
    device: deviceSlice.reducer,
});

const persistConfig = {
    key: 'root',
    storage,
    version: 1,
};

const persistedReducers = persistReducer(persistConfig, rootReducer);




const store = configureStore({
    reducer: persistedReducers
});

export default store;
export const persistor = persistStore(store);
export const deviceActions = deviceSlice.actions;