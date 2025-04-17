package Final.controller;

import Final.model.Cliente;
import Final.model.Veiculo;
import Final.service.ClienteService;
import Final.service.VeiculoService;


import java.util.List;
import java.util.Scanner;

public class ConsoleController {

    private final VeiculoService veiculoService;
    private final ClienteService clienteService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleController(VeiculoService veiculoService, ClienteService clienteService) {
        this.veiculoService = veiculoService;
        this.clienteService = clienteService;
    }

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Cadastrar Veículo");
            System.out.println("2 - Listar Veículos");
            System.out.println("3 - Listar Clientes");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
                continue;
            }

            switch (opcao) {
                case 1 -> cadastrarVeiculo();
                case 2 -> listarVeiculos();
                case 3 -> listarClientes();
                case 0 -> System.out.println("Encerrando o sistema.");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarVeiculo() {
        System.out.println("\n--- Cadastro de Veículo ---");

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        int ano = 0;
        while (ano <= 0) {
            System.out.print("Ano: ");
            try {
                ano = Integer.parseInt(scanner.nextLine());
                if (ano <= 0) {
                    System.out.println("Ano inválido, deve ser um número positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ano deve ser um número.");
            }
        }

        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        while (!placa.matches("[A-Z]{3}-\\d{4}")) {  // Verifica formato: XXX-1234
            System.out.print("Placa inválida. Formato esperado: XXX-1234: ");
            placa = scanner.nextLine();
        }

        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(modelo);
        veiculo.setAno(ano);
        veiculo.setPlaca(placa);
        veiculo.setTipo(tipo);

        veiculoService.cadastrar(veiculo);
        System.out.println("Veículo cadastrado com sucesso!");
    }

    private void listarVeiculos() {
        System.out.println("\n--- Lista de Veículos ---");
        List<Veiculo> lista = veiculoService.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            for (Veiculo veiculo : lista) {
                System.out.println("Modelo: " + veiculo.getModelo());
                System.out.println("Ano: " + veiculo.getAno());
                System.out.println("Placa: " + veiculo.getPlaca());
                System.out.println("Tipo: " + veiculo.getTipo());
                System.out.println("--------------------------");
            }
        }
    }

    private void listarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        List<Cliente> lista = clienteService.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : lista) {
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Email: " + cliente.getEmail());
                System.out.println("Telefone: " + cliente.getTelefone());
                System.out.println("--------------------------");
            }
        }
    }
}