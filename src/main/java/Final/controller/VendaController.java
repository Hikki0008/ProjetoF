package Final.controller;

import Final.model.Venda;
import Final.service.VendaService;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
public class VendaController {

    private final VendaService vendaService;
    private final Scanner scanner;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== MENU DE VENDAS =====");
            System.out.println("1 - Registrar Venda");
            System.out.println("2 - Listar Vendas");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
                continue;
            }

            switch (opcao) {
                case 1 -> cadastrarVenda();
                case 2 -> listarVendas();
                case 0 -> System.out.println("Voltando ao menu anterior.");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarVenda() {
        try {
            System.out.println("\n--- Registrar Venda ---");

            System.out.print("Placa do Veículo: ");
            String placa = scanner.nextLine();

            System.out.print("CPF do Cliente: ");
            String cpf = scanner.nextLine();

            System.out.print("Data da Venda (AAAA-MM-DD): ");
            LocalDate data = LocalDate.parse(scanner.nextLine());

            System.out.print("Valor da Venda: ");
            double valor = Double.parseDouble(scanner.nextLine());

            Venda venda = new Venda();
            venda.setPlacaVeiculo(placa);
            venda.setCpfCliente(cpf);
            venda.setDataVenda(data);
            venda.setValorVenda(valor);

            vendaService.cadastrar(venda);

        } catch (NumberFormatException e) {
            System.out.println("Erro: valor inválido.");
        } catch (DateTimeParseException e) {
            System.out.println("Erro: data em formato inválido.");
        } catch (Exception e) {
            System.out.println("Erro ao registrar venda: " + e.getMessage());
        }
    }

    private void listarVendas() {
        System.out.println("\n--- Lista de Vendas ---");
        List<Venda> vendas = vendaService.listarTodos();

        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (Venda venda : vendas) {
                System.out.println("ID: " + venda.getId());
                System.out.println("Placa do Veículo: " + venda.getPlacaVeiculo());
                System.out.println("CPF do Cliente: " + venda.getCpfCliente());
                System.out.println("Data da Venda: " + venda.getDataVenda());
                System.out.println("Valor: R$ " + venda.getValorVenda());
                System.out.println("-------------------------------");
            }
        }
    }
}
