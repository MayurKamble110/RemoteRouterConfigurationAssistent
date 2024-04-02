import { useRef, useState } from "react";
import './Device.css';
import { DEVICES } from "../Data/device";
import Cards from "./Cards";
import Dialog from "./Dialog";

export default function Device()
{
    const [ data , setData] = useState([]);
    const[isModalOpen , setIsModalOpen] = useState(false);

    function handleAddDevice()
    {
        setIsModalOpen(true);
    }

    function handleCloseModal()
    {
        setIsModalOpen(false);
    }

    return(
        <>
        <Dialog open={isModalOpen} onClose={handleCloseModal}></Dialog>
        <div id = 'device'>
             <h1>Devices</h1>
             <button className="add-button" onClick={handleAddDevice}>Add Device</button>
        </div>
        <div id = 'cards'>
        <ul>
         {DEVICES.map((device)=>(
          <Cards key={device.name}{...device}/>
         ))}
        </ul> 
        </div>
        </>
    )
}