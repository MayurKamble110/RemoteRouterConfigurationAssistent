import { useState } from "react";
import './Device.css';
import Dialog from "./Dialog";
import store from "../Store";
import { addDeviceActions } from "../Store";
import { useSelector , useDispatch } from "react-redux";

export default function Device()
{
    const isModalOpen = useSelector((state) => state.addDevice.isOpenModal)
    const dispatch = useDispatch();
    
    function handleAddDevice()
    {
        dispatch(addDeviceActions.setOpenModal());
    }

    function handleCloseModal()
    {
        dispatch(addDeviceActions.setCloseModal());
    }
    
    return (
        <>

        <Dialog open={isModalOpen} onClose={handleCloseModal}></Dialog>
        <div id = 'device'>
             <h1>Devices</h1>
             <button className="add-button" onClick={handleAddDevice}>Add Device</button>
        </div>
        </>
    )
}