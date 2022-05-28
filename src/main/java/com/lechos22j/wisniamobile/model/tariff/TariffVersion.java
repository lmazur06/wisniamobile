package com.lechos22j.wisniamobile.model.tariff;

import com.lechos22j.wisniamobile.model.extraservices.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class TariffVersion {
    protected UUID id;
    protected Date endDate;
    protected Set<Service> services;
    protected Tariff tariff;

    protected TariffVersion() {
        this.id = UUID.randomUUID();
        this.services = new HashSet<>();
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
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

    public Tariff getTariff() {
        return tariff;
    }
}