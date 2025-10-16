package com.devdojo.service.impl;

import com.devdojo.domain.Developer;
import com.devdojo.domain.Employee;
import com.devdojo.domain.Project;
import com.devdojo.service.ProjectService;

import java.util.Scanner;

public class ProjectServiceImpl implements ProjectService {
    public static final Scanner scanner = new Scanner(System.in);
    private final DeveloperServiceImpl developerService = new DeveloperServiceImpl();
    public static Project[] projectsRegistered = new Project[]{};

    @Override
    public void createProject() {
        System.out.println("Digite o titulo do projeto");
        String projectTitle = scanner.nextLine();

        System.out.println("Digite a descricao do projeto");
        String projectDescription = scanner.nextLine();

        System.out.println("------ ADICIONAR DESENVOLVEDOR ------");
        if (developerService.getDevelopersRegisters().length == 0) {
            System.out.println("Nenhum desenvolvedor cadastrado no sistema");
            System.out.println("Cadastre um desenvolvedor para poder criar um projeto");
            return;
        }
        System.out.println("Desenvolvedores disponíveis:");
        for (Employee employeeAvailable : developerService.getDevelopersRegisters()) {
            System.out.println("ID = " + employeeAvailable.getId() + ", Nome = " + employeeAvailable.getName());
        }

        System.out.println("Quantos desenvolvedores vai querer adicionar: ");
        int developersQunatity = scanner.nextInt();
        scanner.nextLine();
        Developer[] developersSelect = new Developer[developersQunatity];

        for (int i = 0; i < developersQunatity; i++) {
            boolean valid = false;

            System.out.println("Digite somente o ID dos desenvolvedores que deseja adicionar!");
            while(!valid) {
                System.out.println((i + 1) + "Desenvolvedor: ");
                int input = scanner.nextInt();
                scanner.nextLine();

                for (Employee dev : developerService.getDevelopersRegisters()) {
                    if (dev.getId() == input) {
                        developersSelect[i] = (Developer) dev;
                        valid = true;
                        break;
                    }
                }

                if (!valid) {
                    System.out.println("ID invalido. Por favor, digite novamente");
                }
            }

        }

        System.out.println("Digite a data de inicio do projeto");
        String projectStartDate = scanner.nextLine();

        System.out.println("Digite a data de inicio do projeto");
        String projectEndDate = scanner.nextLine();

        Project project = new Project(
                projectTitle,
                projectDescription,
                developersSelect,
                projectStartDate,
                projectEndDate
        );

        Project[] newProject = new Project[projectsRegistered.length + 1];

        for (int i = 0; i < projectsRegistered.length; i++) {
            newProject[i] = projectsRegistered[i];
        }

        newProject[projectsRegistered.length] = project;

        projectsRegistered = newProject;

    }

    @Override
    public void showProject() {
        System.out.println("Projetos cadastrados");
        for (Project projects : projectsRegistered) {
            System.out.println("Titulo = " + projects.getTitle());
            System.out.println("Descricao");
            System.out.println(projects.getDescription());
            System.out.println("------------------------");
        }
    }

    @Override
    public void readProject(String title) {
        Project targetProject = null;
        for (Project project : projectsRegistered) {
            if (project.getTitle().equalsIgnoreCase(title)) {
                targetProject = project;
            }
        }

        if (targetProject == null) {
            System.out.println("Projeto com o titulo " + title + " nao encontrado.");
            return;
        }

        System.out.println("\n------ RELATORIO PROJETO: " + targetProject.getTitle() + " ------");
        System.out.println("Descricao");
        System.out.println(targetProject.getDescription());
        System.out.println("Equipe");
        for (Developer developers : targetProject.getDevelopers()) {
            System.out.println("ID = " + developers.getId() + " Nome = " + developers.getName());
        }
        System.out.println("Data de inicio = " + targetProject.getStartDate());
        System.out.println("Data final = " + targetProject.getEndDate());
    }

    @Override
    public void updateProject(String title) {
        Project targetProject = null;
        for (Project project : projectsRegistered) {
            if (project.getTitle().equalsIgnoreCase(title)) {
                targetProject = project;
            }
        }

        if (targetProject == null) {
            System.out.println("Projeto com o titulo " + title + " nao encontrado.");
            return;
        }

        while(true) {
            System.out.println("\n------ EDITAR PROJETO: " + targetProject.getTitle() + " ------");
            System.out.println("1 - Titulo");
            System.out.println("2 - Descricao");
            System.out.println("3 - Equipe");
            System.out.println("4 - Data de Inicio");
            System.out.println("5 - Data de Conclusao");
            System.out.println("6 - Voltar");
            System.out.println("Digite a opcao correspondente: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            String continueEdit = null;
            switch (option) {
                case 1:
                    System.out.println("Digite o novo titulo: ");
                    String newTitle = scanner.nextLine();
                    targetProject.setTitle(newTitle);
                    System.out.println("Titulo editado com sucesso");

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 2:
                    System.out.println("Digite a nova descricao: ");
                    String newDescription = scanner.nextLine();
                    targetProject.setDescription(newDescription);
                    System.out.println("Descricao editada com sucesso");

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 3:
                    System.out.println("EDITAR EQUIPE");
                    break;
                case 4:
                    System.out.println("Digite a nova data de inicio: ");
                    String newStartDate = scanner.nextLine();
                    targetProject.setStartDate(newStartDate);

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 5:
                    System.out.println("Digite a nova data de conclusao: ");
                    String newEndDate = scanner.nextLine();
                    targetProject.setEndDate(newEndDate);

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 6:
                    System.out.println("Voltando ao menu anterior...");
                    return;
                default:
                    System.out.println("Digite somente uma das opcoes");
                    break;
            }

        }
    }

    @Override
    public void deleteProject(String title) {

    }
}
