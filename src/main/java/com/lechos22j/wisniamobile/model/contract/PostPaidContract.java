package com.lechos22j.wisniamobile.model.contract;

import com.lechos22j.wisniamobile.model.reckoning.Account;
import com.lechos22j.wisniamobile.model.tariff.PostPaidTariffVersion;
import com.lechos22j.wisniamobile.model.tariff.TariffVersion;

import java.util.Date;
import java.util.UUID;

public class PostPaidContract extends Contract{
    public static class Builder {
        private final PostPaidContract contract;
        public Builder() {
            this.contract = new PostPaidContract();
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
        public Builder setEndDate(Date endDate) {
            this.contract.endDate = endDate;
            return this;
        }
        public Builder setTariffVersion(PostPaidTariffVersion tariffVersion) {
            this.contract.tariff = tariffVersion;
            return this;
        }
        public Builder applyTariffVersion() {
            this.contract.smsLeft = contract.tariff.getNumberOfSms();
            this.contract.mmsLeft = contract.tariff.getNumberOfMms();
            this.contract.minutesLeft = contract.tariff.getCallMinutes();
            this.contract.dataLeft = contract.tariff.getDataVolume();
            return this;
        }
        public Builder setSmsLeft(int smsLeft) {
            this.contract.smsLeft = smsLeft;
            return this;
        }
        public Builder setMmsLeft(int mmsLeft) {
            this.contract.mmsLeft = mmsLeft;
            return this;
        }
        public Builder setMinutesLeft(double minutesLeft) {
            this.contract.minutesLeft = minutesLeft;
            return this;
        }
        public Builder setDataLeft(double dataLeft) {
            this.contract.dataLeft = dataLeft;
            return this;
        }

        public PostPaidContract get() {
            if(this.contract.endDate == null || this.contract.endDate.after(this.contract.tariff.getEndDate()))
                this.contract.endDate = this.contract.tariff.getEndDate();
            return this.contract;
        }
    }

    private PostPaidTariffVersion tariff;
    private Integer smsLeft;
    private Integer mmsLeft;
    private Double minutesLeft;
    private Double dataLeft;
    private double due;

    protected PostPaidContract() {
        super();
    }

    @Override
    public TariffVersion getTariff() {
        return tariff;
    }

    public void sendSms() {
        if(smsLeft <= 0)
            due += tariff.getSmsFee();
        smsLeft--;
    }
    public void sendMms() {
        if(mmsLeft <= 0)
            due += tariff.getMmsFee();
        mmsLeft--;
    }
    public void useMinutes(double minutes) {
        minutesLeft -= minutes;
        if(minutesLeft < 0)
            due -= minutesLeft * tariff.getCallFee();
        minutesLeft = 0.0;
    }
    public void useData(double data) {
        dataLeft -= data;
        if(dataLeft < 0)
            due -= dataLeft * tariff.getDataTransferFee();
        dataLeft = 0.0;
    }

    public double getTotal() {
        return due + tariff.getMonthlyFee();
    }
}
