package database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/linketinder"
    private static final String USER = "geek"
    private static final String PASSWORD = "senha"

    static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD)
        } catch (SQLException e) {
            e.printStackTrace()
            throw new RuntimeException("Erro ao conectar ao banco de dados")
        }
    }
}
