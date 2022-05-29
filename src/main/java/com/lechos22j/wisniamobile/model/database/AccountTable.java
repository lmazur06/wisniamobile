package com.lechos22j.wisniamobile.model.database;

import com.lechos22j.wisniamobile.model.account.Account;
import com.lechos22j.wisniamobile.model.contract.Contract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AccountTable {
    private static Map<String, Account> cache = new HashMap<>();

    public static void create() throws SQLException {
        DbInterface.getInstance().query(
            "CREATE TABLE IF NOT EXISTS accounts (" +
                "id TEXT PRIMARY KEY," +
                "customer_id TEXT," +
                "billing_day INTEGER" +
            ");"
        );
    }
    public static void add(Account account) throws SQLException {
        cache.put(account.getId().toString(), account);
        DbInterface.getInstance().query(
            "INSERT OR REPLACE INTO accounts VALUES (" +
                "'" + account.getId() + "'," +
                "'" + account.getCustomer().getId() + "'," +
                account.getBillingDay() +
            ");"
        );
        for(Contract contract : account.getContracts())
            ContractTable.add(contract);
    }
    public static Account get(String id) throws SQLException {
        if(cache.containsKey(id)){
            return cache.get(id);
        }
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT * FROM accounts WHERE id = '" + id + "';"
        );
        if(resultSet.next()){
            Account account = new Account.Builder()
                .setId(UUID.fromString(resultSet.getString("id")))
                .setCustomer(CustomerTable.get(resultSet.getString("customer_id")))
                .setBillingDay(resultSet.getInt("billing_day"))
                .get();
            cache.put(id, account);
            return account;
        }
        return null;
    }
    public static Set<Account> getAll() throws SQLException {
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT id FROM accounts;"
        );
        Set<Account> accounts = new HashSet<>();
        while(resultSet.next()){
            accounts.add(get(resultSet.getString("id")));
        }
        return accounts;
    }
    public static void close(){
        cache.clear();
    }
}
