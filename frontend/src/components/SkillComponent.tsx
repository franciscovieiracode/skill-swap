import { useNavigate } from "react-router-dom";
import { MdStar } from "react-icons/md";

export default function SkillComponent({ offer }) {
  const navigate = useNavigate();

  return (
    <div
      onClick={() => navigate(`/skill/${offer.id}`)}
      className="cursor-pointer p-12 mb-6 w-80 border border-gray-500 rounded-lg bg-gray-800 shadow-md hover:shadow-xl transition-shadow duration-200"
    >
      <h3 className="text-lg font-semibold text-white mb-2">
        {offer.skillName}
      </h3>
      <p className="text-gray-300 text-sm">{offer.skillCategory}</p>
      <div className="flex flex-row">
        <p className="mt-2 text-gray-400 text-xm">
          Experience: {offer.experience}
        </p>
        <p className="text-center pt-3 mt-0.5">
          <MdStar size={12}></MdStar>
        </p>
      </div>
      <p className="text-gray-400 text-xs">Offered by: {offer.userName}</p>
      
    </div>
  );
}
