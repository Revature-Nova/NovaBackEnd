package com.revature.nova.rawg;

import com.revature.nova.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import static com.revature.nova.rawg.DescriptionInfo.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for our API-consumption service layer methods.
 * @author Andrew Petersen
 * @date 12/05/2021
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DescriptionInfoTests {

    /**
     * This test verifies that the same game on different platforms returns the same description.
     */
    @Test
    public void getDescriptionTest_sameGames_sameDescriptions(){
        Product product1 = new Product(121, "The Legend of Zelda: Skyward Sword", "Adventure", 19.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Wii", "https://imgur.com/VvU45oV");
        Product product2 = new Product(123, "The Legend of Zelda: Skyward Sword", "Adventure", 39.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Nintendo Switch", "https://imgur.com/VvU45oV");

        String desc1 = getDescription(product1);
        String desc2 = getDescription(product2);

        System.out.println(desc1);

        assertEquals(desc1,desc2);
    }

    /**
     * This test verifies that the returned description matches the description pulled from online.
     */
    @Test
    public void descriptionMatchesOnline(){
        Product product1 = new Product(121, "The Legend of Zelda: Skyward Sword", "Adventure", 19.99f, "E10+", "https://rawg.io/api/games/the-legend-of-zelda-skyward-sword?key=87ad23cdc737468884eb0216a7ba8df9", "Wii", "https://imgur.com/VvU45oV");
        String desc1 = getDescription(product1);

        // taken directly from the middle of the API description
        String partial = "The Legend of Zelda: Skyward Sword boasts the most realistic";
        String partial2 = "A Wii Remote Plus™ controller or Wii MotionPlus™ accessory is required";
        String partial3 = "<p>*Note that the gold controller will simply be gold in color and will not<br />\n" +
                "contain any actual precious metals. Sorry.</p>";

        assertTrue(desc1.contains(partial));
        assertTrue(desc1.contains(partial2));
        assertTrue(desc1.contains(partial3));
    }
}
