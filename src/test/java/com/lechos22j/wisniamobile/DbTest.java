package com.lechos22j.wisniamobile;

import com.lechos22j.wisniamobile.model.account.Account;
import com.lechos22j.wisniamobile.model.contract.PostPaidContract;
import com.lechos22j.wisniamobile.model.customer.PersonalCustomer;
import com.lechos22j.wisniamobile.model.database.*;
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
        DbInterface.createAllTables();
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
        PersonalCustomer customer = new PersonalCustomer.Builder()
            .setName("(RODO)")
            .setSurname("(RODO)")
            .setPesel("(RODO)")
            .setAddress("ul. (RODO)")
            .setPhone("(RODO)")
            .setEmail("(RODO)@(RODO).pl")
            .get();
        Account account = new Account
            .Builder()
            .setCustomer(customer)
            .genBillingDay()
            .get();
        customer.addAccount(account);
        PostPaidContract postPaidContract = new PostPaidContract.Builder()
            .setPhoneNumber("(RODO 2)")
            .setAccount(account)
            .setEndDate(tariffVersion.getEndDate())
            .setTariffVersion(tariffVersion)
            .applyTariffVersion()
            .get();
        account.addContract(postPaidContract);

        CustomerTable.add(customer);

        var debug = AccountTable.getAll();

        DbInterface.close();
    }
}
