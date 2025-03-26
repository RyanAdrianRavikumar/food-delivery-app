package com.foodapp.order_service.service;

import com.foodapp.order_service.entity.Order;
import com.foodapp.order_service.entity.OrderMenuItem;
import com.foodapp.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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

        // Set order reference for each menu item
        for (OrderMenuItem menuItem : order.getMenuItems()) {
            menuItem.setOrder(order);  // Ensure the order reference is set for each menu item

            // Validate menu item
            String menuUrl = MENU_SERVICE_URL + menuItem.getMenuItemId();
            // Get each menu item details wrapped into a response object
            ResponseEntity<Object> menuResponse = restTemplate.getForEntity(menuUrl, Object.class);

            if (!menuResponse.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Menu item not found: " + menuItem.getMenuItemId());
            }
        }

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
}
