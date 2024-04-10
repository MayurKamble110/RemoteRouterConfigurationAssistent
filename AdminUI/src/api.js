// api.js

export const fetchDevices = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/routers');
      if (!response.ok) {
        throw new Error('Failed to fetch data');
      }
      const responseData = await response.json();
      return responseData.map(item => ({ ...item, id: item.deviceId }));
    } catch (error) {
      console.error('Error fetching devices:', error);
      return []; // Return an empty array in case of error
    }
  
};
