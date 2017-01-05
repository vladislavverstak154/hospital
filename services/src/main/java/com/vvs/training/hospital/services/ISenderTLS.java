package com.vvs.training.hospital.services;

public interface ISenderTLS {

	public void send(String subject, String text, String fromEmail, String toEmail);

}