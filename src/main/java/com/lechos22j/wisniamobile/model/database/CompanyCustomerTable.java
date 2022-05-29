package com.lechos22j.wisniamobile.model.database;

import com.lechos22j.wisniamobile.model.customer.CompanyCustomer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CompanyCustomerTable {
    private static Map<String, CompanyCustomer> cache = new HashMap<>();
    public static void create() throws SQLException {
        DbInterface.getInstance().query(
            "CREATE TABLE IF NOT EXISTS company_customers (" +
                "id TEXT PRIMARY KEY," +
                "name TEXT," +
                "nip TEXT," +
                "address TEXT," +
                "phone TEXT," +
                "email TEXT" +
            ");"
        );
    }
    public static void add(CompanyCustomer customer) throws SQLException {
        cache.put(customer.getId().toString(), customer);
        DbInterface.getInstance().query(
            "INSERT OR REPLACE INTO company_customers VALUES (" +
                "'" + customer.getId() + "'," +
                "'" + customer.getName() + "'," +
                "'" + customer.getNip() + "'," +
                "'" + customer.getAddress() + "'," +
                "'" + customer.getPhone() + "'," +
                "'" + customer.getEmail() + "'" +
            ");"
        );
    }
    public static CompanyCustomer get(String id) throws SQLException {
        if(cache.containsKey(id))
            return cache.get(id);
        CompanyCustomer customer = null;
        ResultSet result = DbInterface.getInstance().query(
            "SELECT * FROM company_customers WHERE id = '" + id + "';"
        );
        if (result.next()) {
            customer = new CompanyCustomer.Builder()
                .setId(UUID.fromString(result.getString("id")))
                .setName(result.getString("name"))
                .setNip(result.getString("nip"))
                .setAddress(result.getString("address"))
                .setPhone(result.getString("phone"))
                .setEmail(result.getString("email"))
                .get();
            cache.put(customer.getId().toString(), customer);
        }
        return customer;
    }
}
