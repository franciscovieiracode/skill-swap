import React, { useContext } from "react";
import { Navbar } from "../components/Navbar";
import { AuthContext } from "../context/AuthContext";
import SideBar from "../components/SideBar";
import SkillsList from "../components/SkillList";

export default function Home() {
  return (
    <main>
      <section>
        <Navbar/>
      </section>
      <section>
            <div className="flex min-h-screen bg-gray-900 text-white">
      <SideBar />
      <main className="flex-1 p-6">
        <SkillsList />
      </main>
    </div>
      </section>
    </main>

  );
}