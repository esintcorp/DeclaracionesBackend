package com.esintcorp.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esintcorp.data.model.Declaration;
import com.esintcorp.data.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q "
        + "  WHERE q.declaration = :declaration "
        + "  ORDER BY q.billType.id ASC")
    public List<Question> findByDeclaration(
        @Param("declaration") Declaration declaration
    );
}
