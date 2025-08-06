import { useEffect, useState } from "react";
import dashboardApi from "../api/dashboardApi";
import SkillComponent from "./SkillComponent";

export default function SkillsList() {
  const [allOffers, setAllOffers] = useState([]);

  useEffect(() => {
    const getAllOfferedSkills = async () => {
      try {
        const res = await dashboardApi.getAllOfferedSkills();
        console.log(res);
        setAllOffers(res);
      } catch (err) {}
    };
    getAllOfferedSkills();
  }, []);

  return (
    <section className="p-4 bg-gray-700 rounded">
      <header>
        <div>Title</div>
      </header>
      <div className="grid grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 place-items-center">
        {" "}
        {allOffers.map((offer) => (
          <div >
            <SkillComponent key={offer.id} offer={offer}></SkillComponent>
          </div>
        ))}
      </div>
    </section>
  );
}
