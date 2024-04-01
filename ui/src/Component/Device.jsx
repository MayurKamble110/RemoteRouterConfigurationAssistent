import { useState } from "react";
import './Device.css';
import { DEVICES } from "../Data/device";
import Cards from "./Cards";

export default function Device()
{
    const [ data , setData] = useState([]);

    return(
        <>
        <div id = 'device'>
             <h2>Devices</h2>
             <button className="add-button">Add Device</button>
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