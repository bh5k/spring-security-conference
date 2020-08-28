package com.pluralsight.conference.controller;

import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.model.Registration;
import com.pluralsight.conference.service.AccountService;
import com.pluralsight.conference.util.OnCreateAccountEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("account")
    public String getRegistration(@ModelAttribute ("account")Account account) {
        return "account";
    }

    @PostMapping("account")
    public String addRegistration(@Valid @ModelAttribute ("account")
                                              Account account,
                                  BindingResult result) {

        if(result.hasErrors()) {
            System.out.println("There were errors");
            return "account";
        }

        //should verify that account and user doesn't already exist
        //should also verify that the email address is valid

        //encrypt password
        account.setPassword(encoder.encode(account.getPassword()));

        System.out.println("Account: " + account.getFirstName());

        account = accountService.create(account);

        eventPublisher.publishEvent(new OnCreateAccountEvent(account,"conference_war"));

        return "redirect:account";
    }

    @GetMapping("accountConfirm")
    public String confirmAccount(@RequestParam("token") String token) {
        System.out.println(token);

        accountService.confirmAccount(token);

        return "accountConfirmed";
    }

}