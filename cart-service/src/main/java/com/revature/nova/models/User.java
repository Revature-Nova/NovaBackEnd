package com.revature.nova.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity @Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "productList"})
@Getter @Setter
@NoArgsConstructor(onConstructor = @__(@Autowired))
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;

    @Column
    private String username;

    @Transient
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;
}
