package com.revature.nova.repositories;

import com.revature.nova.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {
    UserModel findByFirstName(String firstName);
    void deleteByFirstName(String firstName);
}
