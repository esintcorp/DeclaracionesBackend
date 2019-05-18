package com.esintcorp.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esintcorp.data.model.Bill;
import com.esintcorp.data.model.User;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("SELECT b FROM Bill b "
        + "  WHERE b.user = :user "
        + "    AND b.createdAt BETWEEN :start AND :end")
    public List<Bill> findThisMonth(
        @Param("user") User user,
        @Param("start") Date start,
        @Param("end") Date end
    );

}
