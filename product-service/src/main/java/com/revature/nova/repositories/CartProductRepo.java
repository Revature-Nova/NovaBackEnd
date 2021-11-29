package com.revature.nova.repositories;

import com.revature.nova.models.Cart;
import com.revature.nova.models.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepo extends JpaRepository<CartProduct, Cart> {
}
