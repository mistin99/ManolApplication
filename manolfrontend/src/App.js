import "./App.css";
import Login from "./Login";
import Register from "./Register";
import Home from "./Home";
import { Routes, Route } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/Home" element = {<Home/>} />
        <Route path="/" element={<Login />} />
        <Route path="Register" element={<Register />} />

      </Routes>
    </div>
  );
}

export default App;
