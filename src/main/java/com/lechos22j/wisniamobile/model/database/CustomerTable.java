package com.lechos22j.wisniamobile.model.database;

import com.lechos22j.wisniamobile.model.account.Account;
import com.lechos22j.wisniamobile.model.customer.CompanyCustomer;
import com.lechos22j.wisniamobile.model.customer.Customer;
import com.lechos22j.wisniamobile.model.customer.PersonalCustomer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CustomerTable {
    public static void create() throws SQLException {
        DbInterface.getInstance().query(
            "CREATE TABLE IF NOT EXISTS customers (" +
                "id TEXT PRIMARY KEY" +
            ");"
        );
    }
    public static void add(Customer customer) throws SQLException {
        DbInterface.getInstance().query(
            "INSERT OR REPLACE INTO customers VALUES (" +
                "'" + customer.getId() + "'" +
            ");"
        );
        if (customer instanceof CompanyCustomer companyCustomer)
            CompanyCustomerTable.add(companyCustomer);
        else if (customer instanceof PersonalCustomer personalCustomer)
            PersonalCustomerTable.add(personalCustomer);
        for(Account account : customer.getAccounts())
            AccountTable.add(account);
    }
    public static Customer get(String id) throws SQLException {
        Customer customer;
        customer = PersonalCustomerTable.get(id);
        if (customer == null)
            customer = CompanyCustomerTable.get(id);
        return customer;
    }
}
