import { Link } from "react-router-dom";

export default function LoginPage(){
    return (
        <div className="hold-transition login-page">
            <div className="login-box">
            {/* /.login-logo */}
            <div className="card card-outline card-primary">
                <div className="card-header text-center">
                <p className="h1"><b>FS</b> Net-Insight</p>                </div>
                <div className="card-body">
                    <p className="login-box-msg">Sign in to start your session</p>
                    <form action="../../index3.html" method="post">
                        <div className="input-group mb-3">
                            <input type="email" className="form-control" placeholder="Email" />
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
                            {/* /.col */}
                            <div className="col-4">
                                <button type="submit" className="btn btn-primary btn-block">Sign In</button>
                            </div>
                            {/* /.col */}
                        </div>
                    </form>
                   
                    {/* /.social-auth-links */}
                    <p className="mb-1">
                        <Link to="/forgot-password">I forgot my password</Link>
                    </p>
                    <p className="mb-0">
                        <Link to="/register" className="text-center">Register a new membership</Link>
                    </p>
                </div>
                {/* /.card-body */}
            </div>
            {/* /.card */}
            </div>
        </div>
    )
}