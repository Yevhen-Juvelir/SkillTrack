import React from 'react'

const NavBar = () => {
  return (
    <header>
        <nav className="flex flex-row justify-between px-40 text-3xl py-15">
            <div>
                SkillTrack
            </div>
            <ul className="flex flex-row justify-between gap-16">
                <li>Features</li>
                <li>Pricing</li>
                <li>Contact</li>
            </ul>
        </nav>
    </header>
  )
}

export default NavBar