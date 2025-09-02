import './App.css'
import HomePage from './pages/HomePage'
import Register from './components/Register'
import Login from './components/Login'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/homepage" element={<HomePage />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/existing-languages" element={<Login />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
