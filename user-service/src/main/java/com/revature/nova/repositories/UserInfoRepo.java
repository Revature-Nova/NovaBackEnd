package com.revature.nova.repositories;

import com.revature.nova.models.UserInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfoModel, Integer> {
    UserInfoModel findByUsername(String username);
    void deleteByUsername(String username);
}
