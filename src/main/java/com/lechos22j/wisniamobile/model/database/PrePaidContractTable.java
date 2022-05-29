package com.lechos22j.wisniamobile.model.database;

import com.lechos22j.wisniamobile.model.account.Account;
import com.lechos22j.wisniamobile.model.contract.PostPaidContract;
import com.lechos22j.wisniamobile.model.contract.PrePaidContract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PrePaidContractTable {
    private static Map<String, PrePaidContract> cache = new HashMap<>();
    public static void create() throws SQLException {
        DbInterface.getInstance().query(
            "CREATE TABLE IF NOT EXISTS prepaid_contracts (" +
                "id TEXT PRIMARY KEY," +
                "tariff_id TEXT," +
                "balance REAL" +
            ");"
        );
    }
    public static void add(PrePaidContract contract) throws SQLException {
        cache.put(String.valueOf(contract.getId()), contract);
        DbInterface.getInstance().query(
            "INSERT OR REPLACE INTO prepaid_contracts VALUES (" +
                "'" + contract.getId() + "'," +
                "'" + contract.getTariff().getId() + "'," +
                contract.getBalance() +
            ");"
        );
    }
    public static PrePaidContract get(String id) throws SQLException {
        if(cache.containsKey(id)){
            return cache.get(id);
        }
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT * FROM prepaid_contracts JOIN contracts ON prepaid_contracts.id = contracts.id WHERE id = '" + id + "';"
        );
        if(resultSet.next()) {
            PrePaidContract prePaidContract = new PrePaidContract.Builder()
                .setId(UUID.fromString(resultSet.getString("id")))
                .setPhoneNumber(resultSet.getString("phone_number"))
                .setAccount(AccountTable.get(resultSet.getString("account_id")))
                .setEndDate(resultSet.getDate("end_date"))
                .setTariffVersion(PrePaidTariffVersionTable.get(resultSet.getString("tariff_id")))
                .setBalance(resultSet.getDouble("balance"))
                .get();
            cache.put(String.valueOf(prePaidContract.getId()), prePaidContract);
            return prePaidContract;
        }
        return null;
    }

    public static Set<PrePaidContract> getFor(Account account) throws SQLException {
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT id, account_id FROM prepaid_contracts WHERE account_id = " + account.getId() + ";"
        );
        Set<PrePaidContract> prePaidContracts = new java.util.HashSet<>();
        while (resultSet.next()) {
            prePaidContracts.add(get(resultSet.getString("id")));
        }
        return prePaidContracts;
    }
}
