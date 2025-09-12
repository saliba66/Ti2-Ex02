package com.ti2cc.supermercado.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5432/mercado";
    private static final String USER = "ti2cc";     // <<< ajuste para seu usuário do PostgreSQL
    private static final String PASSWORD = "ti@cc"; // <<< ajuste para sua senha

    public static Connection getConnection() throws SQLException {
        // Para JDBC 4+ não precisa do Class.forName
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
