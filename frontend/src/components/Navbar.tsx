import { useState, useEffect } from "react";
import { AiOutlineClose, AiOutlineMenu } from "react-icons/ai";
import { useNavigate } from "react-router-dom";


export const Navbar = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const navigate = useNavigate();


  useEffect(() => {
    const handleResize = () => {
      if (window.innerWidth >= 768) {
        setIsMenuOpen(false);
      }
    };

    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  const menuItems = ["Home","About Us", "Contact"] as const;
  type MenuItem = (typeof menuItems)[number];

  const sectionIds: Record<MenuItem, string> = {
    "About Us": "about-us",
    "Contact": "contact",
    "Home": "home"
  };

  return (
    <nav className="flex justify-between items-center h-24 max-w-[1240px] mx-auto px-4 text-white relative">
      {/* Main Logo */}
      <h1 className="text-3xl font-bold text-[#00df9a]">SKILLSWAP.</h1>

      {/* Desktop Menu */}
      <ul className="hidden md:flex space-x-6">
        {menuItems.map((item) => (
          <li
            key={item}
            onClick={() => {
              document.getElementById(sectionIds[item])?.scrollIntoView({
                behavior: "smooth",
              });
            }}
            className="p-4 cursor-pointer hover:text-[#00df9a]"
          >
            {item}
          </li>
        ))}
        <button onClick={() => navigate("/login")} className="bg-[#00df9a] px-8 py-2 rounded-xl font-medium text-black hover:bg-[#00c57a] transition-colors">
          Login
        </button>
      </ul>

      {/* Mobile Toggle */}
      <button
        onClick={() => setIsMenuOpen((prev) => !prev)}
        aria-label="Toggle Menu"
        className="block md:hidden"
      >
        {isMenuOpen ? (
          <AiOutlineClose size={20} />
        ) : (
          <AiOutlineMenu size={20} />
        )}
      </button>

      {/* Mobile Menu */}
      <div
        className={`fixed top-0 left-0 h-full w-[60%] bg-[#000300] border-r border-gray-900 transform transition-transform duration-500 ${
          isMenuOpen ? "translate-x-0" : "-translate-x-full"
        }`}
      >
        {/* Background Logo (aligned perfectly) */}
        <h1 className="absolute top-0 left-0 text-3xl font-bold text-[#00df9a] h-24 flex items-center px-4">
          SKILLSWAP.
        </h1>

        {/* Menu Items */}
        <ul className="uppercase mt-24 p-4">
          {menuItems.map((item) => (
            <li
              key={item}
              onClick={() => {
                document.getElementById(sectionIds[item])?.scrollIntoView({
                  behavior: "smooth",
                });
              }}
              className="p-4 border-b border-gray-600 last:border-b-0 cursor-pointer hover:text-[#00df9a]"
            >
              {item}
            </li>
          ))}
          <button onClick={() => navigate("/login")} className="bg-[#00df9a] px-7 py-1 mt-4 ml-2 rounded-xl font-medium text-black hover:bg-[#00c57a] transition-colors">
            Login
          </button>
        </ul>
      </div>
    </nav>
  );
};
