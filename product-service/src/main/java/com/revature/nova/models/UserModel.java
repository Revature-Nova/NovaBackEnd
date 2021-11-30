package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
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
    private UserInfoModel userInfoModel;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @Override
    public String toString() {
        return "{\n" +
                "  \"First Name\" : " + firstName + ",\n" +
                "  \"Last Name\" : " + lastName + ",\n" +
                "  \"Username\": " + userInfoModel.getUsername() + "\n" +
                '}';
    }
}
