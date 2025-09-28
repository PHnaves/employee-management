package com.devdojo.app;

import com.devdojo.domain.Employee;
import com.devdojo.domain.enums.EmployeeBenefits;
import com.devdojo.service.impl.DeveloperServiceImpl;

import java.util.Scanner;

public class Menu {
    static final Scanner scanner = new Scanner(System.in);
    private Employee[] employees = new Employee[]{};
    private final DeveloperServiceImpl developerService = new DeveloperServiceImpl();

    public void Menu() {
        boolean conditional = true;
        do {
            System.out.println("Bem vindo ao gestor de funcionarios e projetos");
            System.out.println("Digite uma opcao para continuar");
            System.out.println("1 - Painel Gerentes");
            System.out.println("2 - Painel Desenvolvedores");
            System.out.println("3 - Painel de projetos");
            System.out.println("4 - Sair");
            int option = scanner.nextInt();

            if (option == 1) {
                boolean back = false;
                while(!back) {
                    System.out.println("-----GERENTES-----");
                    System.out.println("1 - cadastrar novo gerente");
                    System.out.println("2 - listar gerentes");
                    System.out.println("3 - Editar gerente");
                    System.out.println("4 - Demitir gerente");
                    System.out.println("5 - Voltar");
                    int optionManager = scanner.nextInt();

                    switch (optionManager) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            back = true;
                        default:
                            System.out.println("Opção invalida, tente novamente!!");
                            break;
                    }
                }
            } else if (option == 2) {
                boolean back = false;
                while(!back) {
                    System.out.println("-----DESENVOLVEDORES-----");
                    System.out.println("1 - cadastrar novo desenvolvedor");
                    System.out.println("2 - listar desenvolvedores");
                    System.out.println("3 - Editar desenvolvedor");
                    System.out.println("4 - Relatorio completo desenvolvedor");
                    System.out.println("5 - Demitir desenvolvedor");
                    System.out.println("6 - Voltar");
                    int optionDeveloper = scanner.nextInt();

                    switch (optionDeveloper) {
                        case 1:
                            this.developerService.createDeveloper();
                            break;
                        case 2:
                            this.developerService.showDevelopers();
                            break;
                        case 3:
                            System.out.println("Digite o id do desenvolvedor que quer editar");
                            int updateId = scanner.nextInt();
                            scanner.nextLine();
                            this.developerService.updateDeveloper(updateId);
                            break;
                        case 4:
                            System.out.println("Digite o id do desenvolvedor que quer gerar o relatorio completo");
                            int reportId = scanner.nextInt();
                            scanner.nextLine();
                            this.developerService.readDeveloper(reportId);
                            break;
                        case 5:
                            System.out.println("Digite o id do desenvolvedor que quer demitir");
                            int deleteId = scanner.nextInt();
                            scanner.nextLine();
                            this.developerService.deleteDeveloper(deleteId);
                            break;
                        case 6:
                            back = true;
                        default:
                            System.out.println("Opção invalida, tente novamente!!");
                            break;
                    }
                }
            } else if (option == 3) {
                System.out.println("projetos");
                break;
            } else if (option == 4) {
                conditional = false;
                break;
            }

        }while (conditional);
        System.out.println("Saindo.....");
        System.out.println("Obrigado, volte sempre!!!");
    }
}
