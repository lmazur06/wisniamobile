package com.lechos22j.wisniamobile;

import com.lechos22j.wisniamobile.contract.PostPaidContract;
import com.lechos22j.wisniamobile.customer.PersonalCustomer;
import com.lechos22j.wisniamobile.reckoning.Account;
import com.lechos22j.wisniamobile.reckoning.FactureBuilder;
import com.lechos22j.wisniamobile.tariff.PostPaidTariffVersion;
import com.lechos22j.wisniamobile.utils.Constants;
import com.lechos22j.wisniamobile.utils.Tools;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.sql.Date;

public class ModuleTest {
    @Test
    public void test() throws Exception {
        PostPaidTariffVersion tariff = new PostPaidTariffVersion
            .Builder()
            .setEndDate(Tools.offsetByMonths(Date.valueOf(LocalDate.now()), 12))
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
        PersonalCustomer customer = new PersonalCustomer
            .Builder()
            .setName("<RODO>")
            .setSurname("<RODO>")
            .setPesel("12345678901")
            .setAddress("Warszawa, ul. Wiśniowa 56")
            .setPhone("123456789")
            .setEmail("rodo@domain")
            .get();
        Account account = new Account
            .Builder()
            .setCustomer(customer)
            .genBillingDay()
            .get();
        customer.getAccounts().add(account);
        PostPaidContract contract = new PostPaidContract
            .Builder()
            .setAccount(account)
            .setTariffVersion(tariff)
            .setEndDate(Tools.offsetByMonths(Date.valueOf(LocalDate.now()), 6))
            .get();
        account.getContracts().add(contract);
        FactureBuilder factureBuilder = new FactureBuilder()
            .setAccount(account)
            .setCity(Constants.CITY);
        String facture = factureBuilder.getXml();
        System.out.println(facture);
    }
}
