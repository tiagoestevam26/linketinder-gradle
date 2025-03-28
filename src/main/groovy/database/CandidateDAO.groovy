package database

import database.DatabaseConnection
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

    static void editar(String cpf, Candidate candidatoAtualizado) {
        String sql = "UPDATE candidatos SET nome = ?, data_nascimento = ?, email = ?, pais = ?, cep = ?, descricao = ?, senha = ? WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, candidatoAtualizado.getName());
            stmt.setDate(2, new Date(candidatoAtualizado.getBornDate().getTime()));
            stmt.setString(3, candidatoAtualizado.getEmail());
            stmt.setString(4, candidatoAtualizado.getCountry());
            stmt.setString(5, candidatoAtualizado.getCep());
            stmt.setString(6, candidatoAtualizado.getDescription());
            stmt.setString(7, candidatoAtualizado.getPassword());
            stmt.setString(8, cpf);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Nenhum candidato foi atualizado. CPF pode estar incorreto.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
