import { useState } from "react";
import { useNavigate } from "react-router-dom";

const SignUpPage = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    

    navigate("/dashboard");
  };

  return (
    <div className="flex justify-center items-center h-screen bg-[#000300] text-white">
      <form
        onSubmit={handleSubmit}
        className="bg-[#111] p-8 rounded-xl w-full max-w-md shadow-lg"
      >
        <h2 className="text-3xl font-bold mb-6 text-center text-[#00df9a]">
          SignUp to SkillSwap
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
          Already have an account?{" "}
          <span onClick={() => navigate("/login")} className="text-[#00df9a] cursor-pointer hover:underline">
            Login in
          </span>
        </p>
      </form>
    </div>
  );
};

export default SignUpPage;
