package com.api.project.Api.Project.Survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {

    @Query("from Survey where user_id=?1")
    public List<Survey> showAllSurvey(int user_id);

}
