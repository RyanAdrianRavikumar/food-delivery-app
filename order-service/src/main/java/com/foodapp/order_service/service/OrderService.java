package com.foodapp.order_service.service;

import com.foodapp.order_service.entity.Order;
import com.foodapp.order_service.entity.OrderMenuItem;
import com.foodapp.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate; // Make http requests to other microservices

    // URLs to other microservices for validation and email notification
    private final String USER_SERVICE_URL = "http://localhost:8080/users/";
    private final String RESTAURANT_SERVICE_URL = "http://localhost:8081/restaurants/";
    private final String MENU_SERVICE_URL = "http://localhost:8082/menu/";
    private final String EMAIL_SERVICE_URL = "http://localhost:8086/email/sendOrderConfirmation/";
    private final String DISCOUNT_SERVICE_URL = "http://localhost:8085/discounts/";

    public Order placeOrder(Order order) {
        // Validate user
        String userUrl = USER_SERVICE_URL + order.getUserId();
        ResponseEntity<Object> userResponse = restTemplate.getForEntity(userUrl, Object.class);

        if (!userResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("User not found");
        }

        // Validate restaurant
        String restaurantUrl = RESTAURANT_SERVICE_URL + order.getRestaurantId();
        ResponseEntity<Object> restaurantResponse = restTemplate.getForEntity(restaurantUrl, Object.class);

        if (!restaurantResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Restaurant not found");
        }

        // Set initial bill amount to 0
        double totalAmount = 0;

        // Set order reference for each menu item and validate qty
        for (OrderMenuItem menuItem : order.getMenuItems()) {
            menuItem.setOrder(order); // Set the order reference

            // Validate menu item
            String menuUrl = MENU_SERVICE_URL + menuItem.getMenuItemId();
            ResponseEntity<Object> menuResponse = restTemplate.getForEntity(menuUrl, Object.class);

            if (!menuResponse.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Menu item not found: " + menuItem.getMenuItemId());
            }

            // Validate and log qty
            if (menuItem.getQty() <= 0) {
                throw new RuntimeException("Invalid quantity for menu item ID: " + menuItem.getMenuItemId());
            }
            System.out.println("MenuItem ID: " + menuItem.getMenuItemId() + ", Qty: " + menuItem.getQty());

            // Add menu item price * quantity to the bill amount
            double menuItemPrice = extractPriceFromResponse(menuResponse);
            totalAmount += menuItemPrice * menuItem.getQty(); // Multiply by qty
        }

        // Apply Discount
        if (order.getDiscountId() != null) { // Check if discount ID is provided
            String discountUrl = DISCOUNT_SERVICE_URL + order.getDiscountId();
            ResponseEntity<Map> discountResponse = restTemplate.getForEntity(discountUrl, Map.class);

            if (discountResponse.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> discountBody = discountResponse.getBody();
                if (discountBody != null && discountBody.containsKey("discountValue")) {
                    double discountPercentage = (double) discountBody.get("discountValue"); // Extract discountValue
                    totalAmount -= totalAmount * (discountPercentage / 100); // Apply the discount
                }
            } else {
                throw new RuntimeException("Discount not found for ID: " + order.getDiscountId());
            }
        }

        // Set the totalAmount to the order
        order.setTotalAmount(totalAmount);

        // Save the order
        Order savedOrder = orderRepository.save(order);

        // Send the order confirmation email
        String emailUrl = EMAIL_SERVICE_URL + savedOrder.getUserId() + "?totalAmount=" + savedOrder.getTotalAmount();
        ResponseEntity<String> response = restTemplate.postForEntity(emailUrl, null, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to send order confirmation email");
        }

        return savedOrder;
    }


    public Order getOrderById(int orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new RuntimeException("Order not found");
        }

        return orderOptional.get(); // Returning the Order entity as it is
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public double extractPriceFromResponse(ResponseEntity<Object> menuResponse){
        // Convert the response body to a Map to directly access the price field
        Map<String, Object> responseBody = (Map<String, Object>) menuResponse.getBody();

        // Get the price from the map (assuming the price field exists in the response)
        if (responseBody != null && responseBody.containsKey("price")) {
            return (Double) responseBody.get("price");
        }

        return 0.0;
    }

    public List<Order> findOrderByUserId(int userId){
        return orderRepository.findOrderByUserId(userId);
    }
}
