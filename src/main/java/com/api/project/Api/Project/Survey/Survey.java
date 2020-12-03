package com.api.project.Api.Project.Survey;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int survey_id;
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_user_id"))
    private int user_id;
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_user_id"))
    private int location_id;
    private Date date_proposed;

    public Date getDate_proposed() {
        return date_proposed;
    }

    public void setDate_proposed(Date date_proposed) {
        this.date_proposed = date_proposed;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
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
