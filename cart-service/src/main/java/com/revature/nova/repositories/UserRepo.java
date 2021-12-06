package com.revature.nova.repositories;

import com.revature.nova.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User getByUsername(String username);
}
