package com.lechos22j.wisniamobile.model.database;

import com.lechos22j.wisniamobile.model.tariff.PostPaidTariffVersion;
import com.lechos22j.wisniamobile.model.tariff.Tariff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PostPaidTariffVersionTable {
    private static final Map<String, PostPaidTariffVersion> cache = new HashMap<>();
    public static void create() throws SQLException {
        DbInterface.getInstance().query("CREATE TABLE IF NOT EXISTS postpaid_tariff_versions (" +
            "id TEXT PRIMARY KEY," +
            "monthly_fee REAL," +
            "call_minutes REAL," +
            "number_of_sms INT," +
            "number_of_mms INT," +
            "data_volume REAL," +
            "call_fee REAL," +
            "sms_fee REAL," +
            "mms_fee REAL," +
            "data_transfer_fee REAL" +
            ");"
        );
    }
    public static void add(PostPaidTariffVersion version) throws SQLException {
        cache.put(version.getId().toString(), version);
        DbInterface.getInstance().query("INSERT OR REPLACE INTO postpaid_tariff_versions VALUES (" +
            "'" + version.getId() + "'," +
            version.getMonthlyFee() + "," +
            version.getCallMinutes() + "," +
            version.getNumberOfSms() + "," +
            version.getNumberOfMms() + "," +
            version.getDataVolume() + "," +
            version.getCallFee() + "," +
            version.getSmsFee() + "," +
            version.getMmsFee() + "," +
            version.getDataTransferFee() +
            ");"
        );
    }
    public static PostPaidTariffVersion get(String id) throws SQLException {
        if(cache.containsKey(id))
            return cache.get(id);
        ResultSet resultSet = DbInterface.getInstance().query("SELECT * FROM postpaid_tariff_versions JOIN tariff_versions ON postpaid_tariff_versions.id=tariff_versions.id WHERE tariff_versions.id = '" + id + "';");
        if(resultSet.next()) {
            PostPaidTariffVersion version = new PostPaidTariffVersion.Builder()
                .setId(UUID.fromString(id))
                .setEndDate(resultSet.getDate("end_date"))
                .setMonthlyFee(resultSet.getDouble("monthly_fee"))
                .setCallMinutes(resultSet.getDouble("call_minutes"))
                .setNumberOfSms(resultSet.getInt("number_of_sms"))
                .setNumberOfMms(resultSet.getInt("number_of_mms"))
                .setDataVolume(resultSet.getDouble("data_volume"))
                .setCallFee(resultSet.getDouble("call_fee"))
                .setSmsFee(resultSet.getDouble("sms_fee"))
                .setMmsFee(resultSet.getDouble("mms_fee"))
                .setDataTransferFee(resultSet.getDouble("data_transfer_fee"))
                .get();
            version.setServices(ExtraServiceTable.getFor(version));
            version.getServices().forEach(service -> service.setTariffVersion(version));
            cache.put(id, version);
            return version;
        }
        return null;
    }
    public static Set<PostPaidTariffVersion> getFor(Tariff tariff) throws SQLException {
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT * FROM tariff_versions INNER JOIN postpaid_tariff_versions ON tariff_versions.id=postpaid_tariff_versions.id WHERE tariff_id = '" + tariff.getId() + "';"
        );
        Set<PostPaidTariffVersion> tariffVersions = new HashSet<>();
        while(resultSet.next()) {
            tariffVersions.add(get(resultSet.getString("id")));
        }
        return tariffVersions;
    }
    public static Set<PostPaidTariffVersion> getAll() throws SQLException {
        ResultSet resultSet = DbInterface.getInstance().query("SELECT * FROM postpaid_tariff_versions INNER JOIN tariff_versions ON tariff_versions.id=postpaid_tariff_versions.id;");
        Set<PostPaidTariffVersion> versions = new HashSet<>();
        while(resultSet.next()) {
            PostPaidTariffVersion tariffVersion = get(resultSet.getString("id"));
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
