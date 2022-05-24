package com.lechos22j.wisniamobile.database;

import com.lechos22j.wisniamobile.extraservices.Service;
import com.lechos22j.wisniamobile.tariff.TariffVersion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ServiceTable {
    private static final Map<String, Service> cache = new HashMap<>();
    public static void create() throws SQLException {
        DbInterface.getInstance().query(
            "CREATE TABLE IF NOT EXISTS services (" +
                "id TEXT PRIMARY KEY," +
                "tariff_version_id TEXT NOT NULL," +
                "name TEXT," +
                "description TEXT," +
                "price REAL" +
            ");");
    }
    public static void add(Service service) throws SQLException {
        cache.put(service.getId().toString(), service);
        DbInterface.getInstance().query(
            "INSERT OR REPLACE INTO services VALUES (" +
                "'" + service.getId() + "'," +
                "'" + service.getTariffVersion().getId() + "'," +
                "'" + service.getName() + "'," +
                "'" + service.getDescription() + "'," +
                service.getPrice() +
            ");"
        );
    }
    public static Service get(String id) throws SQLException {
        if(cache.containsKey(id))
            return cache.get(id);
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT * FROM services WHERE id = '" + id + "';"
        );
        if(!resultSet.next())
            return null;
        Service service = new Service.Builder()
            .setId(UUID.fromString(resultSet.getString("id")))
            .setName(resultSet.getString("name"))
            .setDescription(resultSet.getString("description"))
            .setPrice(resultSet.getDouble("price"))
            .get()
        ;
        cache.put(id, service);
        return service;
    }
    public static Set<Service> getFor(TariffVersion tariffVersion) throws SQLException {
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT * FROM services WHERE tariff_version_id = '" + tariffVersion.getId() + "';"
        );
        Set<Service> services = new HashSet<>();
        while(resultSet.next()) {
            services.add(get(resultSet.getString("id")));
        }
        return services;
    }
    public static Set<Service> getAll() throws SQLException {
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT * FROM services;"
        );
        Set<Service> services = new HashSet<>();
        while(resultSet.next()) {
            services.add(get(resultSet.getString("id")));
        }
        return services;
    }
}
