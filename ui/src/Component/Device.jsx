import { useState } from "react";
import './Device.css';
import Dialog from "./Dialog";

export default function Device()
{
    const[isModalOpen , setIsModalOpen] = useState(false);
    
    function handleAddDevice()
    {
        setIsModalOpen(true);
    }

    function handleCloseModal()
    {
        setIsModalOpen(false);
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