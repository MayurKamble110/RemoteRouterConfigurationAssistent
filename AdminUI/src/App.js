  import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
  import Header from "./components/Header";
  import Home from "./components/Home";
  import SideNav from "./components/SideNav";
  import Footer from "./components/Footer";
  import Content from "./components/Content";
  import Forms from "./components/Forms";
  import Charts from './components/Charts';
  import DeviceData from './components/DeviceData';
import CommandsInfo from './components/CommandsInfo';
import LoginPage from './components/SignIn Components/LoginPage';
import ForgotPassword from './components/SignIn Components/Forgot-password';
import Register from './components/SignIn Components/Register';
let isLoggedIn = false;
  function App() {
    return (
      <Router>
        <div>
        {isLoggedIn 
          ? 
          (<Routes>
            <Route path="/" Component={LoginPage} />
            <Route path="/register" Component={Register} />
            <Route path="/forgot-password" Component={ForgotPassword} />
          </Routes>)
          : 
        (<>
        <Header/>
        <SideNav/>
        <Routes>
            
            <Route path="/content" Component={Content} />
            <Route path='/device' Component={DeviceData}/>
            <Route path='/commands' Component={CommandsInfo}/>
        </Routes>
        <Footer/>
        </>)}
      </div>
     </Router>
  
    );
  }

  export default App;
