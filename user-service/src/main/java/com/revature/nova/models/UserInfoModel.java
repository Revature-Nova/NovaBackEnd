package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.nova.DTOs.UserRegistrationDTO;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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
@NoArgsConstructor(onConstructor = @__(@Autowired))
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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

    @OneToOne(mappedBy = "userInfoModel", cascade = CascadeType.ALL)
    UserModel userModel;

    @Override
    public String toString() {
        return "User Info {\\n" +
                "  Username: " + username + ",\\n" +
                "  Email: " + email + ",\\n" +
                "  State: " + state + ",\\n" +
                "  Favorite Genre: " + favoriteGenre + ",\\n" +
                "  Profile Message: " + message + ",\\n" +
}
