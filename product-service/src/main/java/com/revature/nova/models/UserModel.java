package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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
@NoArgsConstructor
@RequiredArgsConstructor
public class UserModel implements Serializable {
    private int userID;
    private UserInfoModel userInfoModel;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @Override
    public String toString() {
        return "{\n" +
                "  \"First Name\" : " + firstName + ",\n" +
                "  \"Last Name\" : " + lastName + ",\n" +
                "  \"Username\": " + userInfoModel.getUsername() + "\n" +
                '}';
    }
}
