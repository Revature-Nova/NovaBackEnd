package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * POJO used to store a User's sensitive information
 *
 * @date 11/22/2021
 * @author User-Feature Team, Erika Johnson
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "userModel"},
        ignoreUnknown = true)
@Getter @Setter
@NoArgsConstructor(onConstructor = @__(@Autowired))
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoModel implements Serializable {
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

    private UserModel userModel;

    @Override
    public String toString() {
        return "{\n" +
                "  \"Username\": " + username + ",\n" +
                "  \"Email\": " + email + ",\n" +
                "  \"State\": " + state + ",\n" +
                "  \"Favorite Genre\": " + favoriteGenre + ",\n" +
                "  \"Profile Message\": " + message + "\n" +
                "}";
    }
}
