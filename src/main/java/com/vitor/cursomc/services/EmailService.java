package com.vitor.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.vitor.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
