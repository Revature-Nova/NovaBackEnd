package com.revature.nova.DTOs;

import lombok.*;

/**
 * LoginCredentialsDTO
 *
 * This DTO stores user login data
 *
 * @author User-Feature Team
 * @date 11/22/2021
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginCredentialsDTO {
    private String username;
    private String password;
}
