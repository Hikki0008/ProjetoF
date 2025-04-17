package Final.controller;

import Final.service.ClienteService;
import Final.service.ServicoService;
import Final.service.VeiculoService;
import Final.service.VendaService;

import java.util.Scanner;

public class AppController {

    private final Scanner scanner = new Scanner(System.in);

    private final ClienteController clienteController;
    private final VeiculoController veiculoController;
    private final ServicoController servicoController;
    private final VendaController vendaController;

    public AppController() {
        // Inicializa os services
        ClienteService clienteService = new ClienteService();
        VeiculoService veiculoService = new VeiculoService();
        ServicoService servicoService = new ServicoService();
        VendaService vendaService = new VendaService();

        // Inicializa os controllers com os services
        this.clienteController = new ClienteController(clienteService);
        this.veiculoController = new VeiculoController(veiculoService);
        this.servicoController = new ServicoController(servicoService);
        this.vendaController = new VendaController(vendaService);
    }

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Veículos");
            System.out.println("3 - Gerenciar Serviços");
            System.out.println("4 - Gerenciar Vendas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
                continue;
            }

            switch (opcao) {
                case 1 -> clienteController.iniciar();
                case 2 -> veiculoController.iniciar();
                case 3 -> servicoController.iniciar();
                case 4 -> vendaController.iniciar();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
