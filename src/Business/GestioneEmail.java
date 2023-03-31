package Business;
//https://www.youtube.com/watch?v=A7HAB5whD6I tutorial seguito
import View.Sottomenu_MenuUtente.VisualizzaCarrelloUtente;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.Properties;

public class GestioneEmail {

    public static void sendMail(String recipient, String testo) throws MessagingException {

        try{
            System.out.println("Preparazione per l'invio dell'email");
            //Setto tutte le impostazioni riguardanti la mail che stiamo utilizzando, nel mio caso gmail
            Properties properties = new Properties();
            properties.put("mail.smtp.auth","true");
            properties.put("mail.smtp.starttls.enable","true");
            properties.put("mail.smtp.host","smtp.gmail.com");
            properties.put("mail.smtp.port","587");

            //Setto le credenziali della mia email
            String myAccountEmail = "myshop2021emfg@gmail.com";
            String password = "Myshop2021";

            //Avvio l'autenticazione
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail,password);
                }
            });

            //Creo il messaggio e lo invio
            Message message = prepareMessage(session,myAccountEmail,recipient,testo);
            Transport.send(message);
            System.out.println("Email inviata!");
        }catch (Exception E){

        }

    }

    //Creazione del messaggio
    private static Message prepareMessage(Session session,String myAccountEmail,String recipient,String testo) throws MessagingException {

        Message message = new MimeMessage(session);
        //Setto l'email con cui lo sto inviando e sucessivamente ci metterò anche il destinatario
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        message.setSubject("EMAIL DA MYSHOP");
        message.setText(testo);

        return message;
    }

}
