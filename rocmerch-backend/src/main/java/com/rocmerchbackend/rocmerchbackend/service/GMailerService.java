package com.rocmerchbackend.rocmerchbackend.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.rocmerchbackend.rocmerchbackend.Utils;
import com.rocmerchbackend.rocmerchbackend.model.MerchandiseOrders;
import org.apache.commons.codec.binary.Base64;
import org.springframework.lang.NonNull;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Set;

import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;
import static javax.mail.Message.RecipientType.TO;

public class GMailerService {

    private static final String TEST_EMAIL_ADDRESS = "danielleemusic98@gmail.com";
    private Gmail service;

    public void GmailerToRename() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
                .setApplicationName("Test mailer")
                .build();

        System.out.println(service);
    }

    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
            throws IOException {
        // Load client secrets.
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(jsonFactory, new InputStreamReader(GMailerService.class.getResourceAsStream("/client_secret_372423722108-phu5no9r09ntog927ll2nogc0erplott.apps.googleusercontent.com.json")));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Set.of(GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }

    public void sendEmail(String subject, String userMessage) throws Exception {

        // Encode as MIME message
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(TEST_EMAIL_ADDRESS));
        email.addRecipient(TO, new InternetAddress(TEST_EMAIL_ADDRESS));
        email.setSubject(subject);
        email.setContent(userMessage, "text/html");


        // Encode and wrap the MIME message into a gmail message
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message message = new Message();
        message.setRaw(encodedEmail);


        try {
            // Create send message
            GmailerToRename();
            message = service.users().messages().send("me", message).execute();
            System.out.println("Message id: " + message.getId());
            System.out.println(message.toPrettyString());
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("Unable to send message: " + e.getDetails());
            } else {
                throw e;
            }
        }

    }

    public void composeHtmlEmailTemplateFromMerchandiseOrder(@NonNull MerchandiseOrders order) {

        var items = order.getMerchItemsInCart();
        final String itemHtmlTemplate = new Utils().getItemsHtmlTemplate();
        StringBuilder totalItems = new StringBuilder();

        items.forEach(i -> {
            var currItemString = itemHtmlTemplate;
            currItemString = currItemString.replace("{ITEM_NAME}", i.getMerchandiseItem().getName());
            currItemString = currItemString.replace("{ITEM_QUANTITY}", String.valueOf(i.getQuantity()));
            currItemString = currItemString.replace("{ITEM_PRICE}", String.valueOf(i.getMerchandiseItem().getPrice()));
            totalItems.append(currItemString);
        });

        setItemsAsHtmlString(String.valueOf(totalItems));

        var emailTemplate = new Utils().getOrderShippedHtmlTemplate();
        emailTemplate = emailTemplate.replace("{CART_ITEMS}", getItemsAsHtmlString());
        emailTemplate = emailTemplate.replace("{NAME_FOR_ORDER}", order.getNameForOrder());
        emailTemplate = emailTemplate.replace("{SUBTOTAL}", order.getTotalPrice().toString());
        emailTemplate = emailTemplate.replace("{SHIPPING}", "0");
        emailTemplate = emailTemplate.replace("{TOTAL}", order.getTotalPrice().toString());
        emailTemplate = emailTemplate.replace("{ADDRESS_FOR_ORDER}", order.getAddressForOrder());
        emailTemplate = emailTemplate.replace("{EMAIL_FOR_ORDER}", order.getEmailForOrder());
        emailTemplate = emailTemplate.replace("{NUMBER_FOR_ORDER}", order.getPhoneForOrder());

        setEmailHeader("Thank you for your support, " + order.getNameForOrder() + ". :)");
        setEmailAsHtmlString(emailTemplate);
    }

    private String itemsAsHtmlString;

    public String getEmailAsHtmlString() {
        return emailAsHtmlString;
    }

    public void setEmailAsHtmlString(String emailAsHtmlString) {
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

    public void setEmailHeader(String emailHeader) {
        this.emailHeader = emailHeader;
    }

}
