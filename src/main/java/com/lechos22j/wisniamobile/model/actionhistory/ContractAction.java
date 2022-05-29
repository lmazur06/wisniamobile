package com.lechos22j.wisniamobile.model.actionhistory;

import com.lechos22j.wisniamobile.model.contract.Contract;

import java.sql.Date;
import java.util.UUID;

public enum ContractAction {
    SMS,
    MMS,
    PHONE_CALL,
    INTERNET,
    EXTRA_SERVICE_USAGE
    ;

    private UUID id;
    private Date date;
    private Contract contract;
    private double amount;
    private double cost;

    public void setId(UUID id) {
        this.id = id;
    }
    public void genId() {
        this.id = UUID.randomUUID();
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setContract(Contract contract) {
        this.contract = contract;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    public UUID getId() {
        return id;
    }
    public Date getDate() {
        return date;
    }
    public Contract getContract() {
        return contract;
    }
    public double getAmount() {
        return amount;
    }
    public double getCost() {
        return cost;
    }
}
