const AboutUs = () => {
  return (
    <section className="text-white bg-gray-900 h-[calc(100vh)] flex items-center">
      <div className="max-w-5xl mx-auto px-4 text-center">
        <h2 className="text-3xl sm:text-5xl font-bold mb-6">About SkillSwap</h2>
        <p className="text-lg sm:text-xl text-gray-400 leading-relaxed max-w-3xl mx-auto">
          SkillSwap is a community-driven platform where people exchange skills without money.
          Whether you're a designer looking to learn coding, a writer seeking marketing tips,
          or a musician wanting photography lessons — you can connect, teach, and learn
          together.
        </p>
        <div className="grid grid-cols-1 sm:grid-cols-3 gap-8 mt-10">
          <div className="bg-gray-800 rounded-xl p-6 shadow-lg hover:shadow-xl transition-shadow">
            <h3 className="text-xl font-semibold mb-2">Our Mission</h3>
            <p className="text-gray-400">Empower individuals to share and acquire skills freely.</p>
          </div>
          <div className="bg-gray-800 rounded-xl p-6 shadow-lg hover:shadow-xl transition-shadow">
            <h3 className="text-xl font-semibold mb-2">Our Community</h3>
            <p className="text-gray-400">A global network of learners and creators supporting each other.</p>
          </div>
          <div className="bg-gray-800 rounded-xl p-6 shadow-lg hover:shadow-xl transition-shadow">
            <h3 className="text-xl font-semibold mb-2">Our Vision</h3>
            <p className="text-gray-400">A world where skills flow freely, enabling limitless growth.</p>
          </div>
        </div>
      </div>
    </section>
  );
};

export default AboutUs;
