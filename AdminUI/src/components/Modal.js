import { useState, useRef, useEffect } from "react";

export default function Modal(props) {
    const initialFormValues = {
        username: '',
        ipAddress: '',
        deviceName: '',
        password: '',
        enablePassword: ''
    }

    const [formData, setFormData] = useState(initialFormValues);

    const modalRef = useRef(null);

    useEffect(() => {
        if (props.openModal) {
            modalRef.current.showModal();
        }
        else {
            modalRef.current.close();
            setFormData(initialFormValues);
        }
    }, [props.openModal])

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/routers', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (!response.ok) {
                throw new Error('Failed to add device');
            }
            const data = await response.json();
            props.fetchDevices();
            props.closeModal();
        } catch (error) {
            console.error('Error adding device:', error);
        }
    };

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((prevFormData) => ({
            ...prevFormData,
            [name]: value
        }));
    };

    function handleCloseModal() {
        props.closeModal();
    }

    return (
        <dialog id="dialog" ref={modalRef} style={{
            width: '500px', backgroundColor: '#343a40',
            border: '1px solid #343a40'
        }} onClose={props.closeModal}>
            <div className="card card-primary ">
                <div className="card-header card card-primary d-flex align-items-center justify-content-center">
                    <button type="button" style={{ marginLeft: 'auto' }} className="close" onClick={handleCloseModal}>X</button>
                    <h2 className="card-title">Add Device</h2>
                </div>
                <form onSubmit={handleSubmit}>
                    <div className="card-body popup-background ">
                        <div className="popup-content">
                            <div className="form-group">
                                <label>Device Name</label>
                                <input type="text" className="form-control" placeholder="Enter device name" name="deviceName" value={formData.deviceName} onChange={handleChange} />
                            </div>
                            <div className="form-group">
                                <label>IP Address</label>
                                <input type="text" className="form-control" value={formData.ipAddress} placeholder="Enter IP Address" name="ipAddress" onChange={handleChange} />
                            </div>
                            <div className="form-group">
                                <label>Username</label>
                                <input type="text" className="form-control" name="username" value={formData.username} placeholder="Enter username" onChange={handleChange} />
                            </div>
                            <div className="form-group">
                                <label htmlFor="exampleInputPassword1">Login Password</label>
                                <input type="password" className="form-control" name="password" value={formData.password} placeholder="Login Password" onChange={handleChange} />
                            </div>
                            <div className="form-group">
                                <label htmlFor="exampleInputPassword1">Enable Password</label>
                                <input type="password" className="form-control" name="enablePassword" value={formData.enablePassword} placeholder="Enable Password" onChange={handleChange} />
                            </div>
                        </div>
                    </div>
                    <div className="card-footer text-center">
                        <button type="submit" className="btn btn-primary">Submit</button>
                    </div>
                </form>
            </div>
        </dialog>
    )
}