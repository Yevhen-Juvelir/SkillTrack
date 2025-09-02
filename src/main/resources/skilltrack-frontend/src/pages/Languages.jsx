import React, { useState } from "react";
import existingLanguages from "../data/existing-languages";

const Languages = () => {
  const [flippedCard, setFlippedCard] = useState(null); // slug of flipped card
  const [chosen, setChosen] = useState([]); // { name, slug, level }

  const handleSelectLevel = (language, level) => {
    // Update chosen array
    const entry = { name: language.name, slug: language.slug, level };
    setChosen((prev) => {
      const filtered = prev.filter((item) => item.slug !== language.slug);
      return [...filtered, entry];
    });
    setFlippedCard(null); // flip back after selection
  };

  const getSelectedLevel = (slug) => {
    const found = chosen.find((item) => item.slug === slug);
    return found ? found.level : null;
  };

  return (
    <div className="flex items-start justify-center h-screen bg-ui p-10 gap-10">
      {/* Languages grid */}
      <div className="flex flex-wrap gap-4 flex-1 justify-center">
        {existingLanguages.map((language) => {
          const isFlipped = flippedCard === language.slug;
          const selectedLevel = getSelectedLevel(language.slug);

          return (
            <div
              key={language.slug}
              className="w-24 h-32 perspective"
              onClick={() => !isFlipped && setFlippedCard(language.slug)}
            >
              <div
                className={`relative w-full h-full duration-500 transform-style preserve-3d transition-transform ${
                  isFlipped ? "rotate-y-180" : ""
                }`}
              >
                {/* Front */}
                <div className="absolute backface-hidden w-full h-full bg-accent-100 hover:bg-accent-200 text-white flex flex-col items-center justify-center rounded-xl p-3 shadow-lg cursor-pointer">
                  <div className="w-12 h-12 bg-white/20 rounded-full flex items-center justify-center mb-2">
                    <img
                      src={language.icon}
                      alt={language.name}
                      className="w-2/3 h-2/3 object-contain"
                    />
                  </div>
                  <div className="text-center bg-black/25 px-2 py-1 rounded">
                    {language.name}
                  </div>
                  {selectedLevel && (
                    <div className="mt-1 text-sm text-green-300">
                      {selectedLevel}
                    </div>
                  )}
                </div>

                {/* Back */}
                <div className="absolute backface-hidden w-full h-full bg-accent-100 text-white flex flex-col items-center justify-center rounded-xl shadow-lg rotate-y-180">
                  <h3 className="mb-2 font-bold text-center">Select Level</h3>
                  <div className="flex flex-col gap-2">
                    {language.levels.map((level) => (
                      <button
                        key={level}
                        onClick={() => handleSelectLevel(language, level)}
                        className="py-1 rounded-lg bg-gray-200 text-black hover:bg-green-400 hover:text-white w-full"
                      >
                        {level}
                      </button>
                    ))}
                  </div>
                  <button
                    onClick={() => setFlippedCard(null)}
                    className="mt-auto px-3 py-1 bg-red-600 rounded-lg hover:bg-red-500 text-white text-sm transition-colors duration-200"
                  >
                    Cancel
                  </button>
                </div>
              </div>
            </div>
          );
        })}
      </div>

      {/* Side tracker */}
      <div className="w-64 bg-accent-100 text-white p-5 rounded-xl shadow-lg flex flex-col gap-4">
        <h2 className="text-lg font-bold">Your Selection</h2>
        {chosen.length === 0 ? (
          <p className="text-sm text-gray-200">No languages selected yet</p>
        ) : (
          <ul className="flex flex-col gap-2">
            {chosen.map((item) => (
              <li
                key={item.slug}
                className="bg-black/25 px-3 py-2 rounded-md flex justify-between items-center"
              >
                <span>{item.name}</span>
                <span className="text-green-300 text-sm">{item.level}</span>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
};

export default Languages;
