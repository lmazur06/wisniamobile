package com.lechos22j.wisniamobile.model.account;

import com.lechos22j.wisniamobile.model.contract.Contract;
import com.lechos22j.wisniamobile.model.customer.Customer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Account {
    public static class Builder {
        private final Account account;

        public Builder() {
            account = new Account();
        }

        public Builder setId(UUID id) {
            account.id = id;
            return this;
        }
        public Builder setCustomer(Customer customer) {
            account.customer = customer;
            return this;
        }
        public Builder setBillingDay(int billingDay) {
            account.billingDay = billingDay;
            return this;
        }
        public Builder genBillingDay() {
            LocalDate now = LocalDate.now();
            account.billingDay = now.getDayOfMonth();
            if (account.billingDay >= now.lengthOfMonth() - 1)
                account.billingDay = 1;
            else if(account.billingDay >= 27)
                account.billingDay = now.lengthOfMonth() - 1;
            else if(account.billingDay >= 24)
                account.billingDay = 27;
            else if(account.billingDay >= 21)
                account.billingDay = 24;
            else if(account.billingDay >= 17)
                account.billingDay = 21;
            else if(account.billingDay >= 14)
                account.billingDay = 17;
            else if(account.billingDay >= 11)
                account.billingDay = 14;
            else if(account.billingDay >= 7)
                account.billingDay = 11;
            else if(account.billingDay >= 4)
                account.billingDay = 7;
            else
                account.billingDay = 4;
            return this;
        }

        public Account get() {
            if(account.billingDay == 0)
                genBillingDay();
            return account;
        }
    }
    private UUID id;
    private Set<Contract> contracts;
    private Customer customer;
    private int billingDay;

    protected Account() {
        this.id = UUID.randomUUID();
        this.contracts = new HashSet<>();
    }

    public UUID getId() {
        return id;
    }
    public Set<Contract> getContracts() {
        return contracts;
    }
    public Customer getCustomer() {
        return customer;
    }
}
