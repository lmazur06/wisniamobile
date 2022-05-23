package com.lechos22j.wisniamobile;

import com.lechos22j.wisniamobile.contract.PostPaidContract;
import com.lechos22j.wisniamobile.customer.PersonalCustomer;
import com.lechos22j.wisniamobile.reckoning.Account;
import com.lechos22j.wisniamobile.reckoning.FactureBuilder;
import com.lechos22j.wisniamobile.tariff.PostPaidTariffVersion;
import com.lechos22j.wisniamobile.utils.Tools;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamWriter;
import java.util.Date;

public class ModuleTest {
    @Test
    public void test() {
        // Main.main(new String[]{"."});
        PostPaidTariffVersion tariff = new PostPaidTariffVersion
            .Builder()
            .setEndDate(Tools.offsetByMonths(new Date(), 12))
            .setMonthlyFee(100)
            .setNumberOfCalls(100)
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
            .setEndDate(Tools.offsetByMonths(new Date(), 6))
            .get();
        account.getContracts().add(contract);
        String facture = new FactureBuilder()
            .setAccount(account)
            .setCity("Warszawa")
            .getXml();
        System.out.println(facture);
    }
}
