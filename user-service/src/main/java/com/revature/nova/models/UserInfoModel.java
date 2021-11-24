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

    public UserInfoModel(@NonNull String username, @NonNull String email, String state, String favoriteGenre, String message) {
        this.username = username;
        this.email = email;
        this.state = state;
        this.favoriteGenre = favoriteGenre;
        this.message = message;
    }

    @Id
    @Column(name = "userinfo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userInfoID;

    @Column(name = "username", unique = true)
    @NonNull
    private String username;

    @Column(name = "password")
    @NonNull
    private String password;

    @Column
    @NonNull
    private String email;

    @Column
    private String state;

    @Column
    private String favoriteGenre;

    @Column
    private String message;

    @OneToOne(mappedBy = "userInfoModel", cascade = CascadeType.ALL)
    UserModel userModel;

    @Override
    public String toString() {
        return "UserInfoModel {\\n" +
                "userInfoID: " + userInfoID + ",\\n" +
                "username: " + username + ",\\n" +
                "password: " + password + ",\\n" +
                "email: " + email + ",\\n" +
                "state: " + state + ",\\n" +
                "favorite genre: " + favoriteGenre + ",\\n" +
                "message: " + message + ",\\n" +
                "user: " + userModel.getLastName() + ", " + userModel.getFirstName() + ",\\n" +
                '}';
    }
}
