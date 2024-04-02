import { useRef, useState } from "react";
import './Device.css';
import { DEVICES } from "../Data/device";
import Cards from "./Cards";
import Dialog from "./Dialog";
import Modal from "./Modal";

export default function Device()
{
    const [selectedDevice, setSelectedDevice] = useState(null);  
    const[isModalOpen , setIsModalOpen] = useState(false);
    const[isCardModalOpen , setIsCardModalOpen] = useState(false);
    
    function handleAddDevice()
    {
        setIsModalOpen(true);
    }

    function handleCloseModal()
    {
        setIsModalOpen(false);
    }

    const openModal = (device) => {
        setSelectedDevice(device);
        setIsCardModalOpen(true);
    };

    const closeModal = () => {
        setIsCardModalOpen(false);
    };
    

    return (
        <>

        <Dialog open={isModalOpen} onClose={handleCloseModal}></Dialog>
        <div id = 'device'>
             <h1>Devices</h1>
             <button className="add-button" onClick={handleAddDevice}>Add Device</button>
        </div>
        <div>    
            {
                (DEVICES.length !== 0) ? (
                    <>
                    <div id='cards'>
                        <ul>
                            {DEVICES.map((device) => (
                                <Cards key={device.name}{...device} onCardClick={() => openModal(device)}/>
                            ))}
                        </ul>
                    </div>
                    <Modal isOpen={isCardModalOpen} closeModal={closeModal} device={selectedDevice}/>
                  </>
                )
                    :
                    (
                        <div id="preview-content">
                        <p><strong>Sorry, no devices have been added.</strong></p>
                    </div>   
                    )
            }
        </div>
        </>
    )
}