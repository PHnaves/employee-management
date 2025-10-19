package com.devdojo.app;

import com.devdojo.service.impl.DeveloperServiceImpl;
import com.devdojo.service.impl.ManagerServiceImpl;
import com.devdojo.service.impl.ProjectServiceImpl;

import java.util.Scanner;

public class Menu {
    static final Scanner scanner = new Scanner(System.in);
    private final DeveloperServiceImpl developerService = new DeveloperServiceImpl();
    private final ManagerServiceImpl managerService = new ManagerServiceImpl();
    private final ProjectServiceImpl projectService = new ProjectServiceImpl();

    public Menu() {
        boolean conditional = true;
        do {
            System.out.println("\n=========================================");
            System.out.println("👋 BEM-VINDO AO GESTOR DE FUNCIONÁRIOS E PROJETOS 👋");
            System.out.println("=========================================");
            System.out.println("Digite uma opção para continuar:");
            System.out.println("1 - Painel Gerentes");
            System.out.println("2 - Painel Desenvolvedores");
            System.out.println("3 - Painel de Projetos");
            System.out.println("4 - Sair");
            System.out.print("Opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                boolean back = false;
                while(!back) {
                    System.out.println("\n=========================================");
                    System.out.println("🧑‍💼 PAINEL GERENTES 🧑‍💼");
                    System.out.println("=========================================");
                    System.out.println("1 - Cadastrar novo gerente");
                    System.out.println("2 - Listar gerentes");
                    System.out.println("3 - Editar gerente");
                    System.out.println("4 - Gerenciar equipe");
                    System.out.println("5 - Relatório completo gerente");
                    System.out.println("6 - Demitir gerente");
                    System.out.println("7 - Voltar");
                    System.out.print("Opção: ");
                    int optionManager = scanner.nextInt();
                    scanner.nextLine();

                    switch (optionManager) {
                        case 1 -> managerService.createManager();
                        case 2 -> managerService.showManager();
                        case 3 -> {
                            System.out.println("\n1 - Editar informações pessoais");
                            System.out.println("2 - Editar informações técnicas");
                            System.out.println("3 - Voltar");
                            System.out.print("Opção: ");
                            int optionEdit = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Digite o ID do gerente que quer editar: ");
                            int updateId = scanner.nextInt();
                            scanner.nextLine();

                            if (optionEdit == 1) managerService.updateManager(updateId);
                            else if (optionEdit == 2) managerService.updateTechnicalInformations(updateId);
                            else if (optionEdit != 3) System.out.println("⚠ Opção inválida!");
                        }
                        case 4 -> {
                            System.out.print("Digite o ID do gerente que quer gerenciar a equipe: ");
                            int manageId = scanner.nextInt();
                            scanner.nextLine();
                            managerService.managerTeam(manageId);
                        }
                        case 5 -> {
                            System.out.print("Digite o ID do gerente que quer gerar o relatório completo: ");
                            int reportId = scanner.nextInt();
                            scanner.nextLine();
                            managerService.readManager(reportId);
                        }
                        case 6 -> {
                            System.out.print("Digite o ID do gerente que quer demitir: ");
                            int deleteId = scanner.nextInt();
                            scanner.nextLine();
                            managerService.deleteManager(deleteId);
                        }
                        case 7 -> back = true;
                        default -> System.out.println("⚠ Opção inválida, tente novamente!");
                    }
                }
            } else if (option == 2) {
                boolean back = false;
                while(!back) {
                    System.out.println("\n=========================================");
                    System.out.println("💻 PAINEL DESENVOLVEDORES 💻");
                    System.out.println("=========================================");
                    System.out.println("1 - Cadastrar novo desenvolvedor");
                    System.out.println("2 - Listar desenvolvedores");
                    System.out.println("3 - Editar desenvolvedor");
                    System.out.println("4 - Relatório completo desenvolvedor");
                    System.out.println("5 - Ver projeto associado");
                    System.out.println("6 - Demitir desenvolvedor");
                    System.out.println("7 - Voltar");
                    System.out.print("Opção: ");
                    int optionDeveloper = scanner.nextInt();
                    scanner.nextLine();

                    switch (optionDeveloper) {
                        case 1 -> developerService.createDeveloper();
                        case 2 -> developerService.showDevelopers();
                        case 3 -> {
                            System.out.println("\n1 - Editar informações pessoais");
                            System.out.println("2 - Editar informações técnicas");
                            System.out.println("3 - Voltar");
                            System.out.print("Opção: ");
                            int optionEdit = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Digite o ID do desenvolvedor que quer editar: ");
                            int updateId = scanner.nextInt();
                            scanner.nextLine();

                            if (optionEdit == 1) developerService.updateDeveloper(updateId);
                            else if (optionEdit == 2) developerService.updateTechnicalInformations(updateId);
                            else if (optionEdit != 3) System.out.println("⚠ Opção inválida!");
                        }
                        case 4 -> {
                            System.out.print("Digite o ID do desenvolvedor que quer gerar o relatório completo: ");
                            int reportId = scanner.nextInt();
                            scanner.nextLine();
                            developerService.readDeveloper(reportId);
                        }
                        case 5 -> {
                            System.out.print("Digite o ID do desenvolvedor que quer ver o projeto associado: ");
                            int projectId = scanner.nextInt();
                            scanner.nextLine();
                            developerService.project(projectId);
                        }
                        case 6 -> {
                            System.out.print("Digite o ID do desenvolvedor que quer demitir: ");
                            int deleteId = scanner.nextInt();
                            scanner.nextLine();
                            developerService.deleteDeveloper(deleteId);
                        }
                        case 7 -> back = true;
                        default -> System.out.println("⚠ Opção inválida, tente novamente!");
                    }
                }
            } else if (option == 3) {
                boolean back = false;
                while(!back) {
                    System.out.println("\n=========================================");
                    System.out.println("📁 PAINEL PROJETOS 📁");
                    System.out.println("=========================================");
                    System.out.println("1 - Criar novo projeto");
                    System.out.println("2 - Listar projetos");
                    System.out.println("3 - Editar projeto");
                    System.out.println("4 - Excluir projeto");
                    System.out.println("5 - Gerenciar equipe projeto");
                    System.out.println("6 - Relatório projeto");
                    System.out.println("7 - Voltar");
                    System.out.print("Opção: ");
                    int optionProject = scanner.nextInt();
                    scanner.nextLine();

                    switch (optionProject) {
                        case 1 -> projectService.createProject();
                        case 2 -> projectService.showProjects();
                        case 3 -> {
                            System.out.print("Digite o título do projeto que quer editar: ");
                            String titleProjectUpdate = scanner.nextLine();
                            projectService.updateProject(titleProjectUpdate);
                        }
                        case 4 -> {
                            System.out.print("Digite o título do projeto que quer excluir: ");
                            String titleProjectDelete = scanner.nextLine();
                            projectService.deleteProject(titleProjectDelete);
                        }
                        case 5 -> {
                            System.out.print("Digite o título do projeto que quer gerenciar a equipe: ");
                            String titleProjectManager = scanner.nextLine();
                            projectService.managerTeam(titleProjectManager);
                        }
                        case 6 -> {
                            System.out.print("Digite o título do projeto que quer gerar o relatório: ");
                            String titleProjectRead = scanner.nextLine();
                            projectService.readProject(titleProjectRead);
                        }
                        case 7 -> back = true;
                        default -> System.out.println("⚠ Opção inválida, tente novamente!");
                    }
                }
            } else if (option == 4) {
                conditional = false;
            } else {
                System.out.println("⚠ Opção inválida, tente novamente!");
            }

        } while (conditional);

        System.out.println("\n=========================================");
        System.out.println("👋 Saindo... Obrigado, volte sempre!!! 👋");
        System.out.println("=========================================");
    }
}
