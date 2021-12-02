package com.revature.nova.DTOs;

import com.revature.nova.models.Product;
import com.revature.nova.models.UserModel;
import lombok.*;

/**
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
public class CartDataDTO {
    private UserModel user;
    private Product product;
}
