package com.pluralsight.conference.util;

import com.pluralsight.conference.model.Account;
import org.springframework.context.ApplicationEvent;

public class OnCreateAccountEvent extends ApplicationEvent {
    private String appUrl;
    private Account account;

    public OnCreateAccountEvent(Account account, String appUrl) {
        super(account);

        this.account = account;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public Account getAccount() {
        return account;
    }
}
