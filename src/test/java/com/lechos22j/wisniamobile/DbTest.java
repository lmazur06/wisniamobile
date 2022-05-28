package com.lechos22j.wisniamobile;

import com.lechos22j.wisniamobile.model.database.DbInterface;
import com.lechos22j.wisniamobile.model.database.TariffTable;
import com.lechos22j.wisniamobile.model.database.TariffVersionTable;
import com.lechos22j.wisniamobile.model.extraservices.ExtraService;
import com.lechos22j.wisniamobile.model.tariff.PostPaidTariffVersion;
import com.lechos22j.wisniamobile.model.tariff.Tariff;
import com.lechos22j.utils.Tools;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

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
            .setEndDate(Tools.offsetByMonths(Date.valueOf(LocalDate.now()), 12))
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
        ExtraService extraService = new ExtraService
            .Builder()
            .setName("testowa usluga")
            .setDescription("opis")
            .setPrice(10)
            .setTariffVersion(tariffVersion)
            .get();
        tariffVersion.addService(extraService);
        tariff.addVersion(tariffVersion);
        TariffTable.add(tariff);
        var debug = TariffVersionTable.getAll();
        DbInterface.close();
    }
}
