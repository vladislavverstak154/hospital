package com.vvs.training.hospital.services.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.vvs.training.hospital.services.ISenderTLS;

@Component
public class SenderTLSImpl implements ISenderTLS {

	private String username;
	private String password;
	private Properties props;

	public void send(String subject, String text, String fromEmail, String toEmail) {
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			
			Message message = new MimeMessage(session);
			// from
			message.setFrom(new InternetAddress(username));
			// to
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("vladverstak@yandex.ru"));
			// Title
			message.setSubject(subject);
			// The text
			message.setText(text);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public SenderTLSImpl() {

		this.username = "vladislavverstak@gmail.com";
		this.password = "polakmaly1992";

		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	}

}
