import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const RestaurantList = () => {
  const [restaurants, setRestaurants] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetch("http://192.168.1.18:8081/restaurants/all")
      .then((response) => response.json())
      .then((data) => setRestaurants(data))
      .catch((error) => console.error("Error fetching restaurants:", error));
  }, []);

  const handleSelectRestaurant = (restaurantId) => {
    localStorage.setItem("selectedRestaurantId", restaurantId);
    navigate(`/menu/${restaurantId}`);
  };

  return (
    <div>
      <h2>Select a Restaurant</h2>
      <ul>
        {restaurants.map((restaurant) => (
          <li
            key={restaurant.id}
            onClick={() => handleSelectRestaurant(restaurant.id)}
            style={{ cursor: "pointer", color: "blue" }}
          >
            {restaurant.name} - {restaurant.status.toUpperCase()}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default RestaurantList;
