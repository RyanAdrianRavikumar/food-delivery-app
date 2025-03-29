package com.foodapp.menu_service.entity;

import jakarta.persistence.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

@Entity
public class MenuItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "restaurant_id")
    private int restaurantId;

    @Lob
    @Column(name = "food_image", columnDefinition = "LONGBLOB")
    private Blob picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getPicture() {
        if (picture != null) {
            try {
                int blobLength = (int) picture.length();
                byte[] blobAsBytes = picture.getBytes(1, blobLength);
                return Base64.getEncoder().encodeToString(blobAsBytes);
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
                return null; // Or throw an exception
            }
        }
        return null;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }
}
