// api.js
export const fetchDevices = async (jwtToken) => {  
    try {
      
        const token = jwtToken;
        const header = {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        };
      const response = await fetch('http://localhost:8080/api/routers', {
      headers: header
    });
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
