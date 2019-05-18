package com.esintcorp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esintcorp.data.model.Declaration;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Long> {

}
