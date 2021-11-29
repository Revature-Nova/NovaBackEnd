package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.nova.DTOs.UserRegistrationDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * POJO used to store a User's sensitive information
 *
 * @date 11/22/2021
 * @author User-Feature Team, Erika Johnson
 */
@Entity
@Table(name = "user_info")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "user"},
        ignoreUnknown = true)
@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class UserInfoModel implements Serializable {
    public UserInfoModel(UserRegistrationDTO regData){
        this.username = regData.getUsername();
        this.password = regData.getPassword();
        this.email = regData.getEmail();
    }

    @Id
    @Column(name = "userinfo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userInfoID;

    @NonNull
    @Column(name = "username", unique = true)
    private String username;

    @NonNull
    @Column(name = "password")
    private String password;

    @NonNull
    @Column
    private String email;

    @Column
    private String state;

    @Column
    private String favoriteGenre;

    @Column
    private String message;

    @OneToOne(mappedBy = "userInfoModel")
    UserModel userModel;

    @Override
    public String toString() {
        return "User Info {\\n" +
                "  Username: " + username + ",\\n" +
                "  Email: " + email + ",\\n" +
                "  State: " + state + ",\\n" +
                "  Favorite Genre: " + favoriteGenre + ",\\n" +
                "  Profile Message: " + message + ",\\n" +
                "  User: " + userModel.getLastName() + ", " + userModel.getFirstName() + ",\\n" +
                '}';
    }
}
