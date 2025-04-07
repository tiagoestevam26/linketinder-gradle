package database

import models.Job

import java.sql.*

class JobDAO {

    static void salvar(Job job, int empresaId) throws SQLException {
        String sql = "INSERT INTO vagas (empresa_id, nome, descricao, local) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, empresaId);
            stmt.setString(2, job.getTitle());
            stmt.setString(3, job.getDescription());
            stmt.setString(4, job.getLocation());
            stmt.executeUpdate();
        }
    }

    static List<Job> listarTodos() throws SQLException {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM vagas";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                jobs.add(new Job(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("local"),
                        BusinessDAO.findById(rs.getInt("empresa_id"))
                ));
            }
        }
        return jobs;
    }

    static int findIdByName(String jobName) throws SQLException {
        String sql = "SELECT id FROM vagas WHERE nome = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, jobName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        throw new SQLException("Vaga não encontrada para o nome: " + jobName);
    }

    static void deletar(int id) throws SQLException {
        String sql = "DELETE FROM vagas WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nenhuma vaga foi deletada. ID pode estar incorreto.");
            }
        }
    }

}
