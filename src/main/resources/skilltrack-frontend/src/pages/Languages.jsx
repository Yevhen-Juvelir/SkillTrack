import React, { useState, useEffect } from "react";
import existingLanguages from "../data/existing-languages";

const Languages = () => {
  const [chosen, setChosen] = useState([]); // { name, slug, level }
  const [editing, setEditing] = useState(null);
  const [user, setUser] = useState("");

  useEffect(() => {
    // Берём email из localStorage (сохранился после логина)
    const savedUser = localStorage.getItem("userEmail");
    if (savedUser) {
      setUser(savedUser);
    }
  }, []);

  const handleSelectLevel = (language, level) => {
    const entry = { name: language.name, slug: language.slug, level };
    setChosen((prev) => {
      const filtered = prev.filter((item) => item.slug !== language.slug);
      return [...filtered, entry];
    });
  };

  const getSelectedLevel = (slug) => {
    const found = chosen.find((item) => item.slug === slug);
    return found ? found.level : null;
  };

  const handleSubmit = async () => {
    const payload = {
      user_email: user,
      languages: chosen,
    };

    try {
      const res = await fetch("http://localhost:8080/api/users/save_languages", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      });

      if (res.ok) {
        alert("Успешно сохранено!");
      } else {
        alert("Ошибка при сохранении");
      }
    } catch (err) {
      console.error("Ошибка при сохранении:", err);
      alert("Ошибка соединения с сервером");
    }
  };

  return (
      <div className="flex items-start justify-center h-screen bg-ui-100 p-10 gap-10">
        {/* Languages grid */}
        <div className="flex flex-wrap gap-4 flex-1 justify-center">
          {existingLanguages.map((language) => {
            const selectedLevel = getSelectedLevel(language.slug);

            return (
                <div key={language.slug} className="w-24 h-32 perspective flip-box">
                  <div className="flip-box-inner bg-accent-100 hover:bg-accent-200 rounded-xl">
                    {/* Front */}
                    <div className="flip-box-front flex flex-col items-center justify-center">
                      <div className="w-12 h-12 bg-white/20 rounded-full flex items-center justify-center mb-2">
                        <img
                            src={language.icon}
                            alt={language.name}
                            className="w-2/3 h-2/3 object-contain"
                        />
                      </div>
                      <div className="text-center text-white bg-black/25 px-2 py-1 rounded">
                        {language.name}
                      </div>
                      {selectedLevel && (
                          <div className="mt-1 text-sm text-green-300">
                            {selectedLevel}
                          </div>
                      )}
                    </div>

                    {/* Back */}
                    <div className="flip-box-back">
                      <div className="flex flex-col justify-center items-center gap-2 h-full">
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
                    </div>
                  </div>
                </div>
            );
          })}
        </div>

        {/* Side tracker */}
        <div className="w-94 bg-accent-100 text-white p-5 rounded-xl shadow-lg flex flex-col gap-4">
          <h2 className="text-lg font-bold">Your Selection</h2>

          {chosen.length === 0 ? (
              <p className="text-sm text-gray-200">No languages selected yet</p>
          ) : (
              <ul className="flex flex-col gap-2">
                {chosen.map((item) => (
                    <li
                        key={item.slug}
                        className="bg-black/25 px-3 py-2 rounded-md flex justify-between items-center relative"
                    >
                      <span>{item.name}</span>

                      <div className="flex gap-4 items-center">
                        {/* Level badge */}
                        <div className="relative">
                          <span
                              className="px-2 py-1 rounded bg-green-600 text-white text-sm cursor-pointer"
                              onClick={() =>
                                  setEditing(editing === item.slug ? null : item.slug)
                              }
                          >
                            {item.level} ▼
                          </span>

                          {/* Popover */}
                          {editing === item.slug && (
                              <div className="absolute right-0 mt-2 flex flex-col bg-white text-black rounded shadow-lg flex flex-col z-10">
                                {["beginner", "intermediate", "advanced"].map((level) => (
                                    <button
                                        key={level}
                                        onClick={() => {
                                          setChosen((prev) =>
                                              prev.map((i) =>
                                                  i.slug === item.slug ? { ...i, level } : i
                                              )
                                          );
                                          setEditing(null);
                                        }}
                                        className="px-3 py-1 text-sm hover:bg-green-400 bg-white hover:text-white rounded"
                                    >
                                      {level}
                                    </button>
                                ))}
                              </div>
                          )}
                        </div>

                        <span
                            className="text-red-500 hover:text-red-700 transition-colors duration-300 cursor-pointer"
                            onClick={() => {
                              setChosen((prev) => prev.filter((i) => i.slug !== item.slug));
                            }}
                        >
                          Delete
                        </span>
                      </div>
                    </li>
                ))}
              </ul>
          )}

          {/* Submit button */}
          <button
              onClick={handleSubmit}
              className="bg-green-600 hover:bg-green-700 px-4 py-2 rounded text-white"
          >
            Submit
          </button>
        </div>
      </div>
  );
};

export default Languages;
