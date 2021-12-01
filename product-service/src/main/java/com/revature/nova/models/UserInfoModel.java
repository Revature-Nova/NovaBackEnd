package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

/**
 * POJO used to store a User's sensitive information
 *
 * @date 11/22/2021
 * @author User-Feature Team, Erika Johnson
 */

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "user"},
        ignoreUnknown = true)
@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class UserInfoModel implements Serializable {
    private int userInfoID;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String email;

    private String state;

    private String favoriteGenre;

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
