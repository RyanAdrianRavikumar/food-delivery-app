package com.foodapp.order_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "restaurant_id")
    private int restaurantId;

    @ElementCollection
    @CollectionTable(name = "order_menu_items", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "menu_item_id")
    private List<Integer> menuItemIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Integer> getMenuItemIds() {
        return menuItemIds;
    }

    public void setMenuItemIds(List<Integer> menuItemIds) {
        this.menuItemIds = menuItemIds;
    }
}
