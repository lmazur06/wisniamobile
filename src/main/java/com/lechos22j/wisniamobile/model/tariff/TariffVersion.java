package com.lechos22j.wisniamobile.model.tariff;

import com.lechos22j.wisniamobile.model.extraservices.ExtraService;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class TariffVersion {
    protected UUID id;
    protected Date endDate;
    protected Set<ExtraService> extraServices;
    protected Tariff tariff;

    protected TariffVersion() {
        this.id = UUID.randomUUID();
        this.extraServices = new HashSet<>();
    }

    public void setServices(Set<ExtraService> extraServices) {
        this.extraServices = extraServices;
    }
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }
    public void addService(ExtraService extraService) {
        this.extraServices.add(extraService);
    }

    public UUID getId() {
        return id;
    }
    public Date getEndDate() {
        return endDate;
    }
    public Set<ExtraService> getServices() {
        return extraServices;
    }

    public Tariff getTariff() {
        return tariff;
    }
}
