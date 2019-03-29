package com.esintcorp.logic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esintcorp.data.model.PaymentCard;
import com.esintcorp.data.model.User;
import com.esintcorp.data.repository.PaymentCardRepository;
import com.esintcorp.data.repository.UserRepository;

@RestController
public class PaymentCardController {

	@Autowired
	private PaymentCardRepository paymentCardRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/payment")
	public PaymentCard payment(@Valid @RequestBody PaymentCard paymentCard) {
		System.out.println("card: " + paymentCard);
		User user = userRepository.findByEmail(paymentCard.getUser().getEmail());

		PaymentCard paymentCardFound = paymentCardRepository.findByUser(user);
		if (paymentCardFound != null) {
			paymentCardFound.setCardName(paymentCard.getCardName());
			paymentCardFound.setExpirationDate(paymentCard.getExpirationDate());
			return paymentCardRepository.save(paymentCardFound);
		} else {
			paymentCard.setUser(user);
			return paymentCardRepository.save(paymentCard);
		}
	}
}
