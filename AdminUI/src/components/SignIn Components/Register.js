import { useState } from "react"
import { Link } from "react-router-dom"
export default function Register() {
    const [userName, setUserName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] =useState('');

    function submitHandler(){
        console.log('Clickefd')
    }
    return (
        <div className="hold-transition login-page">
            <div className="register-box">
                <div className="card card-outline card-primary">
                    <div className="card-header text-center">
                        <p className="h1"><b>FS</b> Net-Insight</p>
                    </div>
                    <div className="card-body">
                        <p className="login-box-msg">Register a new membership</p>
                        <form>
                            <div className="input-group mb-3">
                                <input type="text" className="form-control" placeholder="Full name" onChange={e=>setUserName(e.target.value)}/>
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-user" />
                                    </div>
                                </div>
                            </div>
                            <div className="input-group mb-3">
                                <input type="email" className="form-control" placeholder="Email" onChange={e => setEmail(e.target.value)}/>
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-envelope" />
                                    </div>
                                </div>
                            </div>
                            <div className="input-group mb-3">
                                <input type="password" className="form-control" placeholder="Password" />
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-lock" />
                                    </div>
                                </div>
                            </div>       
                            <div className="row">
                                <div className="col-4">
                                    <button onClick={submitHandler} type="submit" className="btn btn-primary btn-block">Register</button>
                                </div>
    
                            </div>
                        </form>
                        <Link to="/" className="text-center">I already have a membership</Link>
                    </div>
                    {/* /.form-box */}
                </div>{/* /.card */}
            </div>
        </div>
    )
}