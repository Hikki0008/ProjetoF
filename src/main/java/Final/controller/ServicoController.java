package Final.controller;

import Final.model.Servico;
import Final.service.ServicoService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
public class ServicoController {

    private final ServicoService servicoService;
    private final Scanner scanner;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== MENU SERVIÇOS =====");
            System.out.println("1 - Cadastrar Serviço");
            System.out.println("2 - Listar Serviços");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
                continue;
            }

            switch (opcao) {
                case 1 -> cadastrarServico();
                case 2 -> listarServicos();
                case 0 -> System.out.println("Voltando ao menu anterior.");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarServico() {
        try {
            System.out.println("\n--- Cadastro de Serviço ---");

            System.out.print("Descrição do serviço: ");
            String descricao = scanner.nextLine();

            System.out.print("Data (AAAA-MM-DD): ");
            LocalDate data = LocalDate.parse(scanner.nextLine());

            System.out.print("Valor: ");
            double valor = Double.parseDouble(scanner.nextLine());

            System.out.print("Placa do veículo: ");
            String placa = scanner.nextLine();

            System.out.print("CPF do cliente: ");
            String cpf = scanner.nextLine();

            Servico servico = new Servico();
            servico.setDescricao(descricao);
            servico.setData(data);
            servico.setValor(valor);
            servico.setPlacaVeiculo(placa);
            servico.setCpfCliente(cpf);

            servicoService.cadastrar(servico);

        } catch (NumberFormatException e) {
            System.out.println("Erro: valor inválido.");
        } catch (DateTimeParseException e) {
            System.out.println("Erro: data em formato inválido.");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar serviço: " + e.getMessage());
        }
    }

    private void listarServicos() {
        System.out.println("\n--- Lista de Serviços ---");
        List<Servico> lista = servicoService.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
        } else {
            for (Servico servico : lista) {
                System.out.println("ID: " + servico.getId());
                System.out.println("Descrição: " + servico.getDescricao());
                System.out.println("Data: " + servico.getData());
                System.out.println("Valor: R$" + servico.getValor());
                System.out.println("Placa do Veículo: " + servico.getPlacaVeiculo());
                System.out.println("CPF do Cliente: " + servico.getCpfCliente());
                System.out.println("-------------------------------");
            }
        }
    }
}