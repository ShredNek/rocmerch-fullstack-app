package com.rocmerchbackend.rocmerchbackend.email;

import com.rocmerchbackend.rocmerchbackend.UtilsFunctions;
import com.rocmerchbackend.rocmerchbackend.model.MerchandiseOrders;
import io.micrometer.common.lang.NonNull;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    @Value("${spring.mail.host}")
    private String smtpHost;
    @Value("${spring.mail.port}")
    private String smtpPort;
    @Value("${spring.mail.username}")
    private String smtpUser;
    @Value("${spring.mail.password}")
    private String smtpPass;
    private final String FROM_ADDRESS = "danielleemusic98@Gmail.com";

    public void sendEmail(String subject, String userMessage, String toEmailAddress) throws MessagingException {
        //configure SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        //create the Session object
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUser, smtpPass);
            }
        };

        Session session = Session.getInstance(props, authenticator);

        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_ADDRESS));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress));
            message.setSubject(subject);
            message.setContent(userMessage, "text/html");

            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void composeHtmlEmailTemplateFromMerchandiseOrder(MerchandiseOrders order) throws IllegalStateException {
        var items = order.getMerchItemsInCart();
        final String itemHtmlTemplate = new UtilsFunctions().getItemsHtmlTemplate();
        StringBuilder totalItems = new StringBuilder();

        items.forEach(i -> {
            var currItemString = itemHtmlTemplate;
            currItemString = currItemString.replace("{ITEM_NAME}", i.getMerchandiseItem().getName());
            currItemString = currItemString.replace("{ITEM_QUANTITY}", String.valueOf(i.getQuantity()));
            currItemString = currItemString.replace("{ITEM_PRICE}", String.valueOf(i.getMerchandiseItem().getPrice()));
            totalItems.append(currItemString);
        });

        setItemsAsHtmlString(String.valueOf(totalItems));

        // They give us the full name with a whitespace between the first and
        // family name, so we're just taking the first name for the email.
        var firstNameForOrder = order.getNameForOrder().split(" ")[0];
        var emailTemplate = new UtilsFunctions().getOrderShippedHtmlTemplate();

        emailTemplate = emailTemplate.replace("{CART_ITEMS}", getItemsAsHtmlString());
        emailTemplate = emailTemplate.replace("{FIRST_NAME}", firstNameForOrder);
        emailTemplate = emailTemplate.replace("{NAME_FOR_ORDER}", order.getNameForOrder());
        emailTemplate = emailTemplate.replace("{SUBTOTAL}", order.getTotalPrice().toString());
        emailTemplate = emailTemplate.replace("{SHIPPING}", "0");
        emailTemplate = emailTemplate.replace("{TOTAL}", order.getTotalPrice().toString());
        emailTemplate = emailTemplate.replace("{ADDRESS_FOR_ORDER}", order.getAddressForOrder());
        emailTemplate = emailTemplate.replace("{EMAIL_FOR_ORDER}", order.getEmailForOrder());
        emailTemplate = emailTemplate.replace("{NUMBER_FOR_ORDER}", order.getPhoneForOrder());

        setEmailHeader("Thank you for your support, " + firstNameForOrder + ". :)");
        setEmailAsHtmlString(emailTemplate);
    }

    private String itemsAsHtmlString;

    public String getEmailAsHtmlString() {
        return emailAsHtmlString;
    }

    public void setEmailAsHtmlString(@NonNull String emailAsHtmlString) {
        this.emailAsHtmlString = emailAsHtmlString;
    }

    private String emailAsHtmlString;

    public String getItemsAsHtmlString() {
        return itemsAsHtmlString;
    }

    public void setItemsAsHtmlString(@NonNull String itemsAsHtmlString) {
        this.itemsAsHtmlString = itemsAsHtmlString;
    }

    private String emailHeader;

    public String getEmailHeader() {
        return emailHeader;
    }

    public void setEmailHeader(@NonNull String emailHeader) {
        this.emailHeader = emailHeader;
    }
}
