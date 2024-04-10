
import * as React from 'react';
import { useEffect } from "react";
import { fetchDevices } from '../api';

export default function Content() {
  const [devices, setDevices] = React.useState([]);
  // useEffect(() => {
  //   const script = document.createElement("script");

  //   script.src = `js/content.js`;
  //   script.async = true;

  //   document.body.appendChild(script);
  // }, [])

  useEffect(() => {
    const loadScript = async () => {
      try {
        const response = await fetchDevices();
        setDevices(response);

        const script = document.createElement("script");
        script.src = `js/content.js`;
        script.async = true;

        document.body.appendChild(script);
      } catch (error) {
        console.error('Error loading script:', error);
      }
    };

    loadScript();
  }, []);


  // useEffect(() => {
  //   let fetchDevices = async () => {
  //     try {
  //       const response = await fetch('http://localhost:8080/api/get-router-data'); // Replace with your API endpoint
  //       if (!response.ok) {
  //         throw new Error('Failed to fetch data');
  //       }
  //       const responseData = await response.json();
  //       // const modifiedData = responseData.map(item => ({ ...item, id: item.deviceId }));
  //       // setDevices(modifiedData);
  //       setDevices(responseData);
  //       console.log(responseData);
  //     } catch (error) {
  //       console.error('Error fetching devices:', error);
  //     }
  //   };

  //   fetchDevices();
  // }, [])
  return (
    <div className="content-wrapper">
      <section className="content-header">
        <div className="container-fluid">
          <div className="row mb-2">
            <div className="col-sm-6">
              <h1>Devices</h1>
            </div>
            <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
            <button type="button" class="btn btn-block btn-secondary">Add Device</button>
            </ol>
          </div>
          </div>
        </div>{/* /.container-fluid */}
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
                      {/* <tr>
                        <td>Dsddg</td>
                        <td>Nsv</td>
                        <td>sdvfvsfsf</td>
                        <td>Hardware Model</td>
                        <td>IP Address</td>
                        <td>OS Type</td>
                        <td>OS Version</td>
                        <td>RAW LOGS</td>
                      </tr> */}
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
                            <button type="button" class="btn btn-block btn-secondary">View Logs</button>
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

  )
}