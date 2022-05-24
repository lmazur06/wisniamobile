package com.lechos22j.wisniamobile.database;

import com.lechos22j.wisniamobile.extraservices.Service;

import java.sql.SQLException;

public class ServiceTable {
    public static void create() throws SQLException {
        DbInterface.getInstance().query(
            "CREATE TABLE IF NOT EXISTS services (" +
                "id TEXT PRIMARY KEY," +
                "name TEXT," +
                "description TEXT," +
                "price REAL" +
            ");");
    }
    public static void add(Service service) throws SQLException {
        DbInterface.getInstance().query(
            "INSERT OR REPLACE INTO services VALUES (" +
                "'" + service.getId() + "'," +
                "'" + service.getName() + "'," +
                "'" + service.getDescription() + "'," +
                service.getPrice() +
            ");"
        );
    }
}
