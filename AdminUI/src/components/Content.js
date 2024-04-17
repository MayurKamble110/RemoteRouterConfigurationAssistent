
import * as React from 'react';
import { useEffect } from "react";
import { fetchDevices } from '../api';
import Forms from './Forms';
import { useDispatch } from 'react-redux';
import { deviceActions } from '../Store';
import { useNavigate } from 'react-router-dom'


export default function Content() {
  const [devices, setDevices] = React.useState([]);
  const navigate = useNavigate();
  const [openForm, setOpenForm] = React.useState(false);
  const dispatch = useDispatch();

  function handleDeviceData(id, name) {
    dispatch(deviceActions.clickedDevice({
      id,
      name
    }));
    console.log(id, name);
    navigate("/device");
  }

  function handleFormOpen() {
    setOpenForm(true);
  }

  function handleFormClose() {
    setOpenForm(false);
  }

  useEffect(() => {
    const loadScript = async () => {
      try {
        if (!openForm) {
          const response = await fetchDevices();
          setDevices(response);
        }

        const script = document.createElement("script");
        script.src = `js/content.js`;
        script.async = true;

        document.body.appendChild(script);
      } catch (error) {
        console.error('Error loading script:', error);
      }
    };

    loadScript();
  }, [openForm]);

  return (
    <>
      {openForm && <Forms onClose={handleFormClose}></Forms>}
      {!openForm && <div className="content-wrapper">
        <section className="content-header">
          <div className="container-fluid">
            <div className="row mb-2">
              <div className="col-sm-6">
                <h1>Devices</h1>
              </div>
              <div class="col-sm-6">
                <ol class="breadcrumb float-sm-right">
                  {/* <button type="button" class="btn btn-block btn-secondary" onClick={handleFormOpen}>Add Device</button> */}
                  <button type="button" class="btn btn-block btn-secondary" data-toggle="modal" data-target="#modal-default">
                    Add Device</button>
                </ol>
              </div>
            </div>
          </div>

          <div className="modal fade" id="modal-default">
            <div className="modal-dialog modal-l">
              <div className="modal-content">
                
                <div>
                <Forms/>
                </div>
                
    
              </div>
              {/* /.modal-content */}
            </div>
            {/* /.modal-dialog */}
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
                          <th>Device ID</th>
                          <th>Name</th>
                          <th>Type</th>
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
                            <td>{device.deviceId}</td>
                            <td>{device.deviceName}</td>
                            <td>{device.deviceType}</td>
                            <td>{device.hardwareModel}</td>
                            <td>{device.ipAddress}</td>
                            <td>{device.osType}</td>
                            <td>{device.osVersion}</td>
                            <td>
                              {device.osVersion}
                              <button onClick={() => (handleDeviceData(device.deviceId, device.deviceName))} type="button" class="btn btn-block btn-secondary">View Data</button>                            </td>
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
      </div>}
    </>
  )
}