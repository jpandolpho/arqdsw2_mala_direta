package br.com.ifsp.maladireta.service;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import br.com.ifsp.maladireta.model.Aniversariante;

public class EmailService 
{
    private Session mailSession;

    public EmailService() throws Exception 
    {
    	Properties props = new Properties();
    	props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "mail.smtp2go.com");
		props.put("mail.smtp.port", "25");
    	    
    	mailSession = Session.getDefaultInstance(props,
			  new javax.mail.Authenticator() {
			       protected PasswordAuthentication getPasswordAuthentication()
			       {
			             return new PasswordAuthentication("USUÁRIO DO SMTP2GO", "SENHA SMTP2GO");
			       }
			  });
    }

    public void enviarEmailAniversario(Aniversariante aniversariante) throws MessagingException 
    {
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("EMAIL VERIFICADO SMTP2GO"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(aniversariante.getEmail()));
        message.setSubject("Feliz aniversário, " + aniversariante.getNome() + "!");
        message.setText("Olá " + aniversariante.getNome() + ", desejamos um Feliz Aniversário!");

        Transport.send(message);
    }
}
