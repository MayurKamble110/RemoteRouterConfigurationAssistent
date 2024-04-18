import { Link } from "react-router-dom"
import { useNavigate } from "react-router-dom"
import { useDispatch } from "react-redux";
import { userActions } from "../Store";
import { deviceActions } from "../Store";

export default function Header() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const logoutHandler = ()=>{
    dispatch(userActions.resetUser());
    dispatch(deviceActions.resetDevice());
  }
  return (
    <nav className="main-header navbar navbar-expand navbar-dark">
      {/* Left navbar links */}
      <ul className="navbar-nav">
        <li className="nav-item">
          <a className="nav-link" data-widget="pushmenu" href="#" role="button"><i className="fas fa-bars" /></a>
        </li>
        <li className="nav-item d-none d-sm-inline-block">
          <Link to="/content" className="nav-link">Home</Link>
        </li>
      </ul>
      {/* Right navbar links */}
      <ul className="navbar-nav ml-auto">
        {/* Navbar Search */}
        {/* */}
        <li className="nav-item" style={{display: 'flex'}}>
          <a className="nav-link" data-widget="fullscreen" href="#" role="button">
            <i className="fas fa-expand-arrows-alt" />  
          </a>
          <Link onClick={logoutHandler} to={'/'} className="nav-link" data-widget="logout" role="button" style={{ fontSize: '24px', display: 'flex', alignItems: 'center' }}>
          <ion-icon name="log-out-outline"></ion-icon>  
          </Link>
        </li>
      </ul>
    </nav>
  )
}