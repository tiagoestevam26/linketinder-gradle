package database

import models.Competence;
import java.sql.*;

class CompetenceDAO{

    private static final CompetenceDAO instance = new CompetenceDAO()

    private CandidateDAO() {} // Construtor privado

    static CompetenceDAO getInstance() {
        return instance
    }

    void salvar(Competence competence) throws SQLException {
        String sql = "INSERT INTO competencias (nome) VALUES (?) ON CONFLICT (nome) DO NOTHING";
        try (Connection connection = DatabaseConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, competence.getName());
            stmt.executeUpdate();

        }
    }

    List<Competence> listarTodos() throws SQLException {
        List<Competence> competences = new ArrayList<>();
        String sql = "SELECT * FROM competencias";
        try (Connection connection = DatabaseConnectionFactory.getConnection();
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
        try (Connection connection = DatabaseConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        throw new SQLException("Competência não encontrada para o nome: " + name);
    }

    void deletar(int id) throws SQLException {
        String sql = "DELETE FROM competencias WHERE id = ?";
        try (Connection connection = DatabaseConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nenhuma competência foi deletada. ID pode estar incorreto.");
            }
        }
    }

}
