package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
        return "{\n" +
                "  \"Username\": " + username + ",\n" +
                "  \"Email\": " + email + ",\n" +
                "  \"State\": " + state + ",\n" +
                "  \"Favorite Genre\": " + favoriteGenre + ",\n" +
                "  \"Profile Message\": " + message + "\n" +
                "}";
    }
}
