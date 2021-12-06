package com.revature.nova.helpers;

import com.revature.nova.models.Cart;
import com.revature.nova.models.UserInfoModel;
import lombok.Getter;
import lombok.Setter;

/**
 * Helper class with static variables to keep session data
 *
 * cart: Session cart set on new cart request
 * user: Session user is set on login
 *
 * @date 12/5/2021
 * @author Kollier Martin
 */
public class CurrentUser {
    @Getter @Setter
    public static UserInfoModel user;

    @Getter @Setter
    public static Cart cart;
}
