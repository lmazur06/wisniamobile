package com.lechos22j.wisniamobile.tariff;

import com.lechos22j.wisniamobile.contract.Contract;
import com.lechos22j.wisniamobile.extraservices.Service;
import com.lechos22j.wisniamobile.reckoning.Account;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class TariffVersion {
    protected Date endDate;
    protected Set<Service> services;

    protected TariffVersion() {
        this.services = new HashSet<>();
    }

    public Date getEndDate() {
        return endDate;
    }
    public Set<Service> getServices() {
        return services;
    }
}
