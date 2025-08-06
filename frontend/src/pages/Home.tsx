import { useEffect, useState } from "react";
import dashboardApi from "../api/dashboardApi";
import { Navbar } from "../components/Navbar";
import SideBar from "../components/SideBar";
import SkillsList from "../components/SkillList";

export default function Home() {
  const [offers, setOffers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

    const getAllOfferedSkillsHome = async (filters = {}) =>{

      try{
        const res = await dashboardApi.getAllOfferedSkills(filters);
        setOffers(res);
        
      }catch(err){

      }
    }

    useEffect(() =>{
      getAllOfferedSkillsHome();
    }, [])


  return (
    <main>
      <section>
        <Navbar />
      </section>
      <section>
        <div className="flex min-h-screen bg-gray-900 text-white">
          <SideBar onSearch={getAllOfferedSkillsHome}/>
          <main className="flex-1 p-6">
            <SkillsList offers = {offers} />
          </main>
        </div>
      </section>
    </main>
  );
}
