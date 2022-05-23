package com.lechos22j.wisniamobile.tariff;

import com.lechos22j.wisniamobile.contract.Contract;

public class PrePaidTariffVersion extends TariffVersion {
    private double callPrice;
    private double smsPrice;
    private double mmsPrice;
    private double dataTransferPrice;

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
