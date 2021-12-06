package com.revature.nova.helpers;

import com.revature.nova.models.User;
import lombok.Getter;
import lombok.Setter;

public class CurrentUser {
    @Getter @Setter
    public static User user;
}
