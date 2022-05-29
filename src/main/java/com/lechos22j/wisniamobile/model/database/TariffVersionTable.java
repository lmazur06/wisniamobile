package com.lechos22j.wisniamobile.model.database;

import com.lechos22j.wisniamobile.model.extraservices.ExtraService;
import com.lechos22j.wisniamobile.model.tariff.PostPaidTariffVersion;
import com.lechos22j.wisniamobile.model.tariff.PrePaidTariffVersion;
import com.lechos22j.wisniamobile.model.tariff.Tariff;
import com.lechos22j.wisniamobile.model.tariff.TariffVersion;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TariffVersionTable {
    public static void create() throws SQLException {
        DbInterface.getInstance().query("CREATE TABLE IF NOT EXISTS tariff_versions (" +
            "id TEXT PRIMARY KEY," +
            "tariff_id TEXT NOT NULL," +
            "end_date DATE" +
            ");"
        );
    }

    public static void add(TariffVersion version) throws SQLException {
        DbInterface.getInstance().query("INSERT OR REPLACE INTO tariff_versions VALUES ('"
            + version.getId() + "','"
            + version.getTariff().getId() + "',"
            + version.getEndDate().getTime() + ");");
        if(version instanceof PrePaidTariffVersion v)
            PrePaidTariffVersionTable.add(v);
        else if(version instanceof PostPaidTariffVersion v)
            PostPaidTariffVersionTable.add(v);
        for(ExtraService s : version.getServices())
            ExtraServiceTable.add(s);
    }

    public static Set<TariffVersion> getFor(Tariff tariff) throws SQLException {
        Set<TariffVersion> versions = new HashSet<>();
        versions.addAll(PostPaidTariffVersionTable.getFor(tariff));
        versions.addAll(PrePaidTariffVersionTable.getFor(tariff));
        return versions;
    }

    public static Set<TariffVersion> getAll() throws SQLException {
        Set<TariffVersion> versions = new HashSet<>();
        versions.addAll(PostPaidTariffVersionTable.getAll());
        versions.addAll(PrePaidTariffVersionTable.getAll());
        return versions;
    }

    public static TariffVersion get(String last_version) throws SQLException {
        TariffVersion result = PostPaidTariffVersionTable.get(last_version);
        if (result == null)
            result = PrePaidTariffVersionTable.get(last_version);
        return result;
    }

    public static void close(){
    }
}
