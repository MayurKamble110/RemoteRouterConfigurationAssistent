import React, { useEffect, useRef } from 'react';
import Chart from 'chart.js/auto';
// suppose i want to get data from API , what should be the format of that API, give example
export default function Charts() {
  const chartRef1 = useRef(null);
  const chartRef2 = useRef(null);
  const chartRef3 = useRef(null);
  const chartRef4 = useRef(null);

  useEffect(() => {
    // Function to destroy existing charts
    const destroyCharts = () => {
      [chartRef1, chartRef2, chartRef3, chartRef4].forEach((chartRef) => {
        if (chartRef.current && chartRef.current.chart) {
          chartRef.current.chart.destroy();
        }
      });
    };

    // Destroy existing charts before creating new ones
    destroyCharts();

    // Create Chart 1 (Area Chart)
    const ctx1 = chartRef1.current.getContext('2d');
    chartRef1.current.chart = new Chart(ctx1, {
      type: 'line',
      data: {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [{
          label: 'My First Dataset',
          data: [65, 59, 80, 81, 56, 55, 40],
          fill: false,
          borderColor: 'rgb(75, 192, 192)',
          tension: 0.1
        }]
      }
    });

    // Create Chart 2 (Bar Chart)
    const ctx2 = chartRef2.current.getContext('2d');
    chartRef2.current.chart = new Chart(ctx2, {
      type: 'bar',
      data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
          label: '# of Votes',
          data: [12, 19, 3, 5, 2, 3],
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(255, 159, 64, 0.2)'
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)'
          ],
          borderWidth: 1
        }]
      }
    });

    // Create Chart 3 (Pie Chart)
    const ctx3 = chartRef3.current.getContext('2d');
    chartRef3.current.chart = new Chart(ctx3, {
      type: 'pie',
      data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
          label: '# of Votes',
          data: [12, 19, 3, 5, 2, 3],
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(255, 159, 64, 0.2)'
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)'
          ],
          borderWidth: 1
        }]
      }
    });

    // Create Chart 4 (Line Chart)
    const ctx4 = chartRef4.current.getContext('2d');
    chartRef4.current.chart = new Chart(ctx4, {
      type: 'line',
      data: {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [{
          label: 'My Second Dataset',
          data: [55, 45, 70, 91, 45, 30, 60],
          fill: false,
          borderColor: 'rgb(255, 99, 132)',
          tension: 0.1
        }]
      }
    });

  }, []);

  return (
    <div className="content-wrapper">
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
        <div className="container-fluid">
          <div className="row">
            <div className="col-md-6">
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
                  <canvas ref={chartRef1} style={{ minHeight: 250, height: 250, maxHeight: 250, maxWidth: '100%' }} />
                </div>
              </div>
              <div className="card card-danger">
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
              </div>
            </div>
            <div className="col-md-6">
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
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}
