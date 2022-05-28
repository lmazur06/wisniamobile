package com.lechos22j.wisniamobile.model.contract;

import com.lechos22j.wisniamobile.model.reckoning.Account;
import com.lechos22j.wisniamobile.model.tariff.PrePaidTariffVersion;
import com.lechos22j.wisniamobile.model.tariff.TariffVersion;

import java.util.UUID;

public class PrePaidContract extends Contract {
    public static class Builder {
        private final PrePaidContract contract;
        public Builder() {
            this.contract = new PrePaidContract();
        }

        public Builder setId(UUID id) {
            this.contract.id = id;
            return this;
        }
        public Builder setPhoneNumber(String phoneNumber) {
            this.contract.phoneNumber = phoneNumber;
            return this;
        }
        public Builder setAccount(Account account) {
            this.contract.account = account;
            return this;
        }
        public Builder setTariffVersion(PrePaidTariffVersion tariffVersion) {
            this.contract.tariff = tariffVersion;
            return this;
        }

        public PrePaidContract get() {
            return this.contract;
        }
    }

    private PrePaidTariffVersion tariff;

    protected PrePaidContract() {
        super();
    }

    @Override
    public TariffVersion getTariff() {
        return tariff;
    }
}
