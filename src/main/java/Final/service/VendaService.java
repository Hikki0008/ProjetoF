package Final.service;

import Final.model.Venda;
import Final.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaService {

    public void cadastrar(Venda venda) {
        String sql = "INSERT INTO Vendas (placaVeiculo, cpfCliente, dataVenda, valorVenda) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.abrir(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, venda.getPlacaVeiculo());
            stmt.setString(2, venda.getCpfCliente());

            if (venda.getDataVenda() != null) {
                stmt.setDate(3, Date.valueOf(venda.getDataVenda()));
            } else {
                stmt.setNull(3, Types.DATE);
            }

            stmt.setDouble(4, venda.getValorVenda());

            stmt.executeUpdate();
            System.out.println("Venda registrada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao abrir conexão: " + e.getMessage());
        }
    }

    public List<Venda> listarTodos() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM Vendas";

        try (Connection conn = Conexao.abrir();
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
        } catch (Exception e) {
            System.out.println("Erro ao abrir conexão: " + e.getMessage());
        }

        return vendas;
    }
}

