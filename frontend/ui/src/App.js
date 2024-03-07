import HomePage from "./Component/HomePage";
import InputForm from "./Component/InputForm";
import { BrowserRouter ,Routes, Route, Switch } from 'react-router-dom';



function App() {
  return (
      <Routes>
        <Route path="/" element={<InputForm/>}></Route>
        <Route path="/commands" element={<HomePage/>}></Route>
      </Routes>
  );
}

export default App;
