import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Login";
import Registration from "./Registration";
import RestaurantList from "./RestaurantList";
import MenuItems from "./MenuItems";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Registration />} />
        <Route path="/restaurants" element={<RestaurantList />} />
        <Route path="/menu/:restaurantId" element={<MenuItems />} />  {/* Updated to restaurantId */}
      </Routes>
    </Router>
  );
}

export default App;
