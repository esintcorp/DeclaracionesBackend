package com.esintcorp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esintcorp.data.model.Declaration;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Long> {

    @Query("SELECT d FROM Declaration d "
        + "  WHERE d.type = :type ")
    public Declaration findByType(
        @Param("type") String type
    );
}
