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
    public void showProjects() {
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
            System.out.println("3 - Data de Inicio");
            System.out.println("4 - Data de Conclusao");
            System.out.println("5 - Voltar");
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
                case 4:
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
                case 5:
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
        for(Project projectSelect : projectsRegistered) {
            if(projectSelect.getTitle().equalsIgnoreCase(title)) {
                Project[] proj =  new Project[projectsRegistered.length - 1];
                for (int i = 0; i < projectsRegistered.length; i++) {
                    if (!projectsRegistered[i].getTitle().equalsIgnoreCase(title)) {
                        proj[i] = projectsRegistered[i];
                    }
                }
                projectsRegistered = proj;
                System.out.println("Projeto com o titulo " + title + " removido com sucesso");
                return;
            }
        }

        System.out.println("Projeto com o titulo " + title + " nao encontrado");
    }

    @Override
    public void managerTeam(String title) {
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
            System.out.println("\n------ GERENCIAR EQUIPE DO PROJETO: " + targetProject.getTitle() + " ------");
            System.out.println("1 - Ver equipe do projeto");
            System.out.println("2 - Adicionar developer ao projeto");
            System.out.println("3 - Remover developer do projeto");
            System.out.println("4 - Voltar ao menu anterior");
            System.out.println("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("------ EQUIPE ATUAL DO PROJETO ------");
                    Employee[] currentTeam = targetProject.getDevelopers();
                    if (currentTeam != null && currentTeam.length > 0) {
                        for (Employee teamMember : currentTeam) {
                            System.out.println("ID   = " + teamMember.getId());
                            System.out.println("Nome = " + teamMember.getName());
                            System.out.println("--------------------------");
                        }
                    } else {
                        System.out.println("Nenhum desenvolvedor no projeto.");
                    }
                    break;
                case 2:
                    System.out.println("------ ADICIONAR DESENVOLVEDOR AO PROJETO ------");
                    if (developerService.getDevelopersRegisters().length == 0) {
                        System.out.println("Nenhum desenvolvedor cadastrado no sistema para adicionar.");
                        break;
                    }

                    System.out.println("Desenvolvedores disponíveis:");
                    for (Employee employeeAvailable : developerService.getDevelopersRegisters()) {
                        System.out.println("ID = " + employeeAvailable.getId() + ", Nome = " + employeeAvailable.getName());
                    }

                    System.out.print("Digite o ID do novo membro da equipe: ");
                    int employeeIdToAdd = scanner.nextInt();
                    scanner.nextLine();

                    Employee devToAdd = null;
                    for (Employee dev : developerService.getDevelopersRegisters()) {
                        if (dev.getId() == employeeIdToAdd) {
                            devToAdd = dev;
                            break;
                        }
                    }

                    if (devToAdd == null) {
                        System.out.println("ERRO: Desenvolvedor com ID informado não encontrado.");
                    } else {
                        Employee[] oldTeam = targetProject.getDevelopers() == null ? new Employee[0] : targetProject.getDevelopers();

                        boolean alreadyInTeam = false;
                        for(Employee member : oldTeam) {
                            if(member.getId() == devToAdd.getId()){
                                alreadyInTeam = true;
                                break;
                            }
                        }

                        if(alreadyInTeam) {
                            System.out.println("ERRO: Este desenvolvedor já faz parte do projeto.");
                        } else {
                            Employee[] newTeam = new Employee[oldTeam.length + 1];
                            for (int i = 0; i < oldTeam.length; i++) {
                                newTeam[i] = oldTeam[i];
                            }
                            newTeam[oldTeam.length] = devToAdd;

                            targetProject.setDevelopers((Developer[])newTeam);
                            System.out.println("Novo membro adicionado ao projeto com sucesso!");
                        }
                    }
                    break;
                case 3:
                    System.out.println("------ REMOVER DESENVOLVEDOR DO PROJETO ------");
                    Employee[] teamToRemoveFrom = targetProject.getDevelopers();
                    if (teamToRemoveFrom == null || teamToRemoveFrom.length == 0) {
                        System.out.println("O projeto já está vazio. Nenhum membro para remover.");
                        break;
                    }

                    System.out.println("Membros do projeto:");
                    for (Employee teamMember : teamToRemoveFrom) {
                        System.out.println("ID = " + teamMember.getId() + ", Nome = " + teamMember.getName());
                    }
                    System.out.print("Digite o id do membro que desejas remover: ");
                    int employeeIdToRemove = scanner.nextInt();
                    scanner.nextLine();

                    int indexToRemove = -1;
                    for (int i = 0; i < teamToRemoveFrom.length; i++) {
                        if (teamToRemoveFrom[i].getId() == employeeIdToRemove) {
                            indexToRemove = i;
                            break;
                        }
                    }

                    if (indexToRemove == -1) {
                        System.out.println("ERRO: Membro com o ID informado não encontrado no projeto.");
                    } else {
                        Employee[] newTeam = new Employee[teamToRemoveFrom.length - 1];
                        for (int i = 0, j = 0; i < teamToRemoveFrom.length; i++) {
                            if (i != indexToRemove) {
                                newTeam[j++] = teamToRemoveFrom[i];
                            }
                        }
                        targetProject.setDevelopers((Developer[]) newTeam);
                        System.out.println("Membro removido do projeto com sucesso!");
                    }
                    break;
                case 4:
                    System.out.println("Voltando ao menu anterior...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
