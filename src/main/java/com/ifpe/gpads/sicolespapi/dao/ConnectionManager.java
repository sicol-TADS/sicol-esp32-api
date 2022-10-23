package com.ifpe.gpads.sicolespapi.dao;

import com.ifpe.gpads.sicolespapi.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = Config.get("POSTGRES_URL");
    private static final String USER = Config.get("POSTGRES_USER");
    private static final String PASSWORD = Config.get("POSTGRES_PASSWORD");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
