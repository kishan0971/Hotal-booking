// // ============================working old code=============================================

// import React from 'react'
// import AddRoom from './components/room/AddRoom'
// import "bootstrap/dist/css/bootstrap.min.css";
// import Home from './components/home/Home';

// const App = () => {
//   return (
//     <div>
//       <AddRoom></AddRoom>
     
//     </div>
//   )
// }

// export default App


// ============================================================================================

import React from 'react'
import AddRoom from './components/room/AddRoom'
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router,Routes,Route } from 'react-router-dom';
import Home from './components/home/Home';
import EditRoom from './components/room/EditRoom';
import ExistingRooms from './components/room/ExistingRooms';
import NavBar from './components/layout/NavBar';
import Footer from './components/layout/Footer';
import RoomListing from './components/room/RoomListing';
import Admin from './components/admin/Admin';
import Checkout from './components/booking/Checkout';
import BookingSuccess from './components/booking/BookingSuccess';


const App = () => {
  return (
    <>

    <main>
      <Router>
        <NavBar />
        <Routes>
          <Route path='/' element={<Home/>}/>
          <Route path='/edit-room/:roomId' element={<EditRoom/>}/>
          <Route path='/existing-rooms' element={<ExistingRooms/>}/> 
          <Route path='/add-room' element={<AddRoom/>}/>
          <Route path='/book-room/:roomId' element={<Checkout/>}/>
          <Route path='/browse-all-rooms' element={<RoomListing/>}/>
          <Route path='/admin' element={<Admin/>}/> 
          <Route path='/booking-success' element={<BookingSuccess/>}/>
        </Routes>
      </Router>
      <Footer />
    </main>
      
    </>
  )
}

export default App