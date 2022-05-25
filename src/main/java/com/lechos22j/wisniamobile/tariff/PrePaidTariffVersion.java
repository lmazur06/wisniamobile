package com.lechos22j.wisniamobile.tariff;

import com.lechos22j.wisniamobile.contract.Contract;
import com.lechos22j.wisniamobile.extraservices.Service;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

public class PrePaidTariffVersion extends TariffVersion {
    public static class Builder {
        private final PrePaidTariffVersion tariffVersion;

        public Builder() {
            tariffVersion = new PrePaidTariffVersion();
        }

        public Builder setId(UUID id) {
            tariffVersion.id = id;
            return this;
        }
        public Builder setEndDate(Date endDate) {
            tariffVersion.endDate = endDate;
            return this;
        }
        public Builder setServices(Set<Service> services) {
            tariffVersion.services = services;
            return this;
        }
        public Builder setTariff(Tariff tariff) {
            tariffVersion.tariff = tariff;
            return this;
        }
        public Builder setCallPrice(double callPrice) {
            tariffVersion.callPrice = callPrice;
            return this;
        }
        public Builder setSmsPrice(double smsPrice) {
            tariffVersion.smsPrice = smsPrice;
            return this;
        }
        public Builder setMmsPrice(double mmsPrice) {
            tariffVersion.mmsPrice = mmsPrice;
            return this;
        }
        public Builder setDataTransferPrice(double dataTransferPrice) {
            tariffVersion.dataTransferPrice = dataTransferPrice;
            return this;
        }

        public PrePaidTariffVersion get() {
            return tariffVersion;
        }
    }

    private double callPrice;
    private double smsPrice;
    private double mmsPrice;
    private double dataTransferPrice;

    public PrePaidTariffVersion() {
        super();
    }

    public double getCallPrice() {
        return callPrice;
    }
    public double getSmsPrice() {
        return smsPrice;
    }
    public double getMmsPrice() {
        return mmsPrice;
    }
    public double getDataTransferPrice() {
        return dataTransferPrice;
    }
}
