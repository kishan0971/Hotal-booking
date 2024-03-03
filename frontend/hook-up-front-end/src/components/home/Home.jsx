import React from 'react'
import MainHeader from '../layout/MainHeader'
import HotelServices from '../common/HotelServices'
import Parallax from '../common/Parallax'
import RoomCarousel from '../common/RoomCarousel'

const Home = () => {
  return (
    <section>
     <MainHeader/>

     <div className="container">
      <RoomCarousel />
      <Parallax />
        <RoomCarousel />
      <HotelServices />
      {/* <Parallax /> */}
      {/* <RoomCarousel /> */}
     </div>
    </section>
  )
}

export default Home