import { useState } from "react";
import './Device.css';

export default function Device()
{
    const [ data , setData] = useState([]);

    return(
        <div id = 'device'>
             <h2>Devices</h2>
             <button className="add-button">Add Device</button>
        </div>
    )
}