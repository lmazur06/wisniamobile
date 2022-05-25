package com.lechos22j.wisniamobile.database;

import com.lechos22j.wisniamobile.extraservices.Service;
import com.lechos22j.wisniamobile.tariff.PostPaidTariffVersion;
import com.lechos22j.wisniamobile.tariff.PrePaidTariffVersion;
import com.lechos22j.wisniamobile.tariff.Tariff;
import com.lechos22j.wisniamobile.tariff.TariffVersion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PrePaidTariffVersionTable {
    private static final Map<String, PrePaidTariffVersion> cache = new HashMap<>();
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

    public static PrePaidTariffVersion get(String id) throws SQLException {
        if(cache.containsKey(id))
            return cache.get(id);
        ResultSet result = DbInterface.getInstance().query(
            "SELECT * FROM prepaid_tariff_versions JOIN tariff_versions ON prepaid_tariff_versions.id=tariff_versions.id WHERE tariff_versions.id = '" + id + "';"
        );
        if(!result.next())
            return null;
        PrePaidTariffVersion version = new PrePaidTariffVersion.Builder()
            .setId(UUID.fromString(id))
            .setEndDate(result.getDate("end_date"))
            .setCallPrice(result.getDouble("call_price"))
            .setSmsPrice(result.getDouble("sms_price"))
            .setMmsPrice(result.getDouble("mms_price"))
            .setDataTransferPrice(result.getDouble("data_transfer_price"))
            .get();
        version.setServices(ServiceTable.getFor(version));
        version.getServices().forEach(service -> service.setTariffVersion(version));
        cache.put(id, version);
        return version;
    }
    public static Set<PrePaidTariffVersion> getFor(Tariff tariff) throws SQLException {
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT * FROM tariff_versions INNER JOIN prepaid_tariff_versions ON tariff_versions.id=prepaid_tariff_versions.id WHERE tariff_id = '" + tariff.getId() + "';"
        );
        Set<PrePaidTariffVersion> tariffVersions = new HashSet<>();
        while(resultSet.next()) {
            tariffVersions.add(get(resultSet.getString("id")));
        }
        return tariffVersions;
    }
    public static Set<PrePaidTariffVersion> getAll() throws SQLException {
        ResultSet resultSet = DbInterface.getInstance().query("SELECT * FROM prepaid_tariff_versions INNER JOIN tariff_versions ON tariff_versions.id=prepaid_tariff_versions.id;");
        Set<PrePaidTariffVersion> versions = new HashSet<>();
        while(resultSet.next()) {
            PrePaidTariffVersion tariffVersion = get(resultSet.getString("id"));
            if (tariffVersion == null) continue;
            versions.add(tariffVersion);
            tariffVersion.setTariff(TariffTable.get(resultSet.getString("tariff_id")));
        }
        return versions;
    }
    public static void close(){
        cache.clear();
    }
}
