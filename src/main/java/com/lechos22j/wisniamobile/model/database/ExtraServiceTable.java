package com.lechos22j.wisniamobile.model.database;

import com.lechos22j.wisniamobile.model.extraservices.ExtraService;
import com.lechos22j.wisniamobile.model.tariff.TariffVersion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ExtraServiceTable {
    private static final Map<String, ExtraService> cache = new HashMap<>();
    public static void create() throws SQLException {
        DbInterface.getInstance().query(
            "CREATE TABLE IF NOT EXISTS extra_services (" +
                "id TEXT PRIMARY KEY," +
                "tariff_version_id TEXT NOT NULL," +
                "name TEXT," +
                "description TEXT," +
                "price REAL" +
            ");");
    }
    public static void add(ExtraService extraService) throws SQLException {
        cache.put(extraService.getId().toString(), extraService);
        DbInterface.getInstance().query(
            "INSERT OR REPLACE INTO extra_services VALUES (" +
                "'" + extraService.getId() + "'," +
                "'" + extraService.getTariffVersion().getId() + "'," +
                "'" + extraService.getName() + "'," +
                "'" + extraService.getDescription() + "'," +
                extraService.getPrice() +
            ");"
        );
    }
    public static ExtraService get(String id) throws SQLException {
        if(cache.containsKey(id))
            return cache.get(id);
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT * FROM extra_services WHERE id = '" + id + "';"
        );
        if(!resultSet.next())
            return null;
        ExtraService extraService = new ExtraService.Builder()
            .setId(UUID.fromString(resultSet.getString("id")))
            .setName(resultSet.getString("name"))
            .setDescription(resultSet.getString("description"))
            .setPrice(resultSet.getDouble("price"))
            .get()
        ;
        cache.put(id, extraService);
        return extraService;
    }
    public static Set<ExtraService> getFor(TariffVersion tariffVersion) throws SQLException {
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT * FROM extra_services WHERE tariff_version_id = '" + tariffVersion.getId() + "';"
        );
        Set<ExtraService> extraServices = new HashSet<>();
        while(resultSet.next()) {
            extraServices.add(get(resultSet.getString("id")));
        }
        return extraServices;
    }
    public static Set<ExtraService> getAll() throws SQLException {
        ResultSet resultSet = DbInterface.getInstance().query(
            "SELECT * FROM extra_services;"
        );
        Set<ExtraService> extraServices = new HashSet<>();
        while(resultSet.next()) {
            extraServices.add(get(resultSet.getString("id")));
        }
        return extraServices;
    }
    public static void close(){
        cache.clear();
    }
}
