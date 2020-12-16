package com.api.project.Api.Project.Survey;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int survey_id;
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_user_id"))
    private int user_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_proposed;
    private String location_name;

    public Date getDate_proposed() {
        return date_proposed;
    }

    public void setDate_proposed(Date date_proposed) {
        this.date_proposed = date_proposed;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
