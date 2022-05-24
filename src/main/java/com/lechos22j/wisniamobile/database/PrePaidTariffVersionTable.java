package com.lechos22j.wisniamobile.database;

import com.lechos22j.wisniamobile.tariff.PrePaidTariffVersion;

import java.sql.SQLException;

public class PrePaidTariffVersionTable {
    // TODO: implement
    public static void create() throws SQLException {
        DbInterface.getInstance().query(
            "CREATE TABLE IF NOT EXISTS prepaid_tariff_versions (" +
                "id TEXT PRIMARY KEY," +
                "call_price REAL," +
                "sms_price REAL," +
                "mms_price REAL," +
                "data_transfer_price REAL" +
            ");"
        );
    }

    public static void add(PrePaidTariffVersion version) throws SQLException{
        DbInterface.getInstance().query(
            "INSERT OR REPLACE INTO prepaid_tariff_versions VALUES (" +
                "'" + version.getId() + "'," +
                version.getCallPrice() + "," +
                version.getSmsPrice() + "," +
                version.getMmsPrice() + "," +
                version.getDataTransferPrice() +
            ");"
        );
    }
}
