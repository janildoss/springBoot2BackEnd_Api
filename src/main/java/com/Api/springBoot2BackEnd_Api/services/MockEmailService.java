package com.Api.springBoot2BackEnd_Api.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;


public class MockEmailService extends AbstractEmailService {
	
   private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
     
   @Override
   public void sendEmail(SimpleMailMessage msg) {
	   LOG.info("Simulando envio de email...");
	   LOG.info(msg.toString());
	   LOG.info("Email eviado");
   }

    @Override
	public void sendHtmlEmail(MimeMessage msg) {		
		 LOG.info("Simulando envio de email Html...");
		 LOG.info(msg.toString());
		 LOG.info("Email eviado");
	}
   

}
