package com.esintcorp.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esintcorp.data.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}
