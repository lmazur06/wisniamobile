package com.lechos22j.wisniamobile.model.customer;

import com.lechos22j.wisniamobile.model.account.Account;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class Customer {
    protected UUID id;
    protected Set<Account> accounts;
    public Set<Account> getAccounts() {
        return accounts;
    }
    public void addAccount(Account account) {
        accounts.add(account);
    }
    protected Customer() {
        this.id = UUID.randomUUID();
        accounts = new HashSet<>();
    }

    public UUID getId() {
        return id;
    }
}
