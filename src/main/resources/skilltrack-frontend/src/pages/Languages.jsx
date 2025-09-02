import React from 'react'
import existingLanguages from '../data/existing-languages'

const Languages = () => {
    return (
        <div className="flex items-center justify-center h-screen bg-ui">
            <div className="flex gap-4 flex-wrap justify-center">
                {existingLanguages.map((language) => (
                    <div
                        key={language.name}
                        className="w-24 bg-accent-100 hover:bg-accent-200 text-white flex flex-col items-center justify-center rounded-xl p-3 shadow-lg transition-all duration-300"
                    >
                        <div className="w-12 h-12 bg-white/20 rounded-full flex items-center justify-center mb-2">
                            <img src={language.icon} alt={language.name} className="w-2/3 h-2/3 object-contain" />
                        </div>

                        <div className="text-center bg-black/25 px-2 py-1 rounded">
                            {language.name}
                        </div>
                    </div>
                ))}
            </div>
            <div>
                
            </div>
        </div>
    );

}

export default Languages