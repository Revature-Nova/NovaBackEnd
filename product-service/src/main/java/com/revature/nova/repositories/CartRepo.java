package com.revature.nova.repositories;

import com.revature.nova.models.Cart;
import com.revature.nova.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Integer> {
}
