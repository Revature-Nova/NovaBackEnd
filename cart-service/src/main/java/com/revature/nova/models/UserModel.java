package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

/**
 * POJO used to store a User's public information
 *
 * @date 11/22/2021
 * @author User-Feature Team
 */

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "userInfoModel"},
        ignoreUnknown = true)
@Getter @Setter
@NoArgsConstructor(onConstructor = @__(@Autowired))
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserModel implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    private String firstName;

    @NonNull
    private String lastName;

    private UserInfoModel userInfoModel;

    @Override
    public String toString() {
        return "{\n" +
                "  \"First Name\" : " + firstName + ",\n" +
                "  \"Last Name\" : " + lastName + ",\n" +
                "  \"Username\": " + userInfoModel.getUsername() + "\n" +
                '}';
    }

}
