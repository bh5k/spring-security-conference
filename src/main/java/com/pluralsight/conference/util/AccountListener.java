package com.pluralsight.conference.util;

import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountListener implements ApplicationListener<OnCreateAccountEvent> {

    private String serverUrl = "http://localhost:8080/";

    @Autowired
    private AccountService accountService;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnCreateAccountEvent event) {
        this.confirmCreateAccount(event);
    }

    private void confirmCreateAccount(OnCreateAccountEvent event) {
        Account account = event.getAccount();
        String token = UUID.randomUUID().toString();
        //save token in database
        accountService.createVerificationToken(account, token);

        String recipientAddress = account.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/accountConfirm?token=" + token;
        String message = "Register!!!";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + serverUrl + confirmationUrl);
        mailSender.send(email);
    }
}