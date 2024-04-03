import React from 'react';
import './Navbar.css';

export default function Navbar(){
    function handleLogout(){
        console.log("LogOut Button Clicked");
    }
    return (
        <nav className="navbar">
      <div className="navbar-container">
        <h1 className="navbar-logo">Router Configuration</h1>
        <button className='logout-button' onClick={handleLogout}>LogOut</button>
      </div>
    </nav>
    )
}