import './Cards.css'
export default function Cards(props){
    return (
       <div className='card-wrapper'>
        <div className='card'>
            <img src = {props.image} alt = {props.name} />
            <div className='card-content'>
            <p>Device Name: {props.name}</p> 
            <p>IP Address: {props.ip_address}</p> 
            </div>
            </div>
            <div className='popup-content'>
            <p>Device Status: {props.status}</p> 
            <p>Hardware: {props.hardware}</p> 
            <p>OS: {props.os}</p> 
            <p>OS Version: {props.os_version}</p> 
            </div>
       </div>
    ) 
}