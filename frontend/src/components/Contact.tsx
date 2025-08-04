const Contact = () => {
  return (
    <section className="text-white bg-black h-[calc(100vh)] flex items-center">
      <div className="max-w-3xl mx-auto px-4 text-center">
        <h2 className="text-3xl sm:text-5xl font-bold mb-6">Get in Touch</h2>
        <p className="text-lg sm:text-xl text-gray-400 mb-8">
          Have questions, suggestions, or want to collaborate? We'd love to hear
          from you.
        </p>
        <form className="bg-gray-900 p-8 rounded-xl shadow-lg space-y-6">
          <input
            type="text"
            placeholder="Your Name"
            className="w-full px-4 py-3 rounded-lg bg-gray-800 text-white focus:outline-none focus:ring-2 focus:ring-[#00df9a]"
          />
          <input
            type="email"
            placeholder="Your Email"
            className="w-full px-4 py-3 rounded-lg bg-gray-800 text-white focus:outline-none focus:ring-2 focus:ring-[#00df9a]"
          />
          <textarea
            placeholder="Your Message"
            rows={5} 
            className="w-full px-4 py-3 rounded-lg bg-gray-800 text-white focus:outline-none focus:ring-2 focus:ring-[#00df9a]"
          ></textarea>

          <button
            type="submit"
            className="w-full bg-[#00df9a] text-black py-3 rounded-xl font-medium hover:bg-[#00c57a] transition-colors"
          >
            Send Message
          </button>
        </form>
      </div>
    </section>
  );
};

export default Contact;
