package com.dayen.dayen.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {
	private final JavaMailSender javaMail;

	@Value("${SMTP_USER}")
	private String email;

	public EmailService(JavaMailSender javaMail) {
		this.javaMail = javaMail;
	}

	public void sendMail(@NotNull @Email String toEmail, @NotNull @NotBlank String subject, @NotNull @NotBlank String content) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = javaMail.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(email, "Dayen");
		helper.setTo(toEmail);

		helper.setSubject(subject);
		helper.setText(content, true);

		javaMail.send(message);
	}
}
