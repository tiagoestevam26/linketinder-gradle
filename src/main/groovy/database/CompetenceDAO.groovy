package database

import models.Competence;
import database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class CompetenceDAO {

    static void salvar(Competence competence) throws SQLException {
        String sql = "INSERT INTO competencias (nome) VALUES (?) ON CONFLICT (nome) DO NOTHING";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, competence.getName());
            stmt.executeUpdate();

        }
    }

    static List<Competence> listarTodas() throws SQLException {
        List<Competence> competences = new ArrayList<>();
        String sql = "SELECT * FROM competencias";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                competences.add(new Competence(
                        rs.getString("nome")
                ));
            }
        }
        return competences;
    }

    static int findIdByName(String name) throws SQLException {
        String sql = "SELECT id FROM competencias WHERE nome = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id"); // Retorna o ID da competência
            }
        }
        throw new SQLException("Competência não encontrada para o nome: " + name);
    }

    static void editar(int id, String novoNome) throws SQLException {
        String sql = "UPDATE competencias SET nome = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setInt(2, id);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nenhuma competência foi atualizada. ID pode estar incorreto.");
            }
        }
    }

    static void deletar(int id) throws SQLException {
        String sql = "DELETE FROM competencias WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nenhuma competência foi deletada. ID pode estar incorreto.");
            }
        }
    }

}
