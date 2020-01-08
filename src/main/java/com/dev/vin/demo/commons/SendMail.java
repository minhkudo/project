/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.vin.demo.commons;

import com.dev.vin.demo.config.MyConfig;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class SendMail {

    static Logger logger = Logger.getLogger(SendMail.class);

    static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    public static boolean sendMail(String fromEmail, String fromPassMail,
            String subject, String content, ArrayList<String> toEmail, String fromName) {
        boolean flag = false;
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", MyConfig.MAIL_HOST); //MyContext.MAIL_HOST
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.debug", false);
            props.put("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            // Get the default Session object.
            Session session = Session.getInstance(props);
            // Create a default MimeMessage object.
            MimeMessage messageSend = new MimeMessage(session);
            // Set the RFC 822 "From" header field using the
            // value of the InternetAddress.getLocalAddress method.
            messageSend.setFrom(new InternetAddress(fromEmail, fromName));

            Address[] addresses = new Address[toEmail.size()];
            for (int i = 0; i < toEmail.size(); i++) {
                Address address = new InternetAddress(toEmail.get(i));
                addresses[i] = address;
                // Add the given addresses to the specified recipient type.
                messageSend.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail.get(i)));
            }
            // Set the "Subject" header field.
            messageSend.setSubject(subject, "utf-8");
            // Sets the given String as this part's content,
            // with a MIME type of "text/plain".
            Multipart mp = new MimeMultipart("alternative");
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(content, "text/html;charset=utf-8");
            mp.addBodyPart(mbp);
            messageSend.setContent(mp);
            messageSend.saveChanges();
            // Send message
            Transport transport = session.getTransport("smtp");
//            transport.connect(MyConfig.MAIL_HOST, MyConfig.SMTP_MAIL, MyConfig.SMTP_PASS);
            transport.connect(fromEmail, fromPassMail);
            transport.sendMessage(messageSend, addresses);
            transport.close();
            flag = true;
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean sendMail(String subject, String content, ArrayList<String> toEmail, String fromName) {
        boolean flag = false;
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", MyConfig.MAIL_HOST); //MyContext.MAIL_HOST
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.debug", false);
            props.put("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            // Get the default Session object.
            Session session = Session.getInstance(props);
            // Create a default MimeMessage object.
            MimeMessage messageSend = new MimeMessage(session);
            // Set the RFC 822 "From" header field using the
            // value of the InternetAddress.getLocalAddress method.
            messageSend.setFrom(new InternetAddress(MyConfig.SMTP_MAIL, fromName));

            Address[] addresses = new Address[toEmail.size()];
            for (int i = 0; i < toEmail.size(); i++) {
                Address address = new InternetAddress(toEmail.get(i));
                addresses[i] = address;
                // Add the given addresses to the specified recipient type.
                messageSend.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail.get(i)));
            }
            // Set the "Subject" header field.
            messageSend.setSubject(subject, "utf-8");
            // Sets the given String as this part's content,
            // with a MIME type of "text/plain".
            Multipart mp = new MimeMultipart("alternative");
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(content, "text/html;charset=utf-8");
            mp.addBodyPart(mbp);
            messageSend.setContent(mp);
            messageSend.saveChanges();
            // Send message
            Transport transport = session.getTransport("smtp");
//            transport.connect(MyConfig.MAIL_HOST, MyConfig.SMTP_MAIL, MyConfig.SMTP_PASS);
            transport.connect(MyConfig.SMTP_MAIL, MyConfig.SMTP_PASS);
            transport.sendMessage(messageSend, addresses);
            transport.close();
            flag = true;
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
            e.printStackTrace();
        }
        return flag;
    }

    public static ArrayList<String> parseListMail(String input) {
        String[] arrMail = input.split(",|;|\\n|\\s");
        ArrayList<String> list = new ArrayList<>();
        for (String one : arrMail) {
            if (!Tool.checkNull(one) && one.contains("@")) {
                list.add(one.trim());
            }
        }
        return list;
    }

    public static boolean sendMail(String subject, String content, String mailList) {
        boolean flag = false;
        ArrayList<String> listEmail = parseListMail(mailList);
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", MyConfig.MAIL_HOST); //MyContext.MAIL_HOST
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.debug", false);
            props.put("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            // Get the default Session object.
            Session session = Session.getInstance(props);
            // Create a default MimeMessage object.
            MimeMessage messageSend = new MimeMessage(session);
            // Set the RFC 822 "From" header field using the
            // value of the InternetAddress.getLocalAddress method.
            messageSend.setFrom(new InternetAddress(MyConfig.SMTP_MAIL, MyConfig.FROM_NAME));

            Address[] addresses = new Address[listEmail.size()];
            for (int i = 0; i < listEmail.size(); i++) {
                Address address = new InternetAddress(listEmail.get(i));
                addresses[i] = address;
                // Add the given addresses to the specified recipient type.
                messageSend.addRecipient(Message.RecipientType.TO, new InternetAddress(listEmail.get(i)));
            }
            // Set the "Subject" header field.
            messageSend.setSubject(subject, "utf-8");
            // Sets the given String as this part's content,
            // with a MIME type of "text/plain".
            Multipart mp = new MimeMultipart("alternative");
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(content, "text/html;charset=utf-8");
            mp.addBodyPart(mbp);
            messageSend.setContent(mp);
            messageSend.saveChanges();
            // Send message
            Transport transport = session.getTransport("smtp");
//            transport.connect(MyConfig.MAIL_HOST, MyConfig.SMTP_MAIL, MyConfig.SMTP_PASS);
            transport.connect(MyConfig.SMTP_MAIL, MyConfig.SMTP_PASS);
            transport.sendMessage(messageSend, addresses);
            transport.close();
            flag = true;
            for (String s : listEmail) {
                Tool.debug(" EMAIL WAS SENT TO  : " + s);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        }
        return flag;
    }

//    public static boolean sendMail(String subject, String content, String mailList) {
//        boolean flag = false;
//        ArrayList<String> listEmail = parseListMail(mailList);
//        try {
//            Properties props = new Properties();
//            props.put("mail.smtp.host", MAIL_HOST);
//            props.put("mail.smtp.port", "25");
//            props.put("mail.debug", false);
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.enable", "true");
//            props.setProperty("mail.smtp.socketFactory.port", "25");
////            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
//            props.setProperty("mail.smtp.socketFactory.fallback", "false");
//            // Get the default Session object.
//            Session session = Session.getInstance(props);
//            // Create a default MimeMessage object.
//            MimeMessage messageSend = new MimeMessage(session);
//            // Set the RFC 822 "From" header field using the
//            // value of the InternetAddress.getLocalAddress method.
//            messageSend.setFrom(new InternetAddress(SMTP_MAIL, FROM_NAME));
//
//            Address[] addresses = new Address[listEmail.size()];
//            for (int i = 0; i < listEmail.size(); i++) {
//                Address address = new InternetAddress(listEmail.get(i));
//                addresses[i] = address;
//                // Add the given addresses to the specified recipient type.
//                messageSend.addRecipient(Message.RecipientType.TO, new InternetAddress(listEmail.get(i)));
//            }
//            // Set the "Subject" header field.
//            messageSend.setSubject(subject, "utf-8");
//            // Sets the given String as this part's content,
//            // with a MIME type of "text/plain".
//            Multipart mp = new MimeMultipart("alternative");
//            MimeBodyPart mbp = new MimeBodyPart();
//            mbp.setContent(content, "text/html;charset=utf-8");
//            mp.addBodyPart(mbp);
//            messageSend.setContent(mp);
//            messageSend.saveChanges();
//            // Send message
//            Transport transport = session.getTransport("smtp");
////            transport.connect(MyConfig.MAIL_HOST, MyConfig.SMTP_MAIL, MyConfig.SMTP_PASS);
//            transport.connect(SMTP_MAIL, SMTP_PASS);
//            transport.sendMessage(messageSend, addresses);
//            transport.close();
//            flag = true;
//
//            for (String s : listEmail) {
//                Tool.debug(" EMAIL WAS SENT TO  : " + s);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return flag;
//    }
}
