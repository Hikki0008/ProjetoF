package Final.controller;

import Final.service.VeiculoService;
import Final.model.Veiculo;

import java.util.List;
import java.util.Scanner;
public class VeiculoController {

    private final VeiculoService veiculoService;
    private final Scanner scanner;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== MENU VEÍCULOS =====");
            System.out.println("1 - Cadastrar Veículo");
            System.out.println("2 - Listar Veículos");
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
                case 0 -> System.out.println("Saindo do menu de veículos.");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarVeiculo() {
        System.out.println("\n--- Cadastro de Veículo ---");

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());

        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        Veiculo veiculo = new Veiculo(modelo, ano, placa, tipo);


        veiculoService.cadastrar(veiculo);
    }


    private void listarVeiculos() {
        System.out.println("\n--- Lista de Veículos ---");

        List<Veiculo> veiculos = veiculoService.listarTodos();

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            for (Veiculo veiculo : veiculos) {
                System.out.println("Modelo: " + veiculo.getModelo());
                System.out.println("Ano: " + veiculo.getAno());
                System.out.println("Placa: " + veiculo.getPlaca());
                System.out.println("Tipo: " + veiculo.getTipo());
                System.out.println("-----------------------------");
            }
        }
    }
}

