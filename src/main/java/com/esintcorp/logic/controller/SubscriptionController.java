package com.esintcorp.logic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esintcorp.data.model.Subscription;
import com.esintcorp.data.repository.SubscriptionRepository;

@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@PostMapping("/subscritpion")
	public Subscription subscribe(@Valid @RequestBody Subscription subscription) {
		
		return null;
	}
}
