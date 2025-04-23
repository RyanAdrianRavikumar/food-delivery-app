import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

const MenuItems = () => {
    const { restaurantId } = useParams(); // Get restaurantId from URL params
    const userId = localStorage.getItem("userId"); // Assuming userId is saved in localStorage

    const [menuItems, setMenuItems] = useState([]);
    const [selectedItems, setSelectedItems] = useState([]);
    const [orderPlaced, setOrderPlaced] = useState(false);

    // Fetch the menu once the restaurantId is available
    useEffect(() => {
        if (!restaurantId) {
            console.error("Restaurant ID is missing!");
            return;
        }

        fetch(`http://192.168.1.18:8082/restaurants/${restaurantId}/menu`)
            .then(response => {
                console.log("Fetching menu, Response status:", response.status); // Debugging
                return response.json();
            })
            .then(data => {
                console.log("Fetched menu data:", data); // Debugging
                setMenuItems(data);
            })
            .catch(error => {
                console.error("Error fetching menu:", error);
            });
    }, [restaurantId]);

    // Toggle selection of menu item
    const handleSelectItem = (itemId) => {
        console.log("Selected item:", itemId); // Debugging
        setSelectedItems(prevSelected =>
            prevSelected.includes(itemId)
                ? prevSelected.filter(id => id !== itemId) // Deselect
                : [...prevSelected, itemId] // Select
        );
    };

    // Validate before placing the order
    const handlePlaceOrder = () => {
        console.log("Selected items for order:", selectedItems); // Debugging
        if (selectedItems.length === 0) {
            alert("Please select at least one item before placing the order.");
            return;
        }
        setOrderPlaced(true); // Allow order confirmation
    };

    // Confirm order (send to Order Microservice)
    const handleConfirmOrder = () => {
        if (!userId || !restaurantId) {
            alert("User or Restaurant ID is missing.");
            return;
        }

        const orderData = {
            userId,
            restaurantId,
            menuItemIds: selectedItems,
        };

        console.log("Order Data being sent:", orderData); // Debugging

        fetch("http://192.168.1.18:8083/orders/place", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(orderData),
        })
            .then(response => {
                console.log("Response status:", response.status); // Debugging
                return response.json();
            })
            .then(data => {
                console.log("Order response:", data); // Debugging
                if (data.status === 400) {
                    alert("Error: Bad Request. Please check the order details.");
                } else {
                    alert("Order placed successfully!");
                    setSelectedItems([]); // Clear selection after order
                    setOrderPlaced(false);
                }
            })
            .catch(error => {
                console.error("Error placing order:", error);
                alert("Something went wrong. Please try again.");
            });
    };

    return (
        <div>
            <h2>Menu</h2>
            {menuItems.length === 0 ? (
                <p>Loading menu...</p>
            ) : (
                <ul>
                    {menuItems.map(item => (
                        <li key={item.id} style={{ marginBottom: "10px" }}>
                            <input
                                type="checkbox"
                                checked={selectedItems.includes(item.id)}
                                onChange={() => handleSelectItem(item.id)}
                            />
                            {item.name} - ${item.price}
                        </li>
                    ))}
                </ul>
            )}

            <button onClick={handlePlaceOrder} disabled={orderPlaced || selectedItems.length === 0}>
                Place Order
            </button>

            {orderPlaced && (
                <div>
                    <p>Confirm your order?</p>
                    <button onClick={handleConfirmOrder}>Confirm</button>
                    <button onClick={() => setOrderPlaced(false)}>Cancel</button>
                </div>
            )}
        </div>
    );
};

export default MenuItems;
