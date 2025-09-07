import React from 'react'
import { useState } from "react"
import { useNavigate } from "react-router-dom";

const NavBar = () => {
    const [menuOpen, setMenuOpen] = useState(false);
    let userEmail = localStorage.getItem("userEmail");
    const navigate = useNavigate();

    return (
        <header className="main-section">
            <nav className="flex flex-row justify-between text-3xl py-15" onMouseLeave={() => setMenuOpen(false)}>
                <div>SkillTrack
                </div>
                <div className="relative group inline-block">
                    {userEmail ? (
                        <li className="cursor-pointer list-none" onMouseEnter={() => setMenuOpen(true)}>
                            <img src="./icons/user.png" alt="user" className="w-10 h-10" />
                        </li>
                    ) : null}

                    {menuOpen && (
                        <ul className="absolute right-0 w-40 bg-ui-200 border rounded text-sm overflow-hidden">
                            <li
                                className="px-4 py-2 menu-list-item cursor-pointer"
                                onClick={() => navigate("/profile")}
                            >
                                Profile
                            </li>
                            <li
                                className="px-4 py-2 menu-list-item cursor-pointer"
                                onClick={() => {
                                    localStorage.removeItem("userEmail");
                                    navigate("/");
                                }}
                            >
                                Logout
                            </li>
                        </ul>
                    )}
                </div>
            </nav>
        </header>
    )
}

export default NavBar