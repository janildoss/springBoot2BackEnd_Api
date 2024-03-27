package com.Api.springBoot2BackEnd_Api.services;

import org.springframework.mail.SimpleMailMessage;

import com.Api.springBoot2BackEnd_Api.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
