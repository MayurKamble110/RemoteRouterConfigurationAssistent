
import Device from "./Component/Device";
import InfoTable from "./Component/InfoTable";
import Navbar from "./Component/Navbar";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';


function App() {
  return (
     <Router>
      <div>
      <Navbar/>
      <Device/>
         <Routes>
         <Route path="/" Component={InfoTable}></Route>
         </Routes>
     </div>
     </Router>
  );
}

export default App;

{/* <div>
      <Navbar/>
      <Device/>
      <InfoTable/>
   </div> */}