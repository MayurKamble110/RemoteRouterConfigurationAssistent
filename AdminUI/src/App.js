import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
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
  console.log(userName);
  return (
    <Router>
      {!userName &&
        <Routes>
          <Route path="/" Component={LoginPage} />
          <Route path="/register" Component={Register} />
        </Routes>
      }
      {userName &&
        <Routes>
          <Route path="/content" Component={Content} />
          <Route path="/device" Component={DeviceData} />
          <Route path="/commands" Component={CommandsInfo} />
        </Routes>
      }
      {userName && (
        <>
          <Header />
          <SideNav />
          <Footer />
        </>
      )}
    </Router>

    // <Router>
    //   <Header />
    //   <SideNav />
    //   <Footer />
    //   <Routes>
    //     <Route path='/' Component={LoginPage}></Route>
    //     <Route path="/register" Component={Register} />
    //     <Route path="/content" Component={Content} />
    //     <Route path="/device" Component={DeviceData} />
    //    <Route path="/commands" Component={CommandsInfo} />
    //   </Routes>
    // </Router>

  );
}

export default App;
