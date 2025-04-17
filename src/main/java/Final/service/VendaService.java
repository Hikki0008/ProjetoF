package Final.service;

import Final.model.Venda;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class VendaService {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=SeuBanco";
    private static final String USUARIO = "seuUsuario";
    private static final String SENHA = "suaSenha";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public void cadastrar(Venda venda) {
        String sql = "INSERT INTO Vendas (placaVeiculo, cpfCliente, dataVenda, valorVenda) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, venda.getPlacaVeiculo());
            stmt.setString(2, venda.getCpfCliente());
            stmt.setDate(3, Date.valueOf(venda.getDataVenda()));
            stmt.setDouble(4, venda.getValorVenda());

            stmt.executeUpdate();
            System.out.println("Venda registrada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
        }
    }

    public List<Venda> listarTodos() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM Vendas";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id"));
                venda.setPlacaVeiculo(rs.getString("placaVeiculo"));
                venda.setCpfCliente(rs.getString("cpfCliente"));
                venda.setDataVenda(rs.getDate("dataVenda").toLocalDate());
                venda.setValorVenda(rs.getDouble("valorVenda"));

                vendas.add(venda);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar vendas: " + e.getMessage());
        }

        return vendas;
    }
}
