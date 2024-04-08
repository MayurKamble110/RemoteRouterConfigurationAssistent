
import Device from "./Component/Device";
import InfoTable from "./Component/InfoTable";
import Navbar from "./Component/Navbar";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import RawData from "./Component/RawData";


function App() {
  return (
    <Router>
      <div>
        <Navbar />
        <Device />
        <Routes>
          <Route path="/" element={<InfoTable />} />
          <Route path="/rawData" element={<RawData />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
