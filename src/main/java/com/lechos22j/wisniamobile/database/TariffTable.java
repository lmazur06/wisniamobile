package com.lechos22j.wisniamobile.database;

import com.lechos22j.wisniamobile.tariff.PostPaidTariffVersion;
import com.lechos22j.wisniamobile.tariff.PrePaidTariffVersion;
import com.lechos22j.wisniamobile.tariff.Tariff;

import java.sql.SQLException;

public class TariffTable {
    public static void create() throws SQLException {
        DbInterface.getInstance().query("CREATE TABLE IF NOT EXISTS tariffs (id TEXT PRIMARY KEY, name TEXT, last_version TEXT);");
    }
    public static void add(Tariff<?> tariff) throws SQLException {
        DbInterface.getInstance().query("INSERT OR REPLACE INTO tariffs (id, name, last_version) VALUES ('"
            + tariff.getId() + "', '"
            + tariff.getName() + "', '"
            + tariff.getLastVersion().getId() + "');");
        if(tariff.getLastVersion() instanceof PostPaidTariffVersion) {
            /*tariff.getVersions().forEach(version -> {
                try {
                    PostPaidTariffVersion.add((PostPaidTariffVersion) version);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });*/
        } else if(tariff.getLastVersion() instanceof PrePaidTariffVersion) {
            /*tariff.getVersions().forEach(version -> {
                try {
                    PrePaidTariffVersion.add((PostPaidTariffVersion) version);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });*/
        }
    }
}
