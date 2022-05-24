package com.lechos22j.wisniamobile.database;

import com.lechos22j.wisniamobile.extraservices.Service;
import com.lechos22j.wisniamobile.tariff.PostPaidTariffVersion;
import com.lechos22j.wisniamobile.tariff.PrePaidTariffVersion;
import com.lechos22j.wisniamobile.tariff.TariffVersion;

import java.sql.SQLException;

public class TariffVersionTable {
    public static void create() throws SQLException {
        DbInterface.getInstance().query("CREATE TABLE IF NOT EXISTS tariff_versions (" +
            "id TEXT PRIMARY KEY," +
            "end_date DATE" +
            ");"
        );
        PrePaidTariffVersionTable.create();
        PostPaidTariffVersionTable.create();
    }

    public static void add(TariffVersion version) throws SQLException {
        DbInterface.getInstance().query("INSERT OR REPLACE INTO tariff_versions (id, end_date) VALUES ('"
            + version.getId() + "', '"
            + version.getEndDate() + "');");
        if(version instanceof PrePaidTariffVersion v)
            PrePaidTariffVersionTable.add(v);
        else if(version instanceof PostPaidTariffVersion v)
            PostPaidTariffVersionTable.add(v);
        for(Service s : version.getServices())
            ServiceTable.add(s);
    }
}
