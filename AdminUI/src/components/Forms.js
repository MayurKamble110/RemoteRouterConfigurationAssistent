
import { useRef, useState, useEffect } from "react"


export default function Forms(props) {

  const initialFormValues = {
    username: '',
    ipAddress: '',
    deviceName: '',
    password: '',
    enablePassword: ''
  }

  const [formData, setFormData] = useState(initialFormValues);
  const [errorMessage, setErrorMessage] = useState('');

  const handleCloseModal = async (event) => {
    event.preventDefault();
    try {
      if (!formData.username || !formData.ipAddress || !formData.deviceName || !formData.password || !formData.enablePassword) {
        setErrorMessage('Please Fill all the feilds');
        return;
      }

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
      props.onClose();
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

  function handleCloseIcon()
    {
        setErrorMessage('');
        props.onClose();
    }


  return (


    <div className="content-wrapper">
      <section className="content">
        <div className="container-fluid">
          <div className="col-md-6">
          
            <div className="card card-primary ">
              <div className="card-header card card-primary d-flex align-items-center justify-content-center">
              <button onClick={handleCloseIcon} style={{ marginLeft: 'auto', background: 'none', border: 'none', cursor: 'pointer', fontSize: '1rem', color: '#000'  }}><img src="/imgs/close-button-png-30241.png" style={{width : '25px' , height : '25px'}}></img></button>
              <h3 className="card-title">Add Device</h3>
              </div>
              <form>
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
                <p style={{ color: 'red' }}>{errorMessage}</p>
                <div className="card-footer text-center">
                  <button type="submit" className="btn btn-primary" onClick={handleCloseModal}>Submit</button>
                </div>
              </form>
            </div>

            {/* /.card */}
          </div>
        </div>{/* /.container-fluid */}
      </section>
      {/* /.content */}
    </div>

  )
}