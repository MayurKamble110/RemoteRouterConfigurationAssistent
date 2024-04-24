import { BrowserRouter as Router, Route, Routes, Navigate} from 'react-router-dom';
import { useSelector } from 'react-redux';
import Header from "./components/Header";
import Home from "./components/Home";
import SideNav from "./components/SideNav";
import Footer from "./components/Footer";
import Content from "./components/Content";
import Charts from './components/Charts';
import DeviceData from './components/DeviceData';
import CommandsInfo from './components/CommandsInfo';
import LoginPage from './components/SignIn Components/LoginPage';
import Register from './components/SignIn Components/Register';

function App() {
  const userName = useSelector((state) => state.user.userName);
  const auth  = userName ? true : false ;
  return (
  
    <Router>
      {auth &&
        <>
          <Header></Header>
          <SideNav></SideNav>
          <Footer></Footer>
        </>}
      <Routes>
        <Route path='/' Component={LoginPage} />
        <Route path='/register' Component={Register} />
        <Route path='/device' element={auth ? <DeviceData /> : <Navigate to="/" />} />
        <Route path='/content' element={auth ? <Content /> : <Navigate to="/" />} />
        <Route path='/commands' element={auth ? <CommandsInfo /> : <Navigate to="/" />} />
      </Routes>
    </Router>
  );
}

export default App;
