import { useRef, useState } from "react";
import './Device.css';
import { DEVICES } from "../Data/device";
import Cards from "./Cards";
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