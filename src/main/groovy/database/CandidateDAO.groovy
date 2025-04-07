package database


import models.Candidate
import java.sql.*

class CandidateDAO {

    static void salvar(Candidate candidato) {
        Connection conn = DatabaseConnection.getConnection()
        String sql = "INSERT INTO candidatos (nome, data_nascimento, email, cpf, pais, cep, descricao, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
        PreparedStatement stmt = conn.prepareStatement(sql)

        stmt.setString(1, candidato.getName());
        stmt.setDate(2, new Date(candidato.getBornDate().getTime()));
        stmt.setString(3, candidato.getEmail());
        stmt.setString(4, candidato.getCPF());
        stmt.setString(5, candidato.getCountry());
        stmt.setString(6, candidato.getCep());
        stmt.setString(7, candidato.getDescription());
        stmt.setString(8, candidato.getPassword());

        stmt.executeUpdate()
        stmt.close()
        conn.close()
    }

    static List<Candidate> listarTodos() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM candidatos")) {
            while (rs.next()) {
                candidates.add(new Candidate(
                        rs.getString("nome"),
                        rs.getDate("data_nascimento"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getString("descricao"),
                        rs.getString("senha")
                ));
            }
        }
        return candidates;
    }

    static void deletar(String cpf) {
        String sql = "DELETE FROM candidatos WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Nenhum candidato foi deletado. CPF pode estar incorreto.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
