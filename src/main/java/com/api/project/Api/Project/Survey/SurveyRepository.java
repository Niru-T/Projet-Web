package com.api.project.Api.Project.Survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {

    @Query("from Survey where user_id=?1")
    public List<Survey> getUserSurvey(int user_id);

    @Query("select s from Survey s ORDER BY survey_id")
    public List<Survey> getAllSurvey();

   /* @Query("SELECT location.location_name FROM location INNER JOIN survey ON location.location_id=survey.location_id WHERE survey.user_id=?1")
    public List<Survey> showLoggedUserSurvey(int user_id);*/



}
