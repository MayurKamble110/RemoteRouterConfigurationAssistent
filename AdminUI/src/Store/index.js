import { configureStore, createSlice } from '@reduxjs/toolkit';
import { combineReducers } from 'redux';
import { persistReducer, persistStore } from 'redux-persist'
import storage from 'redux-persist/lib/storage';


const initialDeviceState = {
    deviceId: null,
    deviceName: ''
}

const initialUserState = {
    userName: null,
    jwtToken: null
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

const userSlice = createSlice(
    {
        name: 'user',
        initialState: initialUserState,
        reducers: {
            signInSuccess(state, action) {
                state.userName = action.payload.userName;
                state.jwtToken = action.payload.jwtToken;
            }
        }
    }
)

const rootReducer = combineReducers({
    device: deviceSlice.reducer,
    user: userSlice.reducer
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
export const userActions = userSlice.actions;