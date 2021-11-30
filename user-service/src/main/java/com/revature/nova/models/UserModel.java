package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.nova.DTOs.UserRegistrationDTO;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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
@NoArgsConstructor(onConstructor = @__(@Autowired))
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserModel implements Serializable {
    public UserModel(UserRegistrationDTO regData) {
        this.firstName = regData.getFirstName();
        this.lastName = regData.getLastName();
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    UserInfoModel userInfoModel;

    @Override
    public String toString() {
        return "User {\\n" +
                "  First Name: " + firstName + ",\\n" +
                "  Last Name: " + lastName + ",\\n" +
                "  Info: " + userInfoModel + ",\\n" +
                '}';
    }

}
