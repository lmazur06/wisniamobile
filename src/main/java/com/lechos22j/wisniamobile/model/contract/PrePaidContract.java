package com.lechos22j.wisniamobile.model.contract;

import com.lechos22j.wisniamobile.model.account.Account;
import com.lechos22j.wisniamobile.model.tariff.PrePaidTariffVersion;
import com.lechos22j.wisniamobile.model.tariff.TariffVersion;

import java.sql.Date;
import java.util.UUID;

public class PrePaidContract extends Contract {
    public static class Builder {
        private final PrePaidContract contract;
        public Builder() {
            this.contract = new PrePaidContract();
        }

        public Builder setId(UUID id) {
            contract.id = id;
            return this;
        }
        public Builder setPhoneNumber(String phoneNumber) {
            contract.phoneNumber = phoneNumber;
            return this;
        }
        public Builder setAccount(Account account) {
            contract.account = account;
            return this;
        }
        public Builder setEndDate(Date endDate) {
            contract.endDate = endDate;
            return this;
        }
        public Builder setTariffVersion(PrePaidTariffVersion tariffVersion) {
            contract.tariff = tariffVersion;
            return this;
        }
        public Builder setBalance(double balance) {
            contract.balance = balance;
            return this;
        }

        public PrePaidContract get() {
            return this.contract;
        }
    }

    private PrePaidTariffVersion tariff;
    private double balance;

    protected PrePaidContract() {
        super();
    }

    @Override
    public TariffVersion getTariff() {
        return tariff;
    }

    @Override
    public void sendSms() {
        if(balance - tariff.getSmsPrice() < 0) {
            throw new IllegalStateException("Not enough money");
        }
        balance -= tariff.getSmsPrice();
    }

    @Override
    public void sendMms() {
        if(balance - tariff.getMmsPrice() < 0) {
            throw new IllegalStateException("Not enough money");
        }
        balance -= tariff.getMmsPrice();
    }

    @Override
    public void usePhoneMinutes(double minutes) {
        if(balance - tariff.getCallPrice() * minutes < 0) {
            throw new IllegalStateException("Not enough money");
        }
        balance -= tariff.getCallPrice() * minutes;
    }

    @Override
    public void useData(double volume) {
        if(balance - tariff.getDataTransferPrice() * volume < 0) {
            throw new IllegalStateException("Not enough money");
        }
        balance -= tariff.getDataTransferPrice() * volume;
    }

    public double getBalance() {
        return balance;
    }
}
