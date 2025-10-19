package com.devdojo.service.impl;

import com.devdojo.domain.Developer;
import com.devdojo.domain.Employee;
import com.devdojo.domain.Project;
import com.devdojo.service.ProjectService;
import com.devdojo.storage.DataStorage;

import javax.xml.crypto.Data;
import java.util.Scanner;

public class ProjectServiceImpl implements ProjectService {

    @Override
    public void createProject() {
        System.out.println("Digite o titulo do projeto");
        String projectTitle = DataStorage.scanner.nextLine();

        System.out.println("Digite a descricao do projeto");
        String projectDescription = DataStorage.scanner.nextLine();

        System.out.println("------ ADICIONAR DESENVOLVEDOR ------");
        if (DataStorage.developersRegisters.length == 0) {
            System.out.println("Nenhum desenvolvedor cadastrado no sistema");
            System.out.println("Cadastre um desenvolvedor para poder criar um projeto");
            return;
        }
        System.out.println("Desenvolvedores disponíveis:");
        for (Developer developerAvailable : DataStorage.developersRegisters) {
            System.out.println("ID = " + developerAvailable.getId() + ", Nome = " + developerAvailable.getName());
        }

        System.out.println("Quantos desenvolvedores vai querer adicionar: ");
        int developersQuantity = DataStorage.scanner.nextInt();
        DataStorage.scanner.nextLine();
        Developer[] developersSelect = new Developer[developersQuantity];

        for (int i = 0; i < developersQuantity; i++) {
            boolean valid = false;

            System.out.println("Digite somente o ID dos desenvolvedores que deseja adicionar!");
            while (!valid) {
                System.out.println((i + 1) + " Desenvolvedor: ");
                int input = DataStorage.scanner.nextInt();
                DataStorage.scanner.nextLine();

                for (Developer dev : DataStorage.developersRegisters) {
                    if (dev.getId() == input) {
                        developersSelect[i] = dev;
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
        String projectStartDate = DataStorage.scanner.nextLine();

        System.out.println("Digite a data de conclusao do projeto");
        String projectEndDate = DataStorage.scanner.nextLine();

        Project project = new Project(
                projectTitle,
                projectDescription,
                developersSelect,
                projectStartDate,
                projectEndDate
        );

        if (developersSelect != null) {
            for (Developer d : developersSelect) {
                if (d != null) {
                    d.setProject(project);
                }
            }
        }

        Project[] newProjectRegisters = new Project[DataStorage.projectsRegistered.length + 1];

        for (int i = 0; i < DataStorage.projectsRegistered.length; i++) {
            newProjectRegisters[i] = DataStorage.projectsRegistered[i];
        }

        newProjectRegisters[DataStorage.projectsRegistered.length] = project;

        DataStorage.projectsRegistered = newProjectRegisters;

        System.out.println("Projeto criado com sucesso!");
    }

    @Override
    public void showProjects() {
        System.out.println("Projetos cadastrados");
        for (Project projects : DataStorage.projectsRegistered) {
            System.out.println("Titulo = " + projects.getTitle());
            System.out.println("Descricao");
            System.out.println(projects.getDescription());
            System.out.println("------------------------");
        }
    }

    @Override
    public void readProject(String title) {
        Project targetProject = null;
        for (Project project : DataStorage.projectsRegistered) {
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
        for (Project project : DataStorage.projectsRegistered) {
            if (project.getTitle().equalsIgnoreCase(title)) {
                targetProject = project;
            }
        }

        if (targetProject == null) {
            System.out.println("Projeto com o titulo " + title + " nao encontrado.");
            return;
        }

        while (true) {
            System.out.println("\n------ EDITAR PROJETO: " + targetProject.getTitle() + " ------");
            System.out.println("1 - Titulo");
            System.out.println("2 - Descricao");
            System.out.println("3 - Data de Inicio");
            System.out.println("4 - Data de Conclusao");
            System.out.println("5 - Voltar");
            System.out.println("Digite a opcao correspondente: ");
            int option = DataStorage.scanner.nextInt();
            DataStorage.scanner.nextLine();

            String continueEdit = null;
            switch (option) {
                case 1:
                    System.out.println("Digite o novo titulo: ");
                    String newTitle = DataStorage.scanner.nextLine();
                    targetProject.setTitle(newTitle);
                    System.out.println("Titulo editado com sucesso");

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 2:
                    System.out.println("Digite a nova descricao: ");
                    String newDescription = DataStorage.scanner.nextLine();
                    targetProject.setDescription(newDescription);
                    System.out.println("Descricao editada com sucesso");

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Digite a nova data de inicio: ");
                    String newStartDate = DataStorage.scanner.nextLine();
                    targetProject.setStartDate(newStartDate);

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
                    if (continueEdit.equals("NAO")) {
                        System.out.println("Voltando ao menu anterior...");
                        return;
                    }
                    break;
                case 4:
                    System.out.println("Digite a nova data de conclusao: ");
                    String newEndDate = DataStorage.scanner.nextLine();
                    targetProject.setEndDate(newEndDate);

                    System.out.println("Quer continuar editando? SIM ou NAO");
                    continueEdit = DataStorage.scanner.nextLine().toUpperCase();
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
        for (Project projectSelect : DataStorage.projectsRegistered) {
            if (projectSelect.getTitle().equalsIgnoreCase(title)) {
                Project[] deleteProject = new Project[DataStorage.projectsRegistered.length - 1];
                for (int i = 0; i < DataStorage.projectsRegistered.length; i++) {
                    if (!DataStorage.projectsRegistered[i].getTitle().equalsIgnoreCase(title)) {
                        deleteProject[i] = DataStorage.projectsRegistered[i];
                    }
                }
                DataStorage.projectsRegistered = deleteProject;
                System.out.println("Projeto com o titulo " + title + " removido com sucesso");
                return;
            }
        }

        System.out.println("Projeto com o titulo " + title + " nao encontrado");
    }

    @Override
    public void managerTeam(String title) {
        Project targetProject = null;
        for (Project project : DataStorage.projectsRegistered) {
            if (project.getTitle().equalsIgnoreCase(title)) {
                targetProject = project;
                break;
            }
        }

        if (targetProject == null) {
            System.out.println("Projeto com o título " + title + " não encontrado.");
            return;
        }

        while (true) {
            System.out.println("\n------ GERENCIAR EQUIPE DO PROJETO: " + targetProject.getTitle() + " ------");
            System.out.println("1 - Ver equipe do projeto");
            System.out.println("2 - Adicionar developer ao projeto");
            System.out.println("3 - Remover developer do projeto");
            System.out.println("4 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            int option = DataStorage.scanner.nextInt();
            DataStorage.scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("------ EQUIPE ATUAL DO PROJETO ------");
                    Developer[] currentTeam = targetProject.getDevelopers();
                    if (currentTeam != null && currentTeam.length > 0) {
                        for (Developer teamMember : currentTeam) {
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
                    if (DataStorage.developersRegisters.length == 0) {
                        System.out.println("Nenhum desenvolvedor cadastrado no sistema para adicionar.");
                        break;
                    }

                    System.out.println("Desenvolvedores disponíveis:");
                    for (Developer developerAvailable : DataStorage.developersRegisters) {
                        System.out.println("ID = " + developerAvailable.getId() + ", Nome = " + developerAvailable.getName());
                    }

                    System.out.print("Digite o ID do novo membro da equipe: ");
                    int developerIdToAdd = DataStorage.scanner.nextInt();
                    DataStorage.scanner.nextLine();

                    Developer devToAdd = null;
                    int devIndex = -1;
                    for (int i = 0; i < DataStorage.developersRegisters.length; i++) {
                        if (DataStorage.developersRegisters[i].getId() == developerIdToAdd) {
                            devToAdd = DataStorage.developersRegisters[i];
                            devIndex = i;
                            break;
                        }
                    }

                    if (devToAdd == null) {
                        System.out.println("ERRO: Desenvolvedor com ID informado não encontrado.");
                        break;
                    }

                    Developer[] oldTeam = targetProject.getDevelopers() == null ? new Developer[0] : targetProject.getDevelopers();

                    boolean alreadyInTeam = false;
                    for (Developer member : oldTeam) {
                        if (member.getId() == devToAdd.getId()) {
                            alreadyInTeam = true;
                            break;
                        }
                    }

                    if (alreadyInTeam) {
                        System.out.println("ERRO: Este desenvolvedor já faz parte do projeto.");
                        break;
                    }

                    Developer[] newTeam = new Developer[oldTeam.length + 1];
                    for (int i = 0; i < oldTeam.length; i++) {
                        newTeam[i] = oldTeam[i];
                    }
                    newTeam[oldTeam.length] = devToAdd;

                    targetProject.setDevelopers(newTeam);

                    DataStorage.developersRegisters[devIndex].setProject(targetProject);

                    System.out.println("Novo membro adicionado ao projeto com sucesso!");
                    break;

                case 3:
                    System.out.println("------ REMOVER DESENVOLVEDOR DO PROJETO ------");
                    Developer[] teamToRemoveFrom = targetProject.getDevelopers();
                    if (teamToRemoveFrom == null || teamToRemoveFrom.length == 0) {
                        System.out.println("O projeto já está vazio. Nenhum membro para remover.");
                        break;
                    }

                    System.out.println("Membros do projeto:");
                    for (Developer teamMember : teamToRemoveFrom) {
                        System.out.println("ID = " + teamMember.getId() + ", Nome = " + teamMember.getName());
                    }

                    System.out.print("Digite o ID do membro que desejas remover: ");
                    int developerIdToRemove = DataStorage.scanner.nextInt();
                    DataStorage.scanner.nextLine();

                    Developer devToRemove = null;
                    int indexToRemove = -1;
                    for (int i = 0; i < teamToRemoveFrom.length; i++) {
                        if (teamToRemoveFrom[i].getId() == developerIdToRemove) {
                            devToRemove = teamToRemoveFrom[i];
                            indexToRemove = i;
                            break;
                        }
                    }

                    if (indexToRemove == -1) {
                        System.out.println("ERRO: Membro com o ID informado não encontrado no projeto.");
                        break;
                    }

                    Developer[] newTeamRemove = new Developer[teamToRemoveFrom.length - 1];
                    for (int i = 0, j = 0; i < teamToRemoveFrom.length; i++) {
                        if (i != indexToRemove) {
                            newTeamRemove[j++] = teamToRemoveFrom[i];
                        }
                    }

                    targetProject.setDevelopers(newTeamRemove);

                    for (int i = 0; i < DataStorage.developersRegisters.length; i++) {
                        if (DataStorage.developersRegisters[i].getId() == developerIdToRemove) {
                            DataStorage.developersRegisters[i].setProject(null);
                            break;
                        }
                    }

                    System.out.println("Membro removido do projeto com sucesso!");
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
