import * as React from 'react';
import Box from '@mui/material/Box';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import Checkbox from '@mui/material/Checkbox';
import { useSelector } from 'react-redux';
import noDataImg from '../Images/noData2.png';
import './InfoTable.css';
import { useDispatch } from 'react-redux';
import { deviceActions } from '../Store';
import { useNavigate } from 'react-router-dom';

const VISIBLE_FIELDS = ['id', 'deviceName', 'ipAddress','username','osType', 'osVersion','hardwareModel','deviceType'];


export default function DeviceGrid() {
  const [ devices , setDevices ] = React.useState([]);
  const isModalOpen = useSelector(state => state.addDevice.isOpenModal);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const data = React.useMemo(() => {
    const columns = [
      {
        field: 'checkbox',
        headerName: 'Checkbox',
        width: 100,
        renderCell: (params) => (
          <Checkbox
            checked={false} // You can set the checked state here based on your requirement
            onChange={() => {}}
            iconStyle={{ fill: 'black' }}
          />
        ),
      },
      ...VISIBLE_FIELDS.map(field => ({
        field,
        headerName: field.replace(/_/g, ' ').toUpperCase(),
        width: 160
      })),
      {
        field: 'button',
        headerName: 'RAW LOG',
        width: 130,
        renderCell: (params) => (
          <button
            variant="contained"
            color="primary"
            onClick={() => rawDataClicked(params.row.id)}
            className='custom-button'
          >
            Raw Data
          </button>
        ),
      },
    ];

     function rawDataClicked(id){
         dispatch(deviceActions.clickedDevice({
          id 
         }));
         navigate('/rawData');
    }


  
    return {
      rows: devices,
      columns: columns,
    };
  }, [devices]);
  
  React.useEffect(() => {
    const fetchDevices = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/get-router-data'); // Replace with your API endpoint
        if (!response.ok) {
          throw new Error('Failed to fetch data');
        }
        const responseData = await response.json();
        const modifiedData = responseData.map(item => ({ ...item, id: item.deviceId }));
        setDevices(modifiedData);
        console.log(modifiedData);
      } catch (error) {
        console.error('Error fetching devices:', error);
      }
    };
 
    if (!isModalOpen) {
      fetchDevices();
    }
  }, [isModalOpen]);

  return (
    <Box sx={{ height: 400, width: '100%' }}>
      <DataGrid
        {...data}
        autoHeight={true} 
        hideFooterPagination={true}
        disableColumnFilter
        disableColumnSelector
        disableDensitySelector
        slots={
             { toolbar: GridToolbar }
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