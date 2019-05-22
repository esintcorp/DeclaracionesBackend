package com.esintcorp.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esintcorp.data.model.Answer;
import com.esintcorp.data.model.User;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT a FROM Answer a "
        + "  WHERE a.user = :user "
        + "    AND a.createdAt BETWEEN :start AND :end")
    public List<Answer> findThisMonth(
        @Param("user") User user,
        @Param("start") Date start,
        @Param("end") Date end
    );
}
