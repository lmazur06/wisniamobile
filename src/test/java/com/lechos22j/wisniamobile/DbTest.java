package com.lechos22j.wisniamobile;

import com.lechos22j.wisniamobile.database.DbInterface;
import com.lechos22j.wisniamobile.database.TariffTable;
import com.lechos22j.wisniamobile.database.TariffVersionTable;
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
        TariffVersionTable.create();
        PostPaidTariffVersion tariffVersion = new PostPaidTariffVersion
            .Builder()
            .setEndDate(Tools.offsetByMonths(new Date(), 12))
            .setMonthlyFee(100)
            .setCallMinutes(100)
            .setNumberOfSms(100)
            .setNumberOfMms(100)
            .setDataVolume(100)
            .setCallFee(100)
            .setSmsFee(100)
            .setMmsFee(100)
            .setDataTransferFee(100)
            .get();
        TariffVersionTable.add(tariffVersion);
        Tariff<PostPaidTariffVersion> tariff = new Tariff
            .Builder<PostPaidTariffVersion>()
            .setName("testowa_taryfa")
            .get();
        tariff.addVersion(tariffVersion);
        TariffTable.add(tariff);
        DbInterface.close();
    }
}
