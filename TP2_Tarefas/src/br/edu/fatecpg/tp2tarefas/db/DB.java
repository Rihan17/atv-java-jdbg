package br.edu.fatecpg.tp2tarefas.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    public static Connection connect() throws SQLException {
        try {

            Class.forName("org.postgresql.Driver");

            var jdbcUrl = "jdbc:postgresql://localhost:5432/db_fatec";
            var user = "fatec";
            var password = "fatec777";

            return DriverManager.getConnection(jdbcUrl, user, password);

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
