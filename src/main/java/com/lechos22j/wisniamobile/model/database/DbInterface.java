package com.lechos22j.wisniamobile.model.database;

import org.sqlite.JDBC;
import org.sqlite.SQLiteConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbInterface {
    private static DbInterface instance;
    private SQLiteConnection connection;
    private DbInterface() throws SQLException {
        connection = JDBC.createConnection("jdbc:sqlite:./src/main/resources/main.db", new Properties());
    }
    public static DbInterface getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbInterface();
        }
        return instance;
    }
    public static void close() throws SQLException {
        closeAllTables();
        if (instance != null) {
            instance.connection.close();
        }
        instance = null;
    }

    public ResultSet query(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
        return statement.getResultSet();
    }
    public static void createAllTables() throws SQLException {
        CustomerTable.create();
        TariffTable.create();
        TariffVersionTable.create();
        PrePaidTariffVersionTable.create();
        PostPaidTariffVersionTable.create();
        AccountTable.create();
        ContractTable.create();
        PrePaidContractTable.create();
        PostPaidContractTable.create();
    }
    public static void closeAllTables() throws SQLException {
        CustomerTable.close();
        TariffTable.close();
        TariffVersionTable.close();
        PrePaidTariffVersionTable.close();
        PostPaidTariffVersionTable.close();
        AccountTable.close();
        ContractTable.close();
        PrePaidContractTable.close();
        PostPaidContractTable.close();
    }
}
