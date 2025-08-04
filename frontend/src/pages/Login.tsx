import { useState } from "react";
import { useNavigate } from "react-router-dom";
import type { LoginDto } from "../dto/auth/loginDto";
import authApi from "../api/authApi";
import axios from "axios";
import toast from "react-hot-toast";

const LoginPage = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    const payload: LoginDto = { email, password };

    try {
      const res = await authApi.login(payload);

      console.log("Logged in");
      toast.success("Logged in Sucessfully");

      navigate("/home");
    } catch (error) {
      if (axios.isAxiosError(error)) {
        const message = error.response?.data?.message;
        toast.error(message);
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex justify-center items-center h-screen bg-[#000300] text-white">
      <form
        onSubmit={handleSubmit}
        className="bg-[#111] p-8 rounded-xl w-full max-w-md shadow-lg"
      >
        <h2 className="text-3xl font-bold mb-6 text-center text-[#00df9a]">
          Login to SkillSwap
        </h2>

        <label className="block mb-2 text-sm font-medium">Email</label>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="w-full p-3 mb-4 bg-gray-800 rounded-md text-white outline-none focus:ring-2 focus:ring-[#00df9a]"
          required
        />

        <label className="block mb-2 text-sm font-medium">Password</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="w-full p-3 mb-6 bg-gray-800 rounded-md text-white outline-none focus:ring-2 focus:ring-[#00df9a]"
          required
        />

        <button
          type="submit"
          className="w-full bg-[#00df9a] py-3 rounded-md text-black font-semibold hover:bg-[#00c57a] transition-colors"
        >
          Login
        </button>

        <p className="mt-4 text-center text-gray-400 text-sm">
          Don’t have an account?{" "}
          <span
            onClick={() => navigate("/signup")}
            className="text-[#00df9a] cursor-pointer hover:underline"
          >
            Sign up
          </span>
        </p>
      </form>
    </div>
  );
};

export default LoginPage;
