import Hero from "../components/Hero";
import AboutUs from "../components/AboutUs";
import Contact from "../components/Contact";
import { Navbar } from "../components/Navbar";

const LandingPage = () => {
  return (
    <main>
      <section>
        <Navbar />
      </section>
      <section id="hero">
        <Hero />
      </section>
      <section id="about-us">
        <AboutUs />
      </section>
      <section id="contact">
        <Contact />
      </section>
    </main>
  );
};

export default LandingPage;
