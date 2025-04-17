package Final.service;

import Final.model.Servico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoService {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=SeuBanco";
    private static final String USUARIO = "seuUsuario";
    private static final String SENHA = "suaSenha";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    // Cadastrar um serviço
    public void cadastrar(Servico servico) {
        String sql = "INSERT INTO Servicos (descricao, data, valor, placaVeiculo, cpfCliente) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servico.getDescricao());

            // Garantindo que a data está sendo formatada corretamente
            if (servico.getData() != null) {
                stmt.setDate(2, Date.valueOf(servico.getData()));  // Usando LocalDate diretamente
            } else {
                // Se a data for nula, você pode definir como NULL no banco de dados
                stmt.setNull(2, Types.DATE);
            }

            stmt.setDouble(3, servico.getValor());
            stmt.setString(4, servico.getPlacaVeiculo());
            stmt.setString(5, servico.getCpfCliente());

            stmt.executeUpdate();
            System.out.println("Serviço cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar serviço: " + e.getMessage());
        }
    }

    // Listar todos os serviços
    public List<Servico> listarTodos() {
        List<Servico> lista = new ArrayList<>();
        String sql = "SELECT * FROM Servicos";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Servico servico = new Servico();
                servico.setId(rs.getInt("id"));
                servico.setDescricao(rs.getString("descricao"));

                // Convertendo a data para LocalDate ao buscar do banco
                servico.setData(rs.getDate("data").toLocalDate());
                servico.setValor(rs.getDouble("valor"));
                servico.setPlacaVeiculo(rs.getString("placaVeiculo"));
                servico.setCpfCliente(rs.getString("cpfCliente"));

                lista.add(servico);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar serviços: " + e.getMessage());
        }

        return lista;
    }
}
