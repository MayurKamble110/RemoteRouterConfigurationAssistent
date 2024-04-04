import * as React from 'react';
import Box from '@mui/material/Box';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import DEVICES from '../Data/device';

const VISIBLE_FIELDS = ['id', 'name', 'ip_address', 'status', 'hardware', 'os', 'os_version'];

export default function DeviceGrid() {
  const data = React.useMemo(() => {
    return {
      rows: DEVICES,
      columns: VISIBLE_FIELDS.map(field => ({
        field,
        headerName: field.replace(/_/g, ' ').toUpperCase(), // Replace underscores with spaces and capitalize
        width: 130,
      })),
    };
  }, []);

  return (
    <Box sx={{ height: 400, width: '100%' }}>
      <DataGrid
        {...data}
        disableColumnFilter
        disableColumnSelector
        disableDensitySelector
        slots={{ toolbar: GridToolbar }}
        slotProps={{
          toolbar: {
            showQuickFilter: true,
          },
        }}
      />
    </Box>
  );
}