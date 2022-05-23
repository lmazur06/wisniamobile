package com.lechos22j.wisniamobile.contract;

import com.lechos22j.wisniamobile.reckoning.Account;
import com.lechos22j.wisniamobile.tariff.TariffVersion;

import java.util.Date;
import java.util.UUID;

public abstract class Contract {
    protected UUID id;
    protected String phoneNumber;
    protected Account account;
    protected Date endDate;

    public UUID getId() {
        return id;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Account getAccount(){
        return account;
    }
    public Date getEndDate() {
        return endDate;
    }
    public abstract TariffVersion getTariff();
}
