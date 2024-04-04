import * as React from 'react';
import Box from '@mui/material/Box';
import { DataGrid } from '@mui/x-data-grid';
import DEVICES from '../Data/device';
import noDataImg from '../Images/noData2.png'

const columns = [
  { 
    field: 'id', 
    headerName: 'ID', 
    width: 90 },
  {
    field: 'name',
    headerName: 'Name',
    width: 150,
    editable: true,
  },
  {
    field: 'ip_address',
    headerName: 'IP Address',
    width: 150,
    editable: true,
  },
  {
    field: 'status',
    headerName: 'Status',
    width: 110,
    editable: true,
  },
  {
    field: 'hardware',
    headerName: 'Hardware',
    description: 'This hardware is used.',
    sortable: false,
    width: 160,
    // valueGetter: (value, row) => `${row.firstName || ''} ${row.lastName || ''}`,
  },
  {
    field: 'os',
    headerName: 'OS',
    width: 110,
    editable: true,
  },
  {
    field: 'os_version',
    headerName: 'OS Version',
    width: 110,
    editable: true,
  }
];

function NoDataView() {
    return (
        <div style={{ display: 'flex', justifyContent: 'center', height: 500, width: '200' }}>
        <img src={noDataImg} alt="No data available" style={{ width: '50%', height: '50%', paddingTop:'30px' }} /> {/* Adjust width and height as needed */}
      </div>
    );
  }

export default function InfoTable() {
  return (
    <Box sx={{ height: 400, width: '100%' }}>
      <DataGrid
        rows={DEVICES}
        columns={columns}
        initialState={{
          pagination: {
            paginationModel: {
              pageSize: 10,
            },
          },
        }}
        pageSizeOptions={[10]}
        // checkboxSelection
        slots={{ noRowsOverlay: NoDataView}}
        sx={{ '--DataGrid-overlayHeight': '300px' }}
        disableRowSelectionOnClick
        // isCellEditable={(params) => params.row.status === 'up'}
      />
    </Box>
  );
}
