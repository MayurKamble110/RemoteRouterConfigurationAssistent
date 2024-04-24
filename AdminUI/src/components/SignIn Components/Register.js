import { useState } from "react"
import { Link } from "react-router-dom"
import { useNavigate } from "react-router-dom";
import { NotifyErrorToast, NotifySuccessToast } from "../../data/ToastData";
export default function Register() {
    const [name, setName] = useState('');
    const [emailID, setEmail] = useState('');
    const [password, setPassword] =useState('');
    const navigate = useNavigate();

    const submitHandler = async(event)=>{
        console.log(name);
        event.preventDefault();
        console.log(emailID);
        const emailRegex = /^[a-zA-Z0-9._-]+@gmail\.com$/;
        if(!name || !emailID || !password){
            NotifyErrorToast('Please enter all the fields');
            return;
        }
        if (!emailRegex.test(emailID)) {
            NotifyErrorToast('Invalid email format. Please use a Gmail address.');
            return;
        }
        if(password.length <8){
            NotifyErrorToast('Invalid password. Password length should be at least 8');
            return;    
        }
        try{
            const response = await fetch('http://localhost:8080/auth/create-user',{
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json',
                },
                body : JSON.stringify({emailID, name, password }),
                mode: 'cors'
            });
            console.log(response);
            if(response.status == 400)
            {
                NotifyErrorToast('User Already Exists..');
            }
             const data = await response.text();

            if(data)
            {
                NotifySuccessToast(`Successfully registered user ${name}`)
                navigate('/');
            }
            else
            {
                console.log('login failed');
            }

; 
        }
        catch(e)
        {
            console.log(e.message);
            NotifyErrorToast(`${e.error}`);
        }
    }
    return (
        <div className="hold-transition login-page" style={{ backgroundColor: "#454d55" }}>
            <div className="register-box">
                <div className="card card-outline card-primary">
                    <div className="card-header text-center">
                        <p className="h1">FS Net-Insight</p>
                    </div>
                    <div className="card-body">
                        <p className="login-box-msg">Register a new membership</p>
                        <form>
                            <div className="input-group mb-3">
                                <input required type="text" className="form-control" autoComplete="off" placeholder="Name" onChange={e=>setName(e.target.value)}/>
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-user" />
                                    </div>
                                </div>
                            </div>
                            <div className="input-group mb-3">
                                <input required type="email" className="form-control" autoComplete="off" placeholder="Email" onChange={e => setEmail(e.target.value)}/>
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-envelope" />
                                    </div>
                                </div>
                            </div>
                            <div className="input-group mb-3">
                                <input required  type="password" className="form-control" autoComplete="off" placeholder="Password" onChange={e => setPassword(e.target.value)} />
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-lock" />
                                    </div>
                                </div>
                            </div>       
                            <div className="row" style={{ display: 'flex', justifyContent: 'center' }}>
                                <div className="col-4" >
                                    <button onClick={submitHandler} type="submit" className="btn btn-primary btn-block" style={{ textAlign: 'center' }}>Register</button>
                                </div>
                             </div>
                        </form>
        
                        <Link to="/" className="text-center" style={{ marginTop:'10px', display: 'flex', justifyContent: 'center' }}>I already have a membership</Link>
                    </div>
                    {/* /.form-box */}
                </div>{/* /.card */}
            </div>
        </div>
    )
}