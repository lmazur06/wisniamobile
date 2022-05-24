package com.lechos22j.wisniamobile.database;

import com.lechos22j.wisniamobile.tariff.PostPaidTariffVersion;
import com.lechos22j.wisniamobile.tariff.PrePaidTariffVersion;
import com.lechos22j.wisniamobile.tariff.Tariff;
import com.lechos22j.wisniamobile.tariff.TariffVersion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TariffTable {
    private static final Map<String, Tariff> cache = new HashMap<>();
    public static void create() throws SQLException {
        DbInterface.getInstance().query("CREATE TABLE IF NOT EXISTS tariffs (" +
            "id TEXT PRIMARY KEY," +
            "name TEXT," +
            "last_version TEXT" +
            ");"
        );
        TariffVersionTable.create();
    }
    public static void add(Tariff tariff) throws SQLException {
        cache.put(tariff.getId().toString(), tariff);
        DbInterface.getInstance().query("INSERT OR REPLACE INTO tariffs (id, name, last_version) VALUES ('"
            + tariff.getId() + "', '"
            + tariff.getName() + "', '"
            + tariff.getLastVersion().getId() + "');");
        for(TariffVersion tariffVersion : tariff.getVersions())
            TariffVersionTable.add(tariffVersion);
    }
    public static Tariff get(String id) throws SQLException {
        if(cache.containsKey(id))
            return cache.get(id);
        ResultSet resultSet = DbInterface.getInstance().query("SELECT * FROM tariffs WHERE id = '" + id + "';");
        if(!resultSet.next())
            return null;
        Tariff tariff = new Tariff.Builder()
            .setId(UUID.fromString(id))
            .setName(resultSet.getString("name"))
            .get();
        tariff.setLastVersion(TariffVersionTable.get(resultSet.getString("last_version")));
        tariff.setVersions(TariffVersionTable.getFor(tariff));
        tariff.getVersions().forEach(tariffVersion -> tariffVersion.setTariff(tariff));
        cache.put(id, tariff);
        return tariff;
    }
    public static Set<Tariff> getAll() throws SQLException {
        ResultSet resultSet = DbInterface.getInstance().query("SELECT * FROM tariffs;");
        Set<Tariff> tariffs = new HashSet<>();
        while(resultSet.next()) {
            tariffs.add(get(resultSet.getString("id")));
        }
        return tariffs;
    }
}
