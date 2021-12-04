package com.revature.nova.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseLogin {
    private Integer id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;

}
