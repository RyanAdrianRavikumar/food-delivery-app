package com.foodapp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodapp.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
