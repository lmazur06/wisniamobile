package com.lechos22j.wisniamobile;

import com.lechos22j.wisniamobile.database.TariffTable;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbTest {
    @Test
    public void test() throws Exception {
        TariffTable.create();
    }
}
