import { useState, useEffect, useContext, useRef } from "react";
import { AiOutlineClose, AiOutlineMenu } from "react-icons/ai";
import { IoIosNotificationsOutline } from "react-icons/io";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";
import authApi from "../api/authApi";

export const Navbar = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const navigate = useNavigate();
  const { user, setUser } = useContext(AuthContext);
  const dropdownRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleResize = () => {
      if (window.innerWidth >= 768) {
        setIsMenuOpen(false);
      }
    };

    const handleClickOutside = (event: MouseEvent) => {
      if (
        dropdownRef.current &&
        !dropdownRef.current.contains(event.target as Node)
      ) {
        setIsDropdownOpen(false);
      }
    };

    window.addEventListener("resize", handleResize);
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      window.removeEventListener("resize", handleResize);
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  const handleLogout = async () => {
    await authApi.logout();
    setUser(null);
    navigate("/");
  };

  const menuItems = [
    { label: "Home", type: "link", path: "/" },
    { label: "About Us", type: "scroll", targetId: "about-us" },
    { label: "Contact", type: "scroll", targetId: "contact" },
  ] as const;

  type MenuItem = (typeof menuItems)[number]["label"];

  const sectionIds: Record<MenuItem, string> = {
    "About Us": "about-us",
    Contact: "contact",
    Home: "home",
  };

  return (
    <nav className="flex justify-between items-center h-24 max-w-[1240px] mx-auto px-4 text-white relative">
      <h1 className="text-3xl font-bold text-[#00df9a]">SKILLSWAP.</h1>

      {user ? (
        <div className="flex items-center space-x-6">
          {/* Notifications Icon Placeholder */}
          <button className="hidden md:block"><IoIosNotificationsOutline size={28}/></button>

          {/* Profile Dropdown */}
          <div className="relative" ref={dropdownRef}>
            <button
              onClick={() => setIsDropdownOpen((prev) => !prev)}
              className="w-10 h-10 rounded-full bg-gray-500 flex items-center justify-center overflow-hidden cursor-pointer"
            >
              {user.avatarUrl ? (
                <img
                  src={user.avatarUrl}
                  alt="Profile"
                  className="w-full h-full object-cover"
                />
              ) : (
                <span className="text-white font-bold">
                  {user.name?.charAt(0).toUpperCase() || "U"}
                </span>
              )}
            </button>

            {isDropdownOpen && (
              <div className="absolute right-0 mt-2 w-48 bg-white text-black rounded-xl shadow-lg py-2 z-50">
                <button
                  onClick={() => navigate("/profile")}
                  className="block w-full text-left px-4 py-2 hover:bg-gray-100"
                >
                  Profile
                </button>
                <button
                  onClick={() => navigate("/skills")}
                  className="block w-full text-left px-4 py-2 hover:bg-gray-100"
                >
                  Skills
                </button>
                <button
                  onClick={() => navigate("/favorites")}
                  className="block w-full text-left px-4 py-2 hover:bg-gray-100"
                >
                  Favorites
                </button>
                <button
                  onClick={() => navigate("/history")}
                  className="block w-full text-left px-4 py-2 hover:bg-gray-100"
                >
                  History
                </button>
                <button
                  onClick={() => navigate("/settings")}
                  className="block w-full text-left px-4 py-2 hover:bg-gray-100"
                >
                  Settings
                </button>
                <button
                  onClick={handleLogout}
                  className="block w-full text-left px-4 py-2 text-red-500 hover:bg-gray-100"
                >
                  Logout
                </button>
              </div>
            )}
          </div>
        </div>
      ) : (
        <div>
          <ul className="hidden md:flex space-x-6">
            {menuItems.map((item) => (
              <li
                key={item.label}
                onClick={() => {
                  if (item.type === "scroll") {
                    document
                      .getElementById(item.targetId)
                      ?.scrollIntoView({ behavior: "smooth" });
                  } else {
                    navigate("/home");
                  }
                }}
                className="p-4 cursor-pointer hover:text-[#00df9a]"
              >
                {item.label}
              </li>
            ))}
            <button
              onClick={() => navigate("/login")}
              className="bg-[#00df9a] px-8 py-2 rounded-xl font-medium text-black hover:bg-[#00c57a] transition-colors"
            >
              Login
            </button>
          </ul>

          {/* Mobile Menu Toggle */}
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
            <h1 className="absolute top-0 left-0 text-3xl font-bold text-[#00df9a] h-24 flex items-center px-4">
              SKILLSWAP.
            </h1>

            <ul className="uppercase mt-24 p-4">
              {menuItems.map((item) => (
                <li
                  key={item.label}
                  onClick={() => {
                    setIsMenuOpen(false);
                    if (item.type === "scroll") {
                      document
                        .getElementById(item.targetId)
                        ?.scrollIntoView({ behavior: "smooth" });
                    } else {
                      navigate("/home");
                    }
                  }}
                  className="p-4 border-b border-gray-600 last:border-b-0 cursor-pointer hover:text-[#00df9a]"
                >
                  {item.label}
                </li>
              ))}
              <button
                onClick={() => {
                  setIsMenuOpen(false);
                  navigate("/login");
                }}
                className="bg-[#00df9a] px-7 py-1 mt-4 ml-2 rounded-xl font-medium text-black hover:bg-[#00c57a] transition-colors"
              >
                Login
              </button>
            </ul>
          </div>
        </div>
      )}
    </nav>
  );
};
