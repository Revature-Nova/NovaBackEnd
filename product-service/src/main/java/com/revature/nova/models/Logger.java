package com.revature.nova.models;

import javax.persistence.*;

@Entity
public class Logger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    private Integer warningLevel;
    public Integer getWarningLevel() {
        return warningLevel;
    }
    public void setWarningLevel(Integer warningLevel) {
        this.warningLevel = warningLevel;
    }

    @Column
    private String dataTime;
    public String getDataTime() {
        return dataTime;
    }
    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    @Column(columnDefinition = "varchar(20000)")
    private String message;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Logger() {

    }

    public Logger(String dataTime, String message, Integer warningLevel) {
        this.dataTime = dataTime;
        this.message = message;
        this.warningLevel = warningLevel;
    }

}