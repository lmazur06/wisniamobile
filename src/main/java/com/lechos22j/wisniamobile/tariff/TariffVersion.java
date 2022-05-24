package com.lechos22j.wisniamobile.tariff;

import com.lechos22j.wisniamobile.contract.Contract;
import com.lechos22j.wisniamobile.extraservices.Service;
import com.lechos22j.wisniamobile.reckoning.Account;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class TariffVersion {
    protected UUID id;
    protected Date endDate;
    protected Set<Service> services;

    protected TariffVersion() {
        this.id = UUID.randomUUID();
        this.services = new HashSet<>();
    }

    public void addService(Service service) {
        this.services.add(service);
    }

    public UUID getId() {
        return id;
    }
    public Date getEndDate() {
        return endDate;
    }
    public Set<Service> getServices() {
        return services;
    }
}
