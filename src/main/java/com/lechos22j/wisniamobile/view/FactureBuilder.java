package com.lechos22j.wisniamobile.view;

import com.lechos22j.utils.Constants;
import com.lechos22j.wisniamobile.model.customer.CompanyCustomer;
import com.lechos22j.wisniamobile.model.customer.PersonalCustomer;
import com.lechos22j.wisniamobile.model.account.Account;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.UUID;

public class FactureBuilder {

    private UUID id;
    private String city;
    private Date date;
    private Account account;

    public FactureBuilder() {
        this.id = UUID.randomUUID();
        this.date = Date.valueOf(LocalDate.now());
        this.city = Constants.CITY;
    }

    public FactureBuilder setId(UUID id) {
        this.id = id;
        return this;
    }
    public FactureBuilder setAccount(Account account) {
        this.account = account;
        return this;
    }
    public FactureBuilder setDate(Date date) {
        this.date = date;
        return this;
    }
    public FactureBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public Account getAccount() {
        return account;
    }
    public UUID getId() {
        return id;
    }
    public String getXml() {
        return
            "<facture>" +
                "<id>" + id + "</id>" +
                "<city>" + city + "</city>" +
                "<date>" + DateTimeFormatter.ISO_DATE.format(date.toInstant().atOffset(ZoneOffset.UTC)) + "</date>" +
                "<service-provider>" +
                "</service-provider>" +
                "<customer" +
                    (
                        (account.getCustomer() instanceof PersonalCustomer personalCustomer) ? (
                            " type=\"personal\">" +
                            "<name>" + personalCustomer.getName() + "</name>" +
                            "<surname>" + personalCustomer.getSurname() + "</surname>" +
                            "<pesel>" + personalCustomer.getPesel() + "</pesel>" +
                            "<address>" + personalCustomer.getAddress() + "</address>"
                        )
                        : (account.getCustomer() instanceof CompanyCustomer companyCustomer) ? (
                            " type=\"company\">" +
                            "<name>" + companyCustomer.getName() + "</name>" +
                            "<address>" + companyCustomer.getAddress() + "</address>" +
                            "<nip>" + companyCustomer.getNip() + "</nip>"
                        )
                        : ">"
                    ) +
                "</customer>" +
                "<contracts>" +
                    (
                        (account.getContracts() != null) ?
                            account.getContracts().stream().map(contract ->
                                "<contract>" +
                                "</contract>"
                            ).reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append).toString()
                        : ""
                    ) +
                "</contracts>" +
            "</facture>";
    }
}
