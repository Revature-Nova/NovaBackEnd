package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.nova.DTOs.UserProfileDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Erika Johnson
 * Added 3 fields to model ( state, favorite_genre, message) and custom args constructor
 */

@Entity
@Table(name = "user_info")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "user"},
        ignoreUnknown = true)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoModel implements Serializable {

    @Id
    @Column(name = "userinfo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userInfoID;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column
    private String email;

    @Column
    private String state;

    @Column
    private String favorite_genre;

    @Column
    private String message;

    public UserInfoModel(String username, String email, String state, String favorite_genre, String message) {
        this.username = username;
        this.email = email;
        this.state = state;
        this.favorite_genre = favorite_genre;
        this.message = message;
    }

    @OneToOne(mappedBy = "userInfoModel")
    UserModel userModel;


}
