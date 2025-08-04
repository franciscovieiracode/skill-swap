import {ReactTyped} from "react-typed";

const Hero = () => {
  return (
<section className="text-white h-[calc(100vh-96px)] flex items-center">
      <div className="max-w-5xl mx-auto text-center px-4">
        <p className="text-[#00df9a] font-semibold tracking-wide">
          CONNECT • LEARN • EXCHANGE
        </p>
        <h1 className="text-4xl sm:text-6xl md:text-7xl font-bold mt-4">
          Build your future.
        </h1>
        <div className="flex justify-center items-center mt-6">
          <h2 className="text-xl sm:text-4xl md:text-5xl font-semibold">
            Fast, easy skill swaps for
          </h2>
          <ReactTyped
            className="text-xl sm:text-3xl md:text-5xl font-semibold pl-2 md:pl-4 text-[#00df9a]"
            strings={[
              "designers",
              "developers",
              "writers",
              "marketers",
              "photographers",
              "musicians",
            ]}
            typeSpeed={100}
            backSpeed={80}
            loop
          />
        </div>
        <p className="text-lg md:text-2xl text-gray-400 mt-4">
          Learn new skills or share your expertise with others — no money required.
        </p>
        <button className="bg-[#00df9a] px-6 py-3 rounded-xl font-medium text-black hover:bg-[#00c57a] transition-colors mt-8">
          Join SkillSwap
        </button>
      </div>
    </section>
  );
};

export default Hero;
