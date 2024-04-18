import { useState } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { userActions } from "../../Store";

export default function LoginPage(){
    const [emailID, setEmailID] = useState('');
    const [password, setPassword] = useState('');
    const dispatch = useDispatch();

    const navigate = useNavigate();
    const submitHandler = async(event)=>{
        event.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/auth/login',{
                method : 'POST',
                headers : {
                    'Content-Type' : 'application/json',
                },
                body : JSON.stringify({emailID, password}),
                mode : 'cors'
            });
            if(!response.ok)
            throw new Error('Invalid Credentials');
           
            const data = await response.json();

           if(data.jwt)
           {
            dispatch(userActions.signInSuccess({
                userName : data.userDto.name,
                jwtToken : data.jwt
            }))
            const userName = data.userDto.name;
            console.log(userName);
            navigate('/content');
           }
           else
           {
               console.log('login failed');
           }
        } catch (e) {
            console.log(e.message)
        }
    }
    return (
        <div className="hold-transition login-page" style={{ backgroundColor: "#454d55" }}>
            <div className="login-box">
            <div className="card card-outline card-primary">
                <div className="card-header text-center">
                <p className="h1">FS Net-Insight</p>
            </div>
                <div className="card-body">
                    <p className="login-box-msg">Sign in to start your session</p>
                    <form action="../../index3.html" method="post">
                        <div className="input-group mb-3">
                            <input type="email" className="form-control" placeholder="Email" onChange={e => setEmailID(e.target.value)} />
                            <div className="input-group-append">
                                <div className="input-group-text">
                                    <span className="fas fa-envelope" />
                                </div>
                            </div>
                        </div>
                        <div className="input-group mb-3">
                            <input type="password" className="form-control" placeholder="Password" onChange={e => setPassword(e.target.value)}/>
                            <div className="input-group-append">
                                <div className="input-group-text">
                                    <span className="fas fa-lock" />
                                </div>
                            </div>
                        </div>
                        <div className="row" style={{ display: 'flex', justifyContent: 'center' }}>
                            {/* /.col */}
                            <div className="col-4">
                                <button type="submit" onClick={submitHandler} className="btn btn-primary btn-block">Sign In</button>
                            </div>
                            {/* /.col */}
                        </div>
                    </form>
                    <p className="mb-0">
                        <Link to="/register" className="text-center" style={{ marginTop:'10px', display: 'flex', justifyContent: 'center' }}>Register a new membership</Link>
                    </p>
                </div>
                {/* /.card-body */}
            </div>
            {/* /.card */}
            </div>
        </div>
    )
}