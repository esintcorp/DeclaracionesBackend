package com.esintcorp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esintcorp.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
