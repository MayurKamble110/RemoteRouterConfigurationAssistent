import { useRef, useState } from "react";
import './Device.css';
import Dialog from "./Dialog";

export default function Device()
{
    const [ data , setData] = useState([]);
    const dialog = useRef();

    function handleAddDevice()
    {
        dialog.current.open();
    }

    return(
        <>
        <Dialog ref={dialog}></Dialog>
        <div id = 'device'>
             <h1>Devices</h1>
             <button className="add-button" onClick={handleAddDevice}>Add Device</button>
        </div>
        </>
    )
}