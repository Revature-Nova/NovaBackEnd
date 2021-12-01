package com.revature.nova.repositories;

import com.revature.nova.DTOs.UserProfileDTO;
import com.revature.nova.models.UserInfoModel;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfoModel, Integer> {
    UserInfoModel findByUsername(String username);
    void deleteByUsername(String username);

    @Query("select username, email, state, favoriteGenre, message from UserInfoModel")
    ArrayList<String> getAllWithoutPassword();
}
