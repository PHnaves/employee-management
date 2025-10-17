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
            System.out.println("Bem vindo ao gestor de funcionarios e projetos");
            System.out.println("Digite uma opcao para continuar");
            System.out.println("1 - Painel Gerentes");
            System.out.println("2 - Painel Desenvolvedores");
            System.out.println("3 - Painel de projetos");
            System.out.println("4 - Sair");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                boolean back = false;
                while(!back) {
                    System.out.println("-----GERENTES-----");
                    System.out.println("1 - cadastrar novo gerente");
                    System.out.println("2 - listar gerentes");
                    System.out.println("3 - Editar gerente");
                    System.out.println("4 - Gerenciar equipe");
                    System.out.println("5 - Relatorio completo gerente");
                    System.out.println("6 - Demitir gerente");
                    System.out.println("7 - Voltar");
                    int optionManager = scanner.nextInt();
                    scanner.nextLine();

                    switch (optionManager) {
                        case 1:
                            managerService.createManager();
                            break;
                        case 2:
                            managerService.showManager();
                            break;
                        case 3:
                            System.out.println("1 - Editar informacoes pessoais");
                            System.out.println("2 - Editar informacoes tecnicas");
                            System.out.println("3 - Voltar");
                            int optionEdit = scanner.nextInt();
                            scanner.nextLine();

                            System.out.println("Digite o id do gerente que quer editar");
                            int updateId = scanner.nextInt();
                            scanner.nextLine();

                            if (optionEdit == 1) {
                                this.managerService.updateManager(updateId);
                            } else if (optionEdit == 2) {
                                this.managerService.updateTechnicalInformations(updateId);
                            } else if (optionEdit == 3) {
                                break;
                            } else {
                                System.out.println("Opcao invalida");
                            }
                            break;
                        case 4:
                            System.out.println("Digite o id do gerente que quer gerenciar a equipe");
                            int manageId = scanner.nextInt();
                            scanner.nextLine();

                            managerService.managerTeam(manageId);
                            break;
                        case 5:
                            System.out.println("Digite o id do gerente que quer gerar o relatorio completo");
                            int reportId = scanner.nextInt();
                            scanner.nextLine();

                            managerService.readManager(reportId);
                            break;
                        case 6:
                            System.out.println("Digite o id do gerente que quer demitir");
                            int deleteId = scanner.nextInt();
                            scanner.nextLine();

                            managerService.deleteManager(deleteId);
                            break;
                        case 7:
                            back = true;
                            break;
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
                    scanner.nextLine();

                    switch (optionDeveloper) {
                        case 1:
                            developerService.createDeveloper();
                            break;
                        case 2:
                            developerService.showDevelopers();
                            break;
                        case 3:
                            System.out.println("1 - Editar informacoes pessoais");
                            System.out.println("2 - Editar informacoes tecnicas");
                            System.out.println("3 - Voltar");
                            int optionEdit = scanner.nextInt();
                            scanner.nextLine();

                            System.out.println("Digite o id do desenvolvedor que quer editar");
                            int updateId = scanner.nextInt();
                            scanner.nextLine();

                            if (optionEdit == 1) {
                                developerService.updateDeveloper(updateId);
                            } else if (optionEdit == 2) {
                                developerService.updateTechnicalInformations(updateId);
                            } else if (optionEdit == 3) {
                                break;
                            } else {
                                System.out.println("Opcao invalida");
                            }
                            break;
                        case 4:
                            System.out.println("Digite o id do desenvolvedor que quer gerar o relatorio completo");
                            int reportId = scanner.nextInt();
                            scanner.nextLine();

                            developerService.readDeveloper(reportId);
                            break;
                        case 5:
                            System.out.println("Digite o id do desenvolvedor que quer demitir");
                            int deleteId = scanner.nextInt();
                            scanner.nextLine();

                            developerService.deleteDeveloper(deleteId);
                            break;
                        case 6:
                            back = true;
                            break;
                        default:
                            System.out.println("Opção invalida, tente novamente!!");
                            break;
                    }
                }
            } else if (option == 3) {
                boolean back = false;
                while(!back) {
                    System.out.println("-----PROJETOS-----");
                    System.out.println("1 - Criar novo projeto");
                    System.out.println("2 - Listar projetos");
                    System.out.println("3 - Editar projeto");
                    System.out.println("4 - Excluir projeto");
                    System.out.println("5 - Gerenciar equipe projeto");
                    System.out.println("6 - Relatorio projeto");
                    System.out.println("7 - Voltar");
                    int optionProject = scanner.nextInt();
                    scanner.nextLine();

                    switch (optionProject) {
                        case 1:
                            projectService.createProject();
                            break;
                        case 2:
                            projectService.showProjects();
                            break;
                        case 3:
                            System.out.println("Digite o titulo do projeto que quer editar");
                            String titleProjectUpdate = scanner.nextLine();
                            projectService.updateProject(titleProjectUpdate);
                            break;
                        case 4:
                            System.out.println("Digite o titulo do projeto que quer excluir");
                            String titleProjectDelete = scanner.nextLine();
                            projectService.deleteProject(titleProjectDelete);
                            break;
                        case 5:
                            System.out.println("Digite o titulo do projeto que quer gerenciar a equipe");
                            String titleProjectManager = scanner.nextLine();
                            projectService.managerTeam(titleProjectManager);
                            break;
                        case 6:
                            System.out.println("Digite o titulo do projeto que quer gerar o relatorio");
                            String titleProjectRead= scanner.nextLine();
                            projectService.readProject(titleProjectRead);
                            break;
                        case 7:
                            back = true;
                            break;
                        default:
                            System.out.println("Opção invalida, tente novamente!!");
                            break;
                    }
                }
            } else if (option == 4) {
                conditional = false;
                break;
            }

        }while (conditional);
        System.out.println("Saindo.....");
        System.out.println("Obrigado, volte sempre!!!");
    }
}
