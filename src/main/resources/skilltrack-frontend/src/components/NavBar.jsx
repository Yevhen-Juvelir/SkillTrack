import React from 'react'
import { useState } from "react"
import { useNavigate } from "react-router-dom";

const NavBar = () => {
    const [menuOpen, setMenuOpen] = useState(false);
    let userEmail = localStorage.getItem("userEmail");
    const navigate = useNavigate();

    return (
        <header className="main-section">
            <nav className="flex flex-row justify-between text-3xl py-15">
                <div>
                    SkillTrack
                </div>
                <div className="relative group inline-block">
                    {userEmail ? (
                        <li className="cursor-pointer list-none">
                            <img src="./icons/user.png" alt="user" className="w-10 h-10" />
                        </li>
                    ) : null}

                    {/* Dropdown menu */}
                    <ul
                        className="
          absolute right-0 mt-2 w-40 bg-white border rounded shadow-lg 
          opacity-0 invisible group-hover:opacity-100 group-hover:visible
          transition-opacity duration-200
        "
                    >
                        <li
                            className="px-4 py-2 hover:bg-gray-100 cursor-pointer"
                            onClick={() => navigate("/profile")}
                        >
                            Profile
                        </li>
                        <li
                            className="px-4 py-2 hover:bg-gray-100 cursor-pointer"
                            onClick={() => {
                                localStorage.removeItem("authToken");
                                navigate("/");
                            }}
                        >
                            Logout
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
    )
}

export default NavBar