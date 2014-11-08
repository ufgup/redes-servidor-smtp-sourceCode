package br.ufg.inf.redes.controle;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.ufg.inf.redes.entidades.Email;

public class Reencaminhador {
	
	//autenticação do Yahoo
	private String username = "testedeenvioemail@yahoo.com";
	private String password = "ZGFuaWVsbWVsb2dwaUBnbWFpbC5jb20=";
	private String yahooHost = "smtp.mail.yahoo.com";

	public boolean encaminharParaOutroSMTP(Email email) {

		  String to = email.getDestinatario();//change accordingly
	      String from = email.getRemetente();//change accordingly
	      Properties props = getProperties();

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });
	      
	      session.setDebug(true);

	      try {
	         Message message = getMessage(email, to, from, session);
	         Transport.send(message);
	         return true;

	      } catch (MessagingException e) {
	            return false;
	      }
		
	}

	private Message getMessage(Email email, String to, String from, Session session) 
	throws MessagingException, AddressException {
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(email.getAssunto());
		message.setText(email.getMensagem());
		
		return message;
	}

	private Properties getProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.host", yahooHost);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");
		return props;
	}
}
