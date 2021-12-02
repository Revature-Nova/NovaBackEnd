package com.revature.nova.services;

import com.revature.nova.models.Logger;
import com.revature.nova.repositories.LoggerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;

/**
 * Handles logger repo queries
 *
 * @author Kollier Martin
 * @date 11/22/2021
 */
@Service
@Transactional
public class LoggerService {
    private final LoggerRepo repo;

    @Autowired
    public LoggerService(LoggerRepo repo) {
        this.repo = repo;
    }

    public void writeLog(String message, int level) {
        Logger logger = new Logger(level, getCurrentDateTime(), message);

        repo.save(logger);
    }

    private String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(System.currentTimeMillis());
    }
}
