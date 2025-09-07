import React from 'react'
import Button from './Button'

const Moto = () => {
  return (
    <section className="flex flex-col main-section align-center gap-12 max-w-4xl ">
        <div className="text-6xl font-bold">
            Track your <br /> coding progress
        </div>
        <div className="text-base max-w-[350px]">
            Improve your programming skills and achieve your goals with Skill Track's comprehensive progress tracking.
        </div>
        <Button />
    </section>
  )
}

export default Moto