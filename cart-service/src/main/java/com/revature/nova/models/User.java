package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity @Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cart"})
@Getter @Setter
@NoArgsConstructor(onConstructor = @__(@Autowired))
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userInfoID;

    @NonNull
    @Column(name = "username", unique = true)
    private String username;

    @Transient
    private String password;

    @Transient
    private String email;

    @Transient
    private String state;

    @Transient
    private String favoriteGenre;

    @Transient
    private String message;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;
}
