package database


import models.Candidate
import java.sql.*

class CandidateDAO{

    private static final CandidateDAO instance = new CandidateDAO()

    private CandidateDAO() {} // Construtor privado

    static CandidateDAO getInstance() {
        return instance
    }

    void salvar(Candidate candidato) {
        Connection conn = DatabaseConnectionFactory.getConnection()
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

    List<Candidate> listarTodos() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection connection = DatabaseConnectionFactory.getConnection();
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

    void deletar(String cpf) {
        String sql = "DELETE FROM candidatos WHERE cpf = ?";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
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
    Candidate buscarPorCPF(String cpf) {
        String sql = "SELECT * FROM candidatos WHERE cpf = ?";
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Candidate(
                        rs.getString("nome"),
                        rs.getDate("data_nascimento"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getString("descricao"),
                        rs.getString("senha")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}


