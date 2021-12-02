package com.revature.nova.rawg;

import com.revature.nova.models.Product;
import com.revature.nova.services.APIClientService;
import org.json.JSONObject;

/**
 * ProductController
 * @author Michael Reece
 * @date 11/24/21
 * Handles 3rd party API interactions to get descriptions for game products
 */
public class DescriptionInfo {
    /**
     * Reach out to our third party API to get description for a game
     * @param product to search for
     * @return description for the specified object as string
     */
    public static String getDescription(Product product)
    {
        //get the url out of the product
        String url = product.getEndpoint();

        //json response containing all information about this product from rawg
        String jsonResponse = APIClientService.get(url);

        //convert jsonResponse into a json object so we can pull key value pairs out of it
        JSONObject object = new JSONObject(jsonResponse);

        //return the description out of the product object
        return object.getString("description");
    }
}
