package com.revature.nova.repositories;

import com.revature.nova.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    /**
     * This method finds the rows in the database by title containing (ignore case)
     *
     * @param name to search by, as string
     * @return Returns a list of the relevant games
     */
    List<Product> findByTitleContainingIgnoreCase(String name);

    /**
     * This method finds the rows in the database by the specified genre
     *
     * @param genre Requires the genre that the user wants to filter by
     * @return Returns a list of the filtered games
     */
    List<Product> findByGenreIgnoreCase(String genre);

    /**
     * This method finds the rows in the database by the specified rating
     *
     * @param rating Requires the rating that the user wants to filter by
     * @return Returns a list of the filtered games
     */
    List<Product> findByRatingIgnoreCase(String rating);

    /**
     * This method finds the rows in the database by the specified platform
     *
     * @param platform Requires the platform that the user wants to filter by
     * @return Returns a list of the filtered games
     */
    List<Product> findByPlatformIgnoreCase(String platform);

    /**
     * This method finds the games that are between the given price range.
     *
     * @param rangeMin This is the lower end of the range
     * @param rangeMax This is the higher end of the range
     * @return Returns a list of the games with prices within the given range
     */
    List<Product> findByPriceIsBetween(float rangeMin, float rangeMax);
}
