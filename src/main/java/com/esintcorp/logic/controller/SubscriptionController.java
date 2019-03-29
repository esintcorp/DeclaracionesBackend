package com.esintcorp.logic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esintcorp.data.model.Subscription;
import com.esintcorp.data.model.SubscriptionPeriod;
import com.esintcorp.data.model.User;
import com.esintcorp.data.repository.SubscriptionPeriodRepository;
import com.esintcorp.data.repository.SubscriptionRepository;
import com.esintcorp.data.repository.UserRepository;

@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SubscriptionPeriodRepository subscriptionPeriodRepository;

	@PostMapping("/subscription")
	public Subscription subscribe(@Valid @RequestBody Subscription subscription) {
		System.out.println("subs: " + subscription);
		SubscriptionPeriod period = subscriptionPeriodRepository.findByCode(subscription.getPeriod().getCode());
		User user = userRepository.findByEmail(subscription.getUser().getEmail());

		Subscription subscriptionFound = subscriptionRepository.findByUser(user);
		if (subscriptionFound != null) {
			subscriptionFound.setPeriod(period);
			return subscriptionRepository.save(subscriptionFound);
		} else {
			subscription.setUser(user);
			subscription.setPeriod(period);
			return subscriptionRepository.save(subscription);
		}
	}
}
