package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name = "email_id")
    private String email;

    @OneToOne(mappedBy = "userInfoModel")
    UserModel userModel;
}
