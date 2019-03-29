package com.esintcorp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esintcorp.data.model.Subscription;
import com.esintcorp.data.model.User;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	@Query("SELECT s FROM Subscription s WHERE s.user = :user")
	public Subscription findByUser(@Param("user") User user);
}
