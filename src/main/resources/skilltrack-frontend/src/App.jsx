import './App.css'
import HomePage from './pages/HomePage'
import Register from './components/Register'
import Login from './components/Login'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Languages from './pages/Languages';

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/homepage" element={<HomePage />} />
        <Route path="/register" element={<Register />} />
        <Route path="/existing-languages" element={<Languages />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
