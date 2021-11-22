package com.revature.nova.DTOs;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * UserRegistrationDTO
 *
 * This DTO stores user registration data
 * Username Regex: Can have any lowercase or capital letter and any number [0-9]
 * Password Regex: Needs at least one capital letter, one number, and one symbol
 *                 Length = 8 to 25 characters
 * Email Regex: Email format 'email@email.com'
 *
 * @author User-Feature Team
 * @date 11/22/2021
 */

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegistrationDTO {
    @Length(min = 5, max = 20)
    @NotNull(message = "Null, a username can not be.")
    @Pattern(message = "Username is not valid for the supreme overlord. Try again.",
            regexp = "^[a-zA-Z0-9]*$")
    private String username;

    @NotNull(message = "*crickets*. There's no password here.")
    @Pattern(message = "Come on. Your password should be strong, like Russian.",
            regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,25}$")
    private String password;

    @Email(message = "This email is not valid >:(")
    private String email;

    @NotNull(message = "")
    private String firstName;

    @NotNull(message = "")
    private String lastName;
}
