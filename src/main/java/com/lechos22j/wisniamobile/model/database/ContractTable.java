package com.lechos22j.wisniamobile.model.database;

import com.lechos22j.wisniamobile.model.account.Account;
import com.lechos22j.wisniamobile.model.contract.Contract;
import com.lechos22j.wisniamobile.model.contract.PostPaidContract;
import com.lechos22j.wisniamobile.model.contract.PrePaidContract;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ContractTable {
    public static void create() throws SQLException {
        DbInterface.getInstance().query(
            "CREATE TABLE IF NOT EXISTS contracts (" +
                "id TEXT PRIMARY KEY," +
                "phone_number TEXT," +
                "account_id TEXT," +
                "end_date DATE" +
            ");"
        );
    }
    public static void add(Contract contract) throws SQLException {
        DbInterface.getInstance().query(
            "INSERT OR REPLACE INTO contracts VALUES (" +
                "'" + contract.getId() + "'," +
                "'" + contract.getPhoneNumber() + "'," +
                "'" + contract.getAccount().getId() + "'," +
                "'" + contract.getEndDate() + "'" +
            ");"
        );
        if(contract instanceof PrePaidContract prePaidContract){
            PrePaidContractTable.add(prePaidContract);
        } else if (contract instanceof PostPaidContract postPaidContract){
            PostPaidContractTable.add(postPaidContract);
        }
    }
    public static Set<Contract> getFor(Account account) throws SQLException {
        Set<Contract> contracts = new HashSet<>();
        contracts.addAll(PrePaidContractTable.getFor(account));
        contracts.addAll(PostPaidContractTable.getFor(account));
        return contracts;
    }
}
