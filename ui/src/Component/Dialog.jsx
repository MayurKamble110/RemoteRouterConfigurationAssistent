import { forwardRef, useEffect, useImperativeHandle, useRef } from "react"
import './Dialog.css';
import DEVICES from "../Data/device";
import { useState } from "react";

function Dialog(props,ref){

    const modal = useRef();
    const [formData, setFormData] = useState({
        username: '',
        ipAddress: '',
        loginPassword: '',
        enablePassword: ''
    });
    
    useEffect(()=>{
        if(props.open){
            modal.current.showModal();
        }
        else{
            modal.current.close();
        }
    },[props.open])

    function handleCloseModal()
    {   
        props.onClose();
    }

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevFormData) => ({
            ...prevFormData,
            [name]: value
        }));
    };

    return(
        <dialog id = 'dialog' ref={modal} onClose={props.onClose}>
            <h2>Add Device</h2>
            <button onClick={props.onClose} className="close-button">X</button>
            <form className="modal-form">
                <div>
                    <label>Username</label>
                    <input type="text" name="username" value={formData.username} onChange={handleChange}></input>
                </div>
                <div>
                    <label>IP Address</label>
                    <input type="text" name="ipAddress" value={formData.ipAddress} onChange={handleChange}></input>
                </div>
                <div>
                    <label>Login Password</label>
                    <input type="password" name="loginPassword" value={formData.loginPassword} onChange={handleChange}></input>
                </div>
                <div>
                    <label>Enable Password</label>
                    <input type="password" name="enablePassword" value={formData.enablePassword} onChange={handleChange}></input>
                </div>
                <button onClick={handleCloseModal}>Add</button>
            </form>
        </dialog>
    )
}

export default Dialog;