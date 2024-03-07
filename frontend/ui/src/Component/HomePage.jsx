import { useState } from "react"

export default function HomePage(){
    const [command,setCommand]=useState('');
    const [data,setData] = useState('');

    const handleQuery=(e)=>{
         setCommand(e.target.value)  ; 
    }

    const handleResponse = async () =>
    {
        try{
            const response = await fetch('http://localhost:8080/api/commands',{
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json',
                },
                body : JSON.stringify({command}),
                mode: 'cors'
            });

            if(!response.ok)
             throw new Error('Invalid Credentials');
            
             const dataresp = await response.text();
             setData(dataresp);
        }
        catch(e)
        {
            console.log(e.message);
        }
    };
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
                {
                    data ?
                    <p>{data}</p> :
                    'Loading the data'
                }
            </div>
        </div>
        </>
       
    )
}