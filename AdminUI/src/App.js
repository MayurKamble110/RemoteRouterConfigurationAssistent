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

  function App() {
    return (
      <Router>
        <div>
        <Header/>
        <SideNav/>
        <Routes>
            <Route path="/" Component={Content} />
            <Route path='/device' Component={DeviceData}/>
            <Route path='/commands' Component={CommandsInfo}/>
        </Routes>
        <Footer/>
      </div>
     </Router>
    );
  }

  export default App;
