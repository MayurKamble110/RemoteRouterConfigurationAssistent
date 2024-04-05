import * as React from 'react';
import Box from '@mui/material/Box';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import DEVICES from '../Data/device';
import noDataImg from '../Images/noData2.png';
import './InfoTable.css';

const VISIBLE_FIELDS = ['id', 'name', 'ip_address', 'status', 'hardware', 'os', 'os_version'];
function NoDataView() {
    return (
        <div style={{ display: 'flex', justifyContent: 'center', height: 500, width: '200' }}>
            <h2>No Device</h2>
        {/* <img src={noDataImg} alt="No data available" style={{ width: '50%', height: '50%', paddingTop:'30px' }} /> Adjust width and height as needed */}
      </div>
    );
  }
export default function DeviceGrid() {
  const data = React.useMemo(() => {
    const columns = VISIBLE_FIELDS.map(field => ({
      field,
      headerName: field.replace(/_/g, ' ').toUpperCase(),
      width: 130
    }));
  
    // Add an additional column for the button
    columns.push({
      field: 'button',
      headerName: 'RAW LOG',
      width: 130,
      renderCell: (params) => (
        <button
          variant="contained"
          color="primary"
          onClick={() => console.log('Button clicked for ID:', params.row.id)}
          className='custom-button'
        >
          Raw Data
        </button>
      ),
    });
  
    return {
      rows: DEVICES,
      columns: columns,
    };
  }, []);
  
  return (
    <Box sx={{ height: 400, width: '100%' }}>
      <DataGrid
        {...data}
        disableColumnFilter
        disableColumnSelector
        disableDensitySelector
        slots={
            // { toolbar: GridToolbar }
            { noRowsOverlay: NoDataView }
        }
        slotProps={{
          toolbar: {
            showQuickFilter: true,
          },
          
        }}
      />
    </Box>
  );
}