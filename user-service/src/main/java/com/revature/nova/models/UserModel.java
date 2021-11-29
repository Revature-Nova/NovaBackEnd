package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.nova.DTOs.UserRegistrationDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * POJO used to store a User's public information
 *
 * @date 11/22/2021
 * @author User-Feature Team
 */
@Entity
@Table(name = "user_model")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "userInfoModel"},
        ignoreUnknown = true)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements Serializable {
    public UserModel(UserRegistrationDTO regData) {
        this.firstName = regData.getFirstName();
        this.lastName = regData.getLastName();
    }

    public UserModel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    UserInfoModel userInfoModel;

    @Override
    public String toString() {
<<<<<<< Updated upstream
        return "UserModel {\\n" +
                "userID: " + userID + ",\\n" +
                "firstName: " + firstName + ",\\n" +
                "lastName: " + lastName + ",\\n" +
                "user info: " + userInfoModel + ",\\n" +
=======
        return "User {\n" +
                "  First Name: " + firstName + ",\n" +
                "  Last Name: " + lastName + ",\n" +
                "  Info: " + userInfoModel + ",\n" +
>>>>>>> Stashed changes
                '}';
    }
}
