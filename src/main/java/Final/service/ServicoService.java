package Final.service;

import Final.model.Servico;
import Final.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoService {

    // Cadastrar um serviço
    public void cadastrar(Servico servico) {
        String sql = "INSERT INTO Servicos (descricao, data, valor, placaVeiculo, cpfCliente) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.abrir(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servico.getDescricao());

            if (servico.getData() != null) {
                stmt.setDate(2, Date.valueOf(servico.getData()));  // usando LocalDate
            } else {
                stmt.setNull(2, Types.DATE);
            }

            stmt.setDouble(3, servico.getValor());
            stmt.setString(4, servico.getPlacaVeiculo());
            stmt.setString(5, servico.getCpfCliente());

            stmt.executeUpdate();
            System.out.println("Serviço cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar serviço: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao abrir conexão: " + e.getMessage());
        }
    }

    // Listar todos os serviços
    public List<Servico> listarTodos() {
        List<Servico> lista = new ArrayList<>();
        String sql = "SELECT * FROM Servicos";

        try (Connection conn = Conexao.abrir(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Servico servico = new Servico();
                servico.setId(rs.getInt("id"));
                servico.setDescricao(rs.getString("descricao"));
                servico.setData(rs.getDate("data").toLocalDate());
                servico.setValor(rs.getDouble("valor"));
                servico.setPlacaVeiculo(rs.getString("placaVeiculo"));
                servico.setCpfCliente(rs.getString("cpfCliente"));

                lista.add(servico);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar serviços: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao abrir conexão: " + e.getMessage());
        }

        return lista;
    }
}

