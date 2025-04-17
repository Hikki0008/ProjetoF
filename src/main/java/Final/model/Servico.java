package Final.model;
import java.time.LocalDate;
public class Servico {

    private int id;
    private String descricao;
    private LocalDate data;
    private double valor;
    private String placaVeiculo;  // relacionamento com Veículo
    private String cpfCliente;    // relacionamento com Cliente
    public Servico() {}

    public Servico(int id, String descricao, LocalDate data, double valor, String placaVeiculo, String cpfCliente) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.placaVeiculo = placaVeiculo;
        this.cpfCliente = cpfCliente;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
}