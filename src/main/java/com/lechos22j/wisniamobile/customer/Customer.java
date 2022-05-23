package com.lechos22j.wisniamobile.customer;

import com.lechos22j.wisniamobile.reckoning.Account;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class Customer {
    protected UUID id;
    protected Set<Account> accounts;
    public Set<Account> getAccounts() {
        return accounts;
    }
    protected Customer() {
        this.id = UUID.randomUUID();
        accounts = new HashSet<>();
    }
}
