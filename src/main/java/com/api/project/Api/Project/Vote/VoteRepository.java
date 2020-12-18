package com.api.project.Api.Project.Vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("FROM Vote WHERE survey_id=?1")
    public Vote findBySurveyId(int survey_id);

    @Transactional
    @Modifying
    @Query(value="UPDATE vote SET vote.vote_count = vote.vote_count + 1 WHERE survey_id=?1",nativeQuery = true)
    public void addVoteOnSurvey(int survey_id);

}
