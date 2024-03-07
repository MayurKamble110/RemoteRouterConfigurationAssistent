import { useState } from "react"

export default function HomePage(){
    const [query,setQuery]=useState('');
    const [response,setResponse] = useState('');

    const handleQuery=(e)=>{
        
    }

    const handleResponse=()=>{

    }
    return(
        <>
        <h1>Welcome to Router Configuration</h1>
         <div className="container">    
            <div className="box">
            <label className="label">Enter your command: </label>   
            <input 
            className="input" 
            type="text" 
            placeholder="Enter query"
            onChange={handleQuery}
            ></input> 
            <button onClick={handleResponse}>Fetch Response</button>
            </div>
            <div className="container">
            </div>
        </div>
        </>
       
    )
}