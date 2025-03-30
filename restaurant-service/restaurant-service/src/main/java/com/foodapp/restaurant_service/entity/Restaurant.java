package com.foodapp.restaurant_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "status")
    private String status;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
