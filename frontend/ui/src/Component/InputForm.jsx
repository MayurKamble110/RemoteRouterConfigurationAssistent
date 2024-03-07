import { useState } from "react"


export default function InputForm(){

    const [username , setUsername] = useState('');
    const [password , setPassword] = useState('');
    const [hostIp , setHostIP] = useState('');
   
    const handleClick = async () =>
    {
        try{
            const response = await fetch('http://localhost:8080/connect',{
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
; 
        }
        catch(e)
        {
            console.log(e.message);
        }
    };

    return(
        <>
        
            <input type="text" required placeholder="Username" onChange={e => setUsername(e.target.value)}></input>
            <input type="password" required placeholder="Password" onChange={e => setPassword(e.target.value)}></input>
            <input type="text" required placeholder="Host IP" onChange={e => setHostIP(e.target.value)}></input>
            <button onClick={handleClick}>Submit</button>

        </>
    )

}