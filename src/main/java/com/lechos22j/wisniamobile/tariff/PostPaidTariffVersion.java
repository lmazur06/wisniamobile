package com.lechos22j.wisniamobile.tariff;

import com.lechos22j.wisniamobile.extraservices.Service;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class PostPaidTariffVersion extends TariffVersion {
    public static class Builder {
        private final PostPaidTariffVersion tariffVersion;

        public Builder() {
            tariffVersion = new PostPaidTariffVersion();
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
        public Builder setMonthlyFee(double monthlyFee) {
            tariffVersion.monthlyFee = monthlyFee;
            return this;
        }
        public Builder setCallMinutes(double callMinutes) {
            tariffVersion.callMinutes = callMinutes;
            return this;
        }
        public Builder setNumberOfSms(int numberOfSms) {
            tariffVersion.numberOfSms = numberOfSms;
            return this;
        }
        public Builder setNumberOfMms(int numberOfMms) {
            tariffVersion.numberOfMms = numberOfMms;
            return this;
        }
        public Builder setDataVolume(double dataVolume) {
            tariffVersion.dataVolume = dataVolume;
            return this;
        }
        public Builder setCallFee(double callFee) {
            tariffVersion.callFee = callFee;
            return this;
        }
        public Builder setSmsFee(double smsFee) {
            tariffVersion.smsFee = smsFee;
            return this;
        }
        public Builder setMmsFee(double mmsFee) {
            tariffVersion.mmsFee = mmsFee;
            return this;
        }
        public Builder setDataTransferFee(double dataTransferFee) {
            tariffVersion.dataTransferFee = dataTransferFee;
            return this;
        }

        public PostPaidTariffVersion get() {
            return tariffVersion;
        }
    }
    private double monthlyFee;
    private double callMinutes;
    private int numberOfSms;
    private int numberOfMms;
    private double dataVolume;
    private double callFee;
    private double smsFee;
    private double mmsFee;
    private double dataTransferFee;

    protected PostPaidTariffVersion() {
        super();
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }
    public double getCallMinutes() {
        return callMinutes;
    }
    public int getNumberOfSms() {
        return numberOfSms;
    }
    public int getNumberOfMms() {
        return numberOfMms;
    }
    public double getDataVolume() {
        return dataVolume;
    }
    public double getCallFee() {
        return callFee;
    }
    public double getSmsFee() {
        return smsFee;
    }
    public double getMmsFee() {
        return mmsFee;
    }
    public double getDataTransferFee() {
        return dataTransferFee;
    }
}
