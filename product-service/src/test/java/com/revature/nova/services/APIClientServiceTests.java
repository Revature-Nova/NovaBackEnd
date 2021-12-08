package com.revature.nova.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class contains tests for our API-consumption service layer methods.
 * @author Andrew Petersen
 * @date 12/05/2021
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class APIClientServiceTests {

    /**
     * This test verifies that the method can pull from the Google url.
     */
    @Test
    public void getTest_fromGoogle(){
        String url = "http://www.google.com";

        String response = APIClientService.get(url);

        // response should be the html for google.com, so begins with "<!doctype"
        assertEquals(response.substring(0,9),"<!doctype");
    }

    /**
     * This test verifies that the method works with the used API.
     */
//     @Test
//     public void getTest_worksWithAPI() {
//         /*
//          * API endpoint for "The Legend of Zelda: Skyward Sword"
//          */
//         String url = "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9";

//         String response = APIClientService.get(url);

//         try{
//             JSONObject game = new JSONObject(response);
//             String title = game.getString("name");

//             assertEquals(title,"The Legend of Zelda: Skyward Sword");
//         }catch (JSONException e){
//             e.printStackTrace();
//         }
//     }

    /**
     * This test shows that the method will throw an exception with a bad url.
     */
    @Test
    public void getTest_badUrl(){
        String url = "Bad Url";
        assertThrows(IllegalArgumentException.class,()->APIClientService.get(url));
    }
}
