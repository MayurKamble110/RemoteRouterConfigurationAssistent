
import * as React from 'react';
import { useEffect } from "react";
import { fetchDevices } from '../api';
import Forms from './Forms';
import { useDispatch } from 'react-redux';
import { deviceActions } from '../Store';
import { useNavigate } from 'react-router-dom'


export default function Content() {
  const [devices, setDevices] = React.useState([]);
  const [checkBoxSelection, setCheckBoxSelection] = React.useState({
    deviceChecked : []
  })
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const checkBoxHandler = (deviceId) => {
    setCheckBoxSelection((prevState) => {
      if (prevState.deviceChecked.includes(deviceId)) {
        return ({
          deviceChecked : prevState.deviceChecked.filter((id)=>id !== deviceId)
        })
      } else {
        return({
          deviceChecked : [...prevState.deviceChecked , deviceId ]
        })
      }
    });
    console.log(checkBoxSelection);
  };
  function handleDeviceData(id, name) {
    dispatch(deviceActions.clickedDevice({
      id,
      name
    }));
    console.log(id, name);
    navigate("/device");
  }



  const loadScript = async () => {
    try {
      const response = await fetchDevices();
      console.log(response);
      setDevices(response);
      console.log(devices);
      const script = document.createElement("script");
      script.src = `js/content.js`;
      script.async = true;

      document.body.appendChild(script);
    } catch (error) {
      console.error('Error loading script:', error);
    }
  };

  useEffect(() => {
    loadScript();
  }, []);

  return (
    <>
      <div className="modal fade" id="modal-default">
        <div className="modal-dialog modal-l">
          <div className="modal-content">

            <div>
              <Forms addDevice={loadScript} />
            </div>
          </div>
        </div>
      </div>

      <div className="content-wrapper">
        <section className="content-header">
          <div className="container-fluid">
            <div className="row mb-2">
              <div className="col-sm-6">
                <h1>Devices</h1>
              </div>
              <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                  <button type="button" class="btn btn-block btn-secondary" data-toggle="modal" data-target="#modal-default">
                    Add Device</button>
                </ol>
              </div>
            </div>
          </div>
        </section>
        <section className="content">
          <div className="container-fluid">
            <div className="row">
              <div className="col-12">
                <div className="card">
                  <div className="card-header">
                    <h3 className="card-title">DataTable with default features</h3>
                  </div>
                  <div className="card-body">
                    <table id="example1" className="table table-bordered table-striped">
                      <thead>
                        <tr>
                          <th>Checkbox</th>
                          <th>Name</th>
                          <th>Hardware Model</th>
                          <th>IP Address</th>
                          <th>OS Type</th>
                          <th>OS Version</th>
                          <th>RAW LOGS</th>
                        </tr>
                      </thead>
                      <tbody>
                        {devices.map(device => (
                          <tr key={device.deviceId}>
                            <td>
                            <div className="custom-control custom-checkbox">
                              <input
                               className="custom-control-input" 
                               type="checkbox" 
                               id={`customCheckbox${device.deviceId}`} 
                               defaultValue="option1"
                               onChange={()=>checkBoxHandler(device.deviceId)} />
                              <label htmlFor={`customCheckbox${device.deviceId}`} className="custom-control-label"></label>
                            </div>
                            </td>
                            <td>{device.deviceName}</td>
                            <td>{device.hardwareModel || 'Not Available'}</td>
                            <td>{device.ipAddress}</td>
                            <td>{device.osType || 'N/A'}</td>
                            <td>{device.osVersion || 'N/A'}</td>
                            <td>
                              <button onClick={() => (handleDeviceData(device.deviceId, device.deviceName))} type="button" class="btn btn-block btn-secondary">View Data</button>
                            </td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    </>
  )
}