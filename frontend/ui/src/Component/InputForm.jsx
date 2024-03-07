import { useState } from "react"
import './InputForm.css'
import { useNavigate } from "react-router-dom";

export default function InputForm(){

    const [username , setUsername] = useState('');
    const [password , setPassword] = useState('');
    const [hostIp , setHostIP] = useState('');
    const navigate = useNavigate();
    const handleClick = async () =>
    {
        try{
            const response = await fetch('http://localhost:8080/api/connect',{
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json',
                },
                body : JSON.stringify({username , password , hostIp}),
                mode: 'cors'
            });

            if(!response.ok)
             throw new Error('Invalid Credentials');
            
             const data = await response.text();

             console.log(data)
             if(data==='1'){
                navigate('/commands');
             }
             
; 
        }
        catch(e)
        {
            console.log(e.message);
        }
    };

    return(
        <div className="container">
             <h1>Router Configuration</h1>
            <div>
                <label className="label">Enter Username: </label>
                <input className="inputField" type="text" required placeholder="Username" onChange={e => setUsername(e.target.value)}></input>
            </div>
            <div>
                <label className="label">Enter Password: </label>
                <input className="inputField" type="password" required placeholder="Password" onChange={e => setPassword(e.target.value)}></input>
            </div>
            <div>
                <label className="label">Enter Host IP: </label>
                <input className="inputField" type="text" required placeholder="Host IP" onChange={e => setHostIP(e.target.value)}></input>
            </div>
            <div>
                <button className="button" onClick={handleClick}>Submit</button>
            </div>

        </div>
    )

}