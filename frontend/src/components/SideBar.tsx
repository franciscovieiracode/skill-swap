import { useEffect, useState } from "react";
import dashboardApi from "../api/dashboardApi";

export default function SideBar( {onSearch} ) {
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("");
  const [dateRange, setDateRange] = useState("");
  const [sortByExperience, setSortByExperience] = useState(false);

  useEffect(() => {
    const getAllCategories = async () => {
      try {
        const res = await dashboardApi.getAllCategories();
        setCategories(res);
        console.log(res);
        
      } catch (err) {
        console.error(err);
      }
    };

    getAllCategories();
  }, []);


const handleSearch = () => {
  console.log("Searching with category:", selectedCategory);
  onSearch({
    category: selectedCategory,
    dateRange,
    sortByExperience,
  });
};

  return (
    <aside className="w-64 bg-gray-800 p-6 text-white min-h-screen">
      <h2 className="mb-4 text-lg font-semibold">Filter</h2>

      {/* Category Dropdown */}
      <label className="block mb-3 text-sm">Select Category</label>
      <select
        value={selectedCategory.name}
        onChange={(e) => setSelectedCategory(e.target.value)}
        className="w-full p-2 rounded bg-gray-700 text-white focus:outline-none focus:ring-2 focus:ring-blue-500 mb-4"
      >
        <option value="">-- Choose a Category --</option>
        {categories.map((category) => (
          <option key={category.id} value={category.name}>
            {category.name}
          </option>
        ))}
      </select>

      {/* Date Range Dropdown */}
      <label className="block mb-3 text-sm">Date Range</label>
      <select
        value={dateRange}
        onChange={(e) => setDateRange(e.target.value)}
        className="w-full p-2 rounded bg-gray-700 text-white focus:outline-none focus:ring-2 focus:ring-blue-500 mb-4"
      >
        <option value="">-- Any Time --</option>
        <option value="last7d">Last 7 Days</option>
        <option value="last30d">Last 30 Days</option>
      </select>

      {/* Points Sort Toggle */}
      <div className="flex items-center mb-4">
        <input
          type="checkbox"
          checked={sortByExperience}
          onChange={(e) => setSortByExperience(e.target.checked)}
          className="w-4 h-4 text-blue-600 bg-gray-700 border-gray-600 rounded focus:ring-blue-500"
        />
        <label className="ml-2 text-sm">Sort by Experience Points</label>
      </div>

      {/* Search Button */}
      <div className="text-center p-1 mt-4 bg-[#00df9a] rounded-2xl">
        <button
          onClick={handleSearch}
          className="cursor-pointer text-black font-semibold w-full py-2"
        >
          Search
        </button>
      </div>
    </aside>
  );
}
