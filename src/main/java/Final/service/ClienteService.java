package Final.service;

import Final.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private static final String URL = "jdbc:sqlserver://localhost:1433;database=Final;";
    private static final String USER = "sa";
    private static final String PASS = "sa";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public void cadastrar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, telefone) VALUES (?, ?, ?)";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1,cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());

            stmt.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");

        }catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Clientes";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }

        return clientes;
    }


}