package com.esintcorp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esintcorp.data.model.PaymentCard;
import com.esintcorp.data.model.User;

@Repository
public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {

	@Query("SELECT p FROM PaymentCard p WHERE p.cardNumber = :cardNumber")
	public PaymentCard findByCardNumber(@Param("cardNumber") String cardNumber);

	@Query("SELECT p FROM PaymentCard p WHERE p.user = :user")
	public PaymentCard findByUser(@Param("user") User user);

}
