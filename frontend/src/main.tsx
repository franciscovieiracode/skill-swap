import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import { Navbar } from "./components/Navbar";
import LandingPage from "./pages/LandingPage";
import { Route, Routes, BrowserRouter } from "react-router-dom";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import { Toaster } from "react-hot-toast";
import Home from "./pages/Home";
import { AuthProvider } from "./context/AuthContext";


createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <AuthProvider>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/login" element={<Login/>}/>
        <Route path="/signup" element={<SignUp/>}/>
        <Route path="/home" element={<Home/>} />
      </Routes>
    </BrowserRouter>
    <Toaster position="top-right"/>
    </AuthProvider>
  </StrictMode>
);
