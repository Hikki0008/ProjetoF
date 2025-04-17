package Final.model;


import java.time.LocalDate;
public class Venda {

    private int id;
    private String placaVeiculo;
    private String cpfCliente;
    private LocalDate dataVenda;
    private double valorVenda;

    public Venda(int id, String placaVeiculo, String cpfCliente, LocalDate dataVenda, double valorVenda) {
        this.id = id;
        this.placaVeiculo = placaVeiculo;
        this.cpfCliente = cpfCliente;
        this.dataVenda = dataVenda;
        this.valorVenda = valorVenda;
    }

    public Venda() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
}