package com.revature.nova.services;

import com.revature.nova.models.Logger;
import com.revature.nova.repositories.LoggerRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * This class contains tests for our Logging service layer methods.
 * @author Andrew Petersen
 * @date 12/05/2021
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoggerServiceTests {

    private final LoggerService loggerService;
    private final LoggerRepo repo;

    @Autowired
    public LoggerServiceTests(LoggerService loggerService, LoggerRepo repo){
        this.loggerService=loggerService;
        this.repo=repo;
    }

    /**
     * This test checks if the method returns the current date.
     * (Change the day this test checks against based on the current date)
     */
    @Test
    public void loggerServiceGetDateTime(){
        String dateTime = loggerService.getCurrentDateTime();
        String year = dateTime.substring(0,4);
        String month = dateTime.substring(5,7);
        String day = dateTime.substring(8,10);
        String fullDate = dateTime.substring(0,10);

        assertEquals(year,"2021");
        assertEquals(month,"12");
//         assertEquals(day,"06");
//         assertEquals(fullDate,"2021-12-06");
    }

    /**
     * This test checks if the method changes the size of the log list by adding a log.
     */
    @Test
    public void loggerServiceWriteLog_addsSomeLog(){
        int sizeBefore = repo.findAll().size();
        loggerService.writeLog("Message",1);
        int sizeAfter = repo.findAll().size();

        assertEquals(sizeBefore,sizeAfter-1);
    }

    /**
     * This test checks if the log added has the corresponding information added correctly.
     */
    @Test
    public void loggerServiceWriteLog_writesCorrectLevel_writesCorrectMessage(){
        loggerService.writeLog("Message",1);
        loggerService.writeLog(" ",2);
        loggerService.writeLog("Any Level",-15);


        Logger log1 = repo.findAll().get(repo.findAll().size()-3);
        Logger log2 = repo.findAll().get(repo.findAll().size()-2);
        Logger log3 = repo.findAll().get(repo.findAll().size()-1);

        int checkLevel1 = log1.getWarningLevel();
        int checkLevel2 = log2.getWarningLevel();
        int checkLevel3 = log3.getWarningLevel();

        String checkMessage1 = log1.getMessage();
        String checkMessage2 = log2.getMessage();
        String checkMessage3 = log3.getMessage();

        String checkDateTime1 = log1.getDateTime();

        /*
         * Checks if message and level are written correctly
         */
        assertEquals("Message",checkMessage1);
        assertEquals(1,checkLevel1);

        /*
         * Checks an empty string for message and different level
         */
        assertEquals(" ",checkMessage2);
        assertEquals(2,checkLevel2);

        /*
         * Checks for level outside of expected range
         */
        assertEquals("Any Level",checkMessage3);
        assertEquals(-15,checkLevel3);

        /*
         * Show that message and dateTime are not the same.
         */
        assertNotEquals(checkMessage1,checkDateTime1);
    }
}
