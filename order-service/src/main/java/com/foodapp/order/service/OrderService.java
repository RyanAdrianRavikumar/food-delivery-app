package com.foodapp.order.service;

import com.foodapp.order.entity.Order;
import com.foodapp.order.entity.OrderMenuItem;
import com.foodapp.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    private final String DELIVERY_SERVICE_URL = "http://localhost:8084/drivers/";

    public Order placeOrder(Order order) {
        // Validate user
        String userUrl = USER_SERVICE_URL + order.getUserId();
        // Get user details wrapped into a response object
        ResponseEntity<Object> userResponse = restTemplate.getForEntity(userUrl, Object.class);

        if (!userResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("User not found");
        }

        // Validate restaurant
        String restaurantUrl = RESTAURANT_SERVICE_URL + order.getRestaurantId();
        // Get restaurant details wrapped into a response object
        ResponseEntity<Object> restaurantResponse = restTemplate.getForEntity(restaurantUrl, Object.class);

        if (!restaurantResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Restaurant not found");
        }

        // Set initial bill amount to 0
        double totalAmount = 0;

        for (int i = 0; i < order.getMenuItems().size(); i++) {
            OrderMenuItem menuItem = order.getMenuItems().get(i);
            menuItem.setOrder(order);  // Set the order reference

            // Validate each menu item
            String menuUrl = MENU_SERVICE_URL + menuItem.getMenuItemId();
            ResponseEntity<Object> menuResponse = restTemplate.getForEntity(menuUrl, Object.class);

            if (!menuResponse.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Menu item not found: " + menuItem.getMenuItemId());
            }

            totalAmount += extractPriceFromResponse(menuResponse); // Add price to total
        }

        // Set the totalAmount to the order
        order.setTotalAmount(totalAmount);

        // Save the order (cascade will save associated order menu items if configured)
        Order savedOrder = orderRepository.save(order);

        // Send the order confirmation email using emailNotification microservice
        String emailUrl = EMAIL_SERVICE_URL + savedOrder.getUserId();
        restTemplate.postForObject(emailUrl, null, String.class);

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
}
