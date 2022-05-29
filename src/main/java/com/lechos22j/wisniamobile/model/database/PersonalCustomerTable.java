package com.lechos22j.wisniamobile.model.database;

import com.lechos22j.wisniamobile.model.customer.PersonalCustomer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PersonalCustomerTable {
    private static Map<String, PersonalCustomer> cache = new HashMap<>();

    public static void create() throws SQLException {
        DbInterface.getInstance().query(
            "CREATE TABLE IF NOT EXISTS personal_customers (" +
                "id TEXT PRIMARY KEY," +
                "pesel TEXT," +
                "name TEXT," +
                "surname TEXT," +
                "address TEXT," +
                "phone TEXT," +
                "email TEXT" +
                ");"
        );
    }
    public static void add(PersonalCustomer customer) throws SQLException {
        cache.put(customer.getId().toString(), customer);
        DbInterface.getInstance().query(
            "INSERT OR REPLACE INTO personal_customers VALUES (" +
                "'" + customer.getId() + "'," +
                "'" + customer.getPesel() + "'," +
                "'" + customer.getName() + "'," +
                "'" + customer.getSurname() + "'," +
                "'" + customer.getAddress() + "'," +
                "'" + customer.getPhone() + "'," +
                "'" + customer.getEmail() + "'" +
            ");"
        );
    }
    public static PersonalCustomer get(String id) throws SQLException {
        PersonalCustomer customer = cache.get(id);
        if (customer != null) {
            return customer;
        }
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT * FROM personal_customers WHERE id = '" + id + "';"
        );
        if (resultSet.next()) {
            customer = new PersonalCustomer.Builder()
                .setId(UUID.fromString(resultSet.getString("id")))
                .setPesel(resultSet.getString("pesel"))
                .setName(resultSet.getString("name"))
                .setSurname(resultSet.getString("surname"))
                .setAddress(resultSet.getString("address"))
                .setPhone(resultSet.getString("phone"))
                .setEmail(resultSet.getString("email"))
                .get();
            cache.put(id, customer);
            return customer;
        }
        return null;
    }
}
