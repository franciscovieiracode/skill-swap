import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { MdStar, MdFavorite, MdFavoriteBorder } from "react-icons/md";
import dashboardApi from "../api/dashboardApi";
import { Navbar } from "./Navbar";
import skillsApi from "../api/skillsApi";
import type { MakeRequestDto } from "../dto/skills/MakeRequestDto";

// Define type for details
interface SkillDetailsType {
  userId: string;
  userName: string;
  skillName: string;
  skillCategory: string;
  experience: string;
  description?: string;
}

export default function SkillDetails() {
  const { id } = useParams<{ id: string }>(); // Ensure id is string
  const navigate = useNavigate();
  const [details, setDetails] = useState<SkillDetailsType | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [isFavorite, setIsFavorite] = useState(false);

  useEffect(() => {
    if (!id) return; // id is undefined, cannot fetch
    const getSkillById = async () => {
      try {
        const res = await dashboardApi.getSkillById(id);
        console.log(res);
        
        setDetails(res);
      } catch (err) {
        console.error(err);
        setError("Failed to fetch skill details.");
      } finally {
        setLoading(false);
      }
    };
    getSkillById();
  }, [id]);

  const handleContact = async () => {
    if (!details || !id) return; // Safely check before using

    const payload: MakeRequestDto = {
      receiptingId: details.userId,
      skillId: id,
    };

    try {
      await skillsApi.makeRequest(payload);
    } catch (err) {
      console.error(err);
    }
  };

  const toggleFavorite = () => {
    
  }

  if (loading) {
    return (
      <div className="text-white p-8">
        <Navbar />
        <p>Loading skill details...</p>
      </div>
    );
  }

  if (error || !details) {
    return (
      <div className="text-white p-8">
        <Navbar />
        <p>{error || "Skill not found."}</p>
      </div>
    );
  }

  return (
    <div className="text-white p-8 bg-gray-800 min-h-screen">
      <Navbar />
      <div className="max-w-3xl mx-auto mt-8 p-6 border border-gray-600 rounded-lg bg-gray-900 shadow-lg">
        <div className="flex justify-between items-start">
          <h1 className="text-3xl font-bold mb-2">{details.skillName}</h1>
          <button onClick={toggleFavorite} className="text-red-500">
            {isFavorite ? <MdFavorite size={28} /> : <MdFavoriteBorder size={28} />}
          </button>
        </div>

        <p className="text-gray-300 mb-2">Category: {details.skillCategory}</p>

        <div className="flex items-center mb-4">
          <p className="text-gray-400 mr-2">Experience: {details.experience}</p>
          <MdStar size={18} className="text-yellow-500" />
        </div>

        <p
          onClick={() => navigate(`/profile/${details.userId}`)}
          className="text-blue-400 text-sm mb-4 cursor-pointer hover:underline"
        >
          Offered by: {details.userName}
        </p>

        {details.description && (
          <div className="bg-gray-700 p-4 rounded mb-6">
            <h3 className="text-xl font-semibold mb-2">Description</h3>
            <p className="text-gray-300">{details.description}</p>
          </div>
        )}

        <button
          onClick={handleContact}
          className="px-6 py-2 bg-green-600 hover:bg-green-700 rounded text-white"
        >
          Contact
        </button>
      </div>
    </div>
  );
}
