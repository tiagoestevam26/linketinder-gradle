package database

import models.Business

import java.sql.*

class BusinessDAO{

    private static final BusinessDAO instance = new BusinessDAO()

    private BusinessDAO() {} // Construtor privado

    static BusinessDAO getInstance() {
        return instance
    }

   void salvar(Business business) throws SQLException {
        try (Connection connection = DatabaseConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO empresas (nome, email, cnpj, pais, cep, descricao, senha) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, business.getName());
            stmt.setString(2, business.getEmail());
            stmt.setString(3, business.getCNPJ());
            stmt.setString(4, business.getCountry());
            stmt.setString(5, business.getCep());
            stmt.setString(6, business.getDescription());
            stmt.setString(7, business.getPassword());
            stmt.executeUpdate();
        }
    }

    List<Business> listarTodos() throws SQLException {
        List<Business> businesses = new ArrayList<>();
        try (Connection connection = DatabaseConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM empresas")) {
            while (rs.next()) {
                businesses.add(new Business(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getString("descricao"),
                        rs.getString("senha")
                ));
            }
        }
        return businesses;
    }

    static int findIdByCNPJ(String cnpj) throws SQLException {
        String sql = "SELECT id FROM empresas WHERE cnpj = ?";
        try (Connection connection = DatabaseConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cnpj);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        throw new SQLException("Empresa não encontrada para o CNPJ: " + cnpj);
    }

    static Business findById(int id) throws SQLException {
        String sql = "SELECT * FROM empresas WHERE id = ?";
        try (Connection connection = DatabaseConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Business(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getString("descricao"),
                        rs.getString("senha")
                );
            }
        }
        return null; // Retorna null caso não encontre
    }

    void deletar(String cnpj) throws SQLException {
        String sqlVagas = "DELETE FROM vagas WHERE empresa_id = (SELECT id FROM empresas WHERE cnpj = ?)";
        String sqlEmpresa = "DELETE FROM empresas WHERE cnpj = ?";

        try (Connection connection = DatabaseConnectionFactory.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement stmtVagas = connection.prepareStatement(sqlVagas)) {
                stmtVagas.setString(1, cnpj);
                stmtVagas.executeUpdate();
            }

            try (PreparedStatement stmtEmpresa = connection.prepareStatement(sqlEmpresa)) {
                stmtEmpresa.setString(1, cnpj);
                stmtEmpresa.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            throw e;
        }
    }

    Business buscarPorCNPJ(String cnpj) throws SQLException {
        String sql = "SELECT * FROM empresas WHERE cnpj = ?";
        try (Connection connection = DatabaseConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, cnpj);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Business(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cnpj"),
                        rs.getString("pais"),
                        rs.getString("cep"),
                        rs.getString("descricao"),
                        rs.getString("senha")
                );
            }
        }
        return null;
    }


}
