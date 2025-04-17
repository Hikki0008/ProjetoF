package Final.service;

import Final.model.Veiculo;
import Final.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoService {

    // Cadastrar um veículo
    public void cadastrar(Veiculo veiculo) {
        String sql = "INSERT INTO Veiculos (modelo, ano, placa, tipo) VALUES (?, ?, ?, ?)";

        try (Connection connection = Conexao.abrir();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getModelo());
            stmt.setInt(2, veiculo.getAno());
            stmt.setString(3, veiculo.getPlaca());
            stmt.setString(4, veiculo.getTipo());

            stmt.executeUpdate();
            System.out.println("Veículo cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar veículo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao abrir conexão: " + e.getMessage());
        }
    }

    // Listar todos os veículos
    public List<Veiculo> listarTodos() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT modelo, ano, placa, tipo FROM Veiculos";

        try (Connection connection = Conexao.abrir();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String modelo = rs.getString("modelo");
                int ano = rs.getInt("ano");
                String placa = rs.getString("placa");
                String tipo = rs.getString("tipo");

                Veiculo veiculo = new Veiculo(modelo, ano, placa, tipo);
                veiculos.add(veiculo);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar veículos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao abrir conexão: " + e.getMessage());
        }

        return veiculos;
    }

    // Atualizar um veículo
    public void atualizarVeiculo(Veiculo veiculo, String placaAntiga) {
        String sql = "UPDATE Veiculos SET modelo = ?, ano = ?, placa = ?, tipo = ? WHERE placa = ?";

        try (Connection connection = Conexao.abrir();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getModelo());
            stmt.setInt(2, veiculo.getAno());
            stmt.setString(3, veiculo.getPlaca());
            stmt.setString(4, veiculo.getTipo());
            stmt.setString(5, placaAntiga);

            stmt.executeUpdate();
            System.out.println("Veículo atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar veículo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao abrir conexão: " + e.getMessage());
        }
    }
}


