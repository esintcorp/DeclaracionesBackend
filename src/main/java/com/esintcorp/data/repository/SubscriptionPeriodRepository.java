package com.esintcorp.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esintcorp.data.model.SubscriptionPeriod;

public interface SubscriptionPeriodRepository extends JpaRepository<SubscriptionPeriod, Long> {

	@Query("SELECT sp FROM SubscriptionPeriod sp WHERE sp.status = 'active'")
	public List<SubscriptionPeriod> findActive();

	@Query("SELECT sp FROM SubscriptionPeriod sp WHERE sp.code = :code")
	public SubscriptionPeriod findByCode(@Param("code") String code);
}
