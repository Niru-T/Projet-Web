package com.api.project.Api.Project.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("from User where user_email=?1")
    public List<User> findByEmail(String email);

    @Query("from User where user_email=?1 and user_pass=?2")
    public User findByEmailAndPassword(String email, String password);

}
