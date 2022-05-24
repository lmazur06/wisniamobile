package com.lechos22j.wisniamobile.database;

import com.lechos22j.wisniamobile.tariff.PrePaidTariffVersion;

import java.sql.SQLException;

public class PrePaidTariffVersionTable {
    // TODO: implement
    public static void create() throws SQLException {
        DbInterface.getInstance().query("CREATE TABLE IF NOT EXISTS prepaid_tariff_versions (id TEXT PRIMARY KEY);");
    }

    public static void add(PrePaidTariffVersion v) throws SQLException{
        //
    }
}
