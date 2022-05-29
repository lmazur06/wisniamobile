package com.lechos22j.wisniamobile.model.database;

import com.lechos22j.wisniamobile.model.account.Account;
import com.lechos22j.wisniamobile.model.contract.PostPaidContract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PostPaidContractTable {
    private static Map<String, PostPaidContract> cache = new HashMap<>();
    public static void create() throws SQLException {
        DbInterface.getInstance().query(
            "CREATE TABLE IF NOT EXISTS postpaid_contracts (" +
                "id TEXT PRIMARY KEY," +
                "tariff_id TEXT," +
                "sms_left INTEGER," +
                "mms_left INTEGER," +
                "minutes_left REAL," +
                "data_left REAL," +
                "due REAL" +
            ");"
        );
    }

    public static void add(PostPaidContract postPaidContract) throws SQLException {
        cache.put(postPaidContract.getId().toString(), postPaidContract);
        DbInterface.getInstance().query(
            "INSERT OR REPLACE INTO postpaid_contracts VALUES (" +
                "'" + postPaidContract.getId() + "'," +
                "'" + postPaidContract.getTariff().getId() + "'," +
                postPaidContract.getSmsLeft() + "," +
                postPaidContract.getMmsLeft() + "," +
                postPaidContract.getMinutesLeft() + "," +
                postPaidContract.getDataLeft() + "," +
                postPaidContract.getDue() +
            ");"
        );
    }

    public static PostPaidContract get(String id) throws SQLException {
        if(cache.containsKey(id)){
            return cache.get(id);
        }
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT * FROM postpaid_contracts JOIN contracts ON postpaid_contracts.id = contracts.id WHERE id = '" + id + "';"
        );
        if(resultSet.next()) {
            return new PostPaidContract.Builder()
                .setId(UUID.fromString(resultSet.getString("id")))
                .setPhoneNumber(resultSet.getString("phone_number"))
                .setAccount(AccountTable.get(resultSet.getString("account_id")))
                .setEndDate(resultSet.getDate("end_date"))
                .setTariffVersion(PostPaidTariffVersionTable.get(resultSet.getString("tariff_id")))
                .setSmsLeft(resultSet.getInt("sms_left"))
                .setMmsLeft(resultSet.getInt("mms_left"))
                .setMinutesLeft(resultSet.getDouble("minutes_left"))
                .setDataLeft(resultSet.getDouble("data_left"))
                .setDue(resultSet.getDouble("due"))
                .get();
        }
        return null;
    }

    public static Set<PostPaidContract> getFor(Account account) throws SQLException {
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT id, account_id FROM postpaid_contracts WHERE account_id = " + account.getId() + ";"
        );
        Set<PostPaidContract> postPaidContracts = new java.util.HashSet<>();
        while (resultSet.next()) {
            postPaidContracts.add(get(resultSet.getString("id")));
        }
        return postPaidContracts;
    }
}
