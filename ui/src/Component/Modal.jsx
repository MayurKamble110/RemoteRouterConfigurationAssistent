import './Modal.css'
export default function Modal({isOpen, closeModal, device}){
    return (
        <>
           {isOpen &&
                <div className="modal-overlay" onClick={closeModal}>
                    <div className="modal-content" onClick={(e) => e.stopPropagation()}>
                        <span className="modal-close" onClick={closeModal}>&times;</span>
                        <h1>{device.name}</h1>
                        <p>IP Address: {device.ip_address}</p>
                        <p>Device Status: {device.status}</p>
                        <p>Hardware: {device.hardware}</p>
                        <p>OS: {device.os}</p>
                        <p>OS Version: {device.os_version}</p>
                    </div>
                </div>
            }
        </>
    )
}