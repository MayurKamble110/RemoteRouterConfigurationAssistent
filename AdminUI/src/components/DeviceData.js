import { useEffect, useState, useRef } from 'react';
import { useSelector } from 'react-redux';
import Chart from 'chart.js/auto';
import Loader from './Spinner';


export default function DeviceData() {

  const deviceId = useSelector(state => state.device.deviceId);
  const deviceName = useSelector(state => state.device.deviceName);
  const [chartData, setChartData] = useState({});
  const [analyseInfo, setAnalyseInfo] = useState('');

  const chartRefs = useRef([null, null, null, null])
  const getAnalyseData = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/routers/${deviceId}/analyse`);
      if (!response.ok) {
        throw new Error("Failed to fetch data");
      }
      console.log(response);
      const analyseData = await response.text();
      console.log(analyseData);
      setAnalyseInfo(analyseData);
    } catch (error) {
      console.error('Error fetching app data:', error);
    }
  };

  useEffect(() => {
    const destroyCharts = () => {
      chartRefs.current.forEach((chartRef) => {
        if (chartRef && chartRef.chart) {
          chartRef.chart.destroy();
        }
      });
    };

    const fetchChartData = async () => {
      // setIsLoading(true);
      try {
        const response = await fetch(`http://localhost:8080/api/routers/${deviceId}/cpu-process-history`);
        if (!response.ok) {
          throw new Error("Failed to fetch data");
        }
        console.log(response);
        const responseData = await response.json();
        console.log(responseData);
        setChartData(responseData);
      } catch (error) {
        console.error('Error fetching chart data:', error);
      }
      // setIsLoading(false);
    };

    destroyCharts();
    fetchChartData();
  }, [deviceId]);

  useEffect(() => {
    Object.entries(chartData).forEach(([chartId, data]) => {
      const ctx = chartRefs.current[chartId].getContext('2d');
      chartRefs.current[chartId].chart = new Chart(ctx, {
        type: 'line', // You can adjust the chart type based on your data
        data: {
          labels: data.x,
          datasets: [{
            label: chartId,
            data: data.y,
            fill: false,
            borderColor: data.borderColor,
            tension: 0.1
          }]
        }
      });
    });
  }, [chartData]);

  return (
    <>
      <div className="content-wrapper">
        {/* Content Header (Page header) */}
        <section className="content-header">
          <div className="container-fluid">
            <div className="row mb-2">
              <div className="col-sm-6">
                <h1>{deviceName}</h1>
              </div>
              <div className="col-sm-6">
                <div className="breadcrumb float-sm-right">
                  {/* <button onClick={getAnalyseData} type="button" className="mr-2 btn btn-secondary">Analyse Data</button> */}
                  <button onClick={getAnalyseData} type="button" class="mr-2 btn btn-secondary" data-toggle="modal" data-target="#modal-default">
                    Analyse Data</button>
                  <button type="button" className="btn btn-secondary">Connect</button>
                </div>
              </div>
            </div>
          </div>{/* /.container-fluid */}

          <div className="modal fade" id="modal-default">
            <div className="modal-dialog">
              <div className="modal-content">
                <div className="modal-header">
                  <h4 className="modal-title">Analysing data for {deviceName}</h4>
                  <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                  </button>
                </div>
                <div className="modal-body">
                  {analyseInfo &&<div dangerouslySetInnerHTML={{ __html: analyseInfo }} /> }
                  {!analyseInfo && <Loader ></Loader>}
                </div>
              
              </div>
              {/* /.modal-content */}
            </div>
            {/* /.modal-dialog */}
          </div>


        </section>
        {/* Main content */}
        <section className="content">
          <div className="container-fluid">
            <div className="row">
              <div className="col-12 col-sm-6 col-md-3">
                <div className="info-box">
                  <span className="info-box-icon bg-info elevation-1"><ion-icon name="radio-button-on-outline"></ion-icon></span>
                  <div className="info-box-content">
                    <span className="info-box-text">Total Interfaces</span>
                    <span className="info-box-number">
                      100
                    </span>
                  </div>
                  {/* /.info-box-content */}
                </div>
                {/* /.info-box */}
              </div>
              {/* /.col */}
              <div className="col-12 col-sm-6 col-md-3">
                <div className="info-box mb-3">
                  <span className="info-box-icon bg-success elevation-1"><ion-icon name="caret-up-outline"></ion-icon> </span>                 
                  <div className="info-box-content">
                    <span className="info-box-text">UP Interfaces</span>
                    <span className="info-box-number">41</span>
                  </div>
                  {/* /.info-box-content */}
                </div>
                {/* /.info-box */}
              </div>
              {/* /.col */}
              {/* fix for small devices only */}
              <div className="clearfix hidden-md-up" />
              <div className="col-12 col-sm-6 col-md-3">
                <div className="info-box mb-3">
                  <span className="info-box-icon bg-danger elevation-1"><ion-icon name="caret-down-outline"></ion-icon></span>
                  <div className="info-box-content">
                    <span className="info-box-text">Down Interfaces</span>
                    <span className="info-box-number">59</span>
                  </div>
                  {/* /.info-box-content */}
                </div>
                {/* /.info-box */}
              </div>
              {/* /.col */}
              <div className="col-12 col-sm-6 col-md-3">
                <div className="info-box mb-3">
                  <span className="info-box-icon bg-warning elevation-1"><i className="fas fa-users" /></span>
                  <div className="info-box-content">
                    <span className="info-box-text">Money</span>
                    <span className="info-box-number">2,000</span>
                  </div>
                  {/* /.info-box-content */}
                </div>
                {/* /.info-box */}
              </div>
              {/* /.col */}
            </div>
          </div>
        </section>

        <section className="content">
          <div className="container-fluid">
            <div className="row">
              <div className="col-12">
                <div className="card">
                  <div className="card-header">
                    <h3 className="card-title">Fixed Header Table</h3>
                    <div className="card-tools">
                      <div className="input-group input-group-sm" style={{ width: 150 }}>
                        <input type="text" name="table_search" className="form-control float-right" placeholder="Search" />
                        <div className="input-group-append">
                          <button type="submit" className="btn btn-default">
                            <i className="fas fa-search" />
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                  {/* /.card-header */}
                  <div className="card-body table-responsive p-0" style={{ height: 300 }}>
                    <table className="table table-head-fixed text-nowrap">
                      <thead>
                        <tr>
                          <th>ID</th>
                          <th>User</th>
                          <th>Date</th>
                          <th>Status</th>
                          <th>Reason</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>183</td>
                          <td>John Doe</td>
                          <td>11-7-2014</td>
                          <td><span className="tag tag-success">Approved</span></td>
                          <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                        </tr>
                        <tr>
                          <td>219</td>
                          <td>Alexander Pierce</td>
                          <td>11-7-2014</td>
                          <td><span className="tag tag-warning">Pending</span></td>
                          <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                        </tr>
                        <tr>
                          <td>657</td>
                          <td>Bob Doe</td>
                          <td>11-7-2014</td>
                          <td><span className="tag tag-primary">Approved</span></td>
                          <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                        </tr>
                        <tr>
                          <td>175</td>
                          <td>Mike Doe</td>
                          <td>11-7-2014</td>
                          <td><span className="tag tag-danger">Denied</span></td>
                          <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                        </tr>
                        <tr>
                          <td>134</td>
                          <td>Jim Doe</td>
                          <td>11-7-2014</td>
                          <td><span className="tag tag-success">Approved</span></td>
                          <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                        </tr>
                        <tr>
                          <td>494</td>
                          <td>Victoria Doe</td>
                          <td>11-7-2014</td>
                          <td><span className="tag tag-warning">Pending</span></td>
                          <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                        </tr>
                        <tr>
                          <td>832</td>
                          <td>Michael Doe</td>
                          <td>11-7-2014</td>
                          <td><span className="tag tag-primary">Approved</span></td>
                          <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                        </tr>
                        <tr>
                          <td>982</td>
                          <td>Rocky Doe</td>
                          <td>11-7-2014</td>
                          <td><span className="tag tag-danger">Denied</span></td>
                          <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  {/* /.card-body */}
                </div>
                {/* /.card */}
              </div>
            </div>
          </div>{/* /.container-fluid */}
        </section>

        <section className="content-header">
          <div className="container-fluid">
            <div className="row mb-2">
              <div className="col-sm-6">
                <h1>ChartJS</h1>
              </div>
              <div className="col-sm-6">
                <ol className="breadcrumb float-sm-right">
                  <li className="breadcrumb-item"><a href="#">Home</a></li>
                  <li className="breadcrumb-item active">ChartJS</li>
                </ol>
              </div>
            </div>
          </div>
        </section>
        <section className="content">
          {Object.keys(chartData).map((chartId) => (
            <div className="container-fluid">
              <div className="row">
                <div className="col-md-6">
                  {/* {Object.keys(chartData).map((chartId) => ( */}
                  <div className="card card-primary">
                    <div className="card-header">
                      <h3 className="card-title">Area Chart</h3>
                      <div className="card-tools">
                        <button type="button" className="btn btn-tool" data-card-widget="collapse">
                          <i className="fas fa-minus" />
                        </button>
                        <button type="button" className="btn btn-tool" data-card-widget="remove">
                          <i className="fas fa-times" />
                        </button>
                      </div>
                    </div>
                    <div className="card-body">
                      {/* <canvas ref={chartRefs[0]} style={{ minHeight: 250, height: 250, maxHeight: 250, maxWidth: '100%' }} /> */}
                      <canvas key={chartId} ref={(ref) => (chartRefs.current[chartId] = ref)} style={{ minHeight: 250, height: 250, maxHeight: 250, maxWidth: '100%' }}></canvas>

                    </div>
                  </div>
                  {/* ))} */}
                  {/* <div className="card card-danger">
                  <div className="card-header">
                    <h3 className="card-title">Donut Chart</h3>
                    <div className="card-tools">
                      <button type="button" className="btn btn-tool" data-card-widget="collapse">
                        <i className="fas fa-minus" />
                      </button>
                      <button type="button" className="btn btn-tool" data-card-widget="remove">
                        <i className="fas fa-times" />
                      </button>
                    </div>
                  </div>
                  <div className="card-body">
                    <canvas ref={chartRef2} style={{ minHeight: 250, height: 250, maxHeight: 250, maxWidth: '100%' }} />
                  </div>
                </div> */}
                </div>
                {/* <div className="col-md-6">
                <div className="card card-danger">
                  <div className="card-header">
                    <h3 className="card-title">Pie Chart</h3>
                    <div className="card-tools">
                      <button type="button" className="btn btn-tool" data-card-widget="collapse">
                        <i className="fas fa-minus" />
                      </button>
                      <button type="button" className="btn btn-tool" data-card-widget="remove">
                        <i className="fas fa-times" />
                      </button>
                    </div>
                  </div>
                  <div className="card-body">
                    <canvas ref={chartRef3} style={{ minHeight: 250, height: 250, maxHeight: 250, maxWidth: '100%' }} />
                  </div>
                </div>
                <div className="card card-info">
                  <div className="card-header">
                    <h3 className="card-title">Line Chart</h3>
                    <div className="card-tools">
                      <button type="button" className="btn btn-tool" data-card-widget="collapse">
                        <i className="fas fa-minus" />
                      </button>
                      <button type="button" className="btn btn-tool" data-card-widget="remove">
                        <i className="fas fa-times" />
                      </button>
                    </div>
                  </div>
                  <div className="card-body">
                    <canvas ref={chartRef4} style={{ minHeight: 250, height: 250, maxHeight: 250, maxWidth: '100%' }} />
                  </div>
                </div>
              </div> */}
              </div>
            </div>
          ))}
        </section>
        {/* /.content */}
      </div>
    </>
  )
}