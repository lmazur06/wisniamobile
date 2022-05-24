package com.lechos22j.wisniamobile.database;

import com.lechos22j.wisniamobile.extraservices.Service;
import com.lechos22j.wisniamobile.tariff.PostPaidTariffVersion;

import java.sql.SQLException;

public class PostPaidTariffVersionTable {
    public static void create() throws SQLException {
        DbInterface.getInstance().query("CREATE TABLE IF NOT EXISTS postpaid_tariff_versions (" +
            "id TEXT PRIMARY KEY," +
            "end_date DATE," +
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
        DbInterface.getInstance().query("INSERT OR REPLACE INTO postpaid_tariff_versions VALUES (" +
            "'" + version.getId() + "'," +
            "'" + version.getEndDate() + "'," +
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
}
