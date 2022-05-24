package com.lechos22j.wisniamobile;

import com.lechos22j.wisniamobile.database.DbInterface;
import com.lechos22j.wisniamobile.database.ServiceTable;
import com.lechos22j.wisniamobile.database.TariffTable;
import com.lechos22j.wisniamobile.database.TariffVersionTable;
import com.lechos22j.wisniamobile.extraservices.Service;
import com.lechos22j.wisniamobile.tariff.PostPaidTariffVersion;
import com.lechos22j.wisniamobile.tariff.Tariff;
import com.lechos22j.wisniamobile.tariff.TariffVersion;
import com.lechos22j.wisniamobile.utils.Tools;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DbTest {
    @Test
    public void test() throws Exception {
        TariffTable.create();
        Tariff tariff = new Tariff
            .Builder()
            .setName("testowa taryfa")
            .get();
        PostPaidTariffVersion tariffVersion = new PostPaidTariffVersion
            .Builder()
            .setEndDate(Tools.offsetByMonths(new Date(), 12))
            .setMonthlyFee(100)
            .setCallMinutes(120)
            .setNumberOfSms(50)
            .setNumberOfMms(50)
            .setDataVolume(10)
            .setCallFee(0.5)
            .setSmsFee(0.5)
            .setMmsFee(1)
            .setDataTransferFee(100)
            .setTariff(tariff)
            .get();
        Service service = new Service
            .Builder()
            .setName("testowa usluga")
            .setDescription("opis")
            .setPrice(10)
            .setTariffVersion(tariffVersion)
            .get();
        tariffVersion.addService(service);
        tariff.addVersion(tariffVersion);
        TariffTable.add(tariff);
        TariffTable.getAll();
        DbInterface.close();
    }
}
