package com.denisgl.models;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class Sender {
	
	private static final Logger logger = Logger.getLogger(Sender.class);

	protected final static String usernameOfMyMail = "denisglotov.1911@gmail.com";
	protected final static String passwordOfMyMail = "123456asdzxcv";
	
	static Session session;
	
	static {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usernameOfMyMail, passwordOfMyMail);
			}
		});
	}

	
	public static boolean sendPassword(String toEmail, String passwordForClient) {
		
		String content = "<h2>Dear user! Your password :  " + passwordForClient + "</h2><h2>Link to the website : </h2> <a href=\"https://calculator-netcracker.herokuapp.com/\">Click here!</a> ";
		
		return sendMessage(toEmail,"Registration",content);
	}
	
	private static boolean sendMessage(String toEmail,String Subject,String Content) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(usernameOfMyMail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject(Subject);
			message.setContent(Content,"text/html");
			Transport.send(message);
			return true;
		} catch(MessagingException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @param email from whom?
	 */
	public static boolean sendFeedBackAndCheck(String feedback,String subject,String email) {
		return sendMessage("denisglotov98@mail.ru",subject + " from " + email,feedback);
	}
}