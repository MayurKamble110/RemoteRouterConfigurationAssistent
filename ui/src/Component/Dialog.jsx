import { forwardRef, useEffect, useImperativeHandle, useRef } from "react";
import "./Dialog.css";

import { useState } from "react";

function Dialog(props) {
  const initialFormValues = {
    username: "",
    ipAddress: "",
    deviceName: "",
    password: "",
    enablePassword: "",
  };

  const modal = useRef();
  const [formData, setFormData] = useState(initialFormValues);

  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    if (props.open) {
      modal.current.showModal();
    } else {
      modal.current.close();
      setFormData(initialFormValues);
    }
  }, [props.open]);

  const handleCloseModal = async (event) => {
    event.preventDefault();
    try {
      if (
        !formData.username ||
        !formData.ipAddress ||
        !formData.deviceName ||
        !formData.password ||
        !formData.enablePassword
      ) {
        setErrorMessage("Please enter all fields.");
        return;
      }

      const response = await fetch("http://localhost:8080/api/routers", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error("Failed to add device");
      }

      const data = await response.json();
      console.log(data);
      props.onClose();
    } catch (error) {
      console.error("Error adding device:", error);
    }
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  function handleCloseIcon() {
    setErrorMessage("");
    props.onClose();
  }

  return (
    <dialog id="dialog" ref={modal} onClose={props.onClose}>
      <h2>Add Device</h2>
      <button onClick={handleCloseIcon} className="close-button">
        X
      </button>
      <form className="modal-form">
        <div>
          <label>Username</label>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
            required
          ></input>
        </div>
        <div>
          <label>IP Address</label>
          <input
            type="text"
            name="ipAddress"
            value={formData.ipAddress}
            onChange={handleChange}
            required
          ></input>
        </div>
        <div>
          <label>Device Name</label>
          <input
            type="text"
            name="deviceName"
            value={formData.deviceName}
            onChange={handleChange}
            required
          ></input>
        </div>
        <div>
          <label>Login Password</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          ></input>
        </div>
        <div>
          <label>Enable Password</label>
          <input
            type="password"
            name="enablePassword"
            value={formData.enablePassword}
            onChange={handleChange}
            required
          ></input>
        </div>
        {errorMessage && <p className="error-message">{errorMessage}</p>}
        <button onClick={handleCloseModal}>Add</button>
      </form>
    </dialog>
  );
}

export default Dialog;
