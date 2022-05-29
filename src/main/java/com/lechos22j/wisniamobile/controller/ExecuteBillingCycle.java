package com.lechos22j.wisniamobile.controller;

import com.lechos22j.utils.Constants;
import com.lechos22j.utils.Emails;
import com.lechos22j.wisniamobile.model.contract.Contract;
import com.lechos22j.wisniamobile.model.database.AccountTable;
import com.lechos22j.wisniamobile.view.FactureBuilder;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class ExecuteBillingCycle {
    public static void run() throws SQLException {
        AccountTable.getAll().forEach(account -> {
            int dayOfMonth = LocalDate.now().getDayOfMonth();
            if (dayOfMonth == account.getBillingDay()) {
                Emails.sendEmail(account.getCustomer().getEmail(), "Facture",
                    new FactureBuilder()
                        .setAccount(account)
                        .getXml()
                );
                account.getContracts().forEach(Contract::executeBillingCycle);
            }
        });
    }
}
