package com.revature.nova.models;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO used for logging error data to the database
 *
 * @date 11/22/2021
 * @author User-Feature Team
 */

@Entity
@Table
@Getter @Setter
@NoArgsConstructor(onConstructor = @__(@Autowired))
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Logger {
    @Id
    private Integer id;

    @NonNull
    @Column(name = "warning_level")
    private Integer warningLevel;

    @NonNull
    @Column(name = "date_time")
    private String dateTime;

    @NonNull
    @Column
    private String message;
}