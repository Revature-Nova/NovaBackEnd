package com.revature.nova.DTOs;

import com.revature.nova.models.UserInfoModel;
import com.revature.nova.models.UserModel;
import lombok.*;

/**
 * This DTO is used to send persisted registration data back on registration
 *
 * @author Kollier Martin
 * @date 11/23/2021
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisteredDataDTO {
    public RegisteredDataDTO(UserModel userModel, UserInfoModel userInfoModel) {
        this.status = "Successfully Registered!";
        this.firstName = userModel.getFirstName();
        this.lastName = userModel.getLastName();
        this.username = userInfoModel.getUsername();
        this.email = userInfoModel.getEmail();
    }

    private String status;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
