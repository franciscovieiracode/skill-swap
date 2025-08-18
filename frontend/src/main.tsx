
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import LandingPage from "./pages/LandingPage";
import { Route, Routes, BrowserRouter } from "react-router-dom";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import { Toaster } from "react-hot-toast";
import Home from "./pages/Home";
import { AuthProvider } from "./context/AuthContext";
import SkillDetails from "./components/SkillDetails";
import Notifications from "./configs/Notifications";

createRoot(document.getElementById("root")!).render(
<StrictMode>
  <AuthProvider>
    <BrowserRouter>
      <Notifications />
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/home" element={<Home />} />
        <Route path="/skill/:id" element={<SkillDetails />} />
      </Routes>
    </BrowserRouter>
    <Toaster position="top-right" />
  </AuthProvider>
</StrictMode>
);
