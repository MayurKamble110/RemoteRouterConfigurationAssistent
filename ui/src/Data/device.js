import routerImg from '../Images/router.jpg'
export const DEVICES = [
    {
        id: 1,
        name : 'DEVICE 1',
        ip_address : '123456789',
        status : 'down',
        hardware : 'Empty',
        os : 'Linux',
        os_version : '8.1'
    },
    {
        id: 2,
        image : routerImg,
        name : 'DEVICE 2',
        ip_address : '123456789',
        status : 'down',
        hardware : 'Empty',
        os : 'Linux',
        os_version : '8.2'
    },
    {
        id: 3,
        image : routerImg,
        name : 'DEVICE 3',
        ip_address : '123456789',
        status : 'up',
        hardware : 'Empty',
        os : 'Linux',
        os_version : '8.3'
    },
    {
        id: 4,
        image : routerImg,
        name : 'DEVICE 4',
        ip_address : '123456789',
        status : 'down',
        hardware : 'Empty',
        os : 'Linux',
        os_version : '8.4'
    }
];

export default DEVICES;