package com.revature.nova.DTOs;

import com.revature.nova.models.UserInfoModel;
import lombok.*;

/**
 * @author Erika Johnson
 * @date 11/23/2021
 * Receive user information for profile page, will be tied to
 * a User through UserInfo
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfileDTO {
    private String username;
    private String email;
    private String state;
    private String favorite_genre;
    private String message;

    public UserProfileDTO(UserInfoModel userInfoModel, String state, String favorite_genre, String message) {
        this.username = userInfoModel.getUsername();
        this.email = userInfoModel.getEmail();
        this.state = state;
        this.favorite_genre = favorite_genre;
        this.message = message;
    }
}
