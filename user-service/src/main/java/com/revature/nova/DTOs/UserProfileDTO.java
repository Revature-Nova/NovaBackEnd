package com.revature.nova.DTOs;

import lombok.*;

/**
 * Receive user information for profile page, will be tied to
 * a User through UserInfo
 *
 * @author Erika Johnson
 * @date 11/23/2021
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfileDTO {
    @NonNull
    private String username;
    private String email;
    private String state;
    private String favoriteGenre;
    private String message;
}
