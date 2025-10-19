package com.devdojo.service.impl;

import com.devdojo.domain.Developer;
import com.devdojo.domain.Project;
import com.devdojo.service.ProjectService;
import com.devdojo.storage.DataStorage;

public class ProjectServiceImpl implements ProjectService {

    @Override
    public void createProject() {
        System.out.println("\n=========================================");
        System.out.println("📝 CRIAR NOVO PROJETO 📝");
        System.out.println("=========================================");

        System.out.print("Digite o título do projeto: ");
        String projectTitle = DataStorage.scanner.nextLine();

        System.out.print("Digite a descrição do projeto: ");
        String projectDescription = DataStorage.scanner.nextLine();

        System.out.println("\n------ ADICIONAR DESENVOLVEDORES ------");
        if (DataStorage.developersRegisters.length == 0) {
            System.out.println("⚠ Nenhum desenvolvedor cadastrado no sistema.");
            System.out.println("Cadastre desenvolvedores antes de criar um projeto.");
            return;
        }

        System.out.println("Desenvolvedores disponíveis:");
        for (Developer developerAvailable : DataStorage.developersRegisters) {
            System.out.printf("ID = %d, Nome = %s%n", developerAvailable.getId(), developerAvailable.getName());
        }

        System.out.print("Quantos desenvolvedores deseja adicionar? ");
        int developersQuantity = DataStorage.scanner.nextInt();
        DataStorage.scanner.nextLine();

        Developer[] developersSelect = new Developer[developersQuantity];

        for (int i = 0; i < developersQuantity; i++) {
            boolean valid = false;
            System.out.println("\nDigite somente o ID dos desenvolvedores que deseja adicionar!");
            while (!valid) {
                System.out.print((i + 1) + "º Desenvolvedor: ");
                int input = DataStorage.scanner.nextInt();
                DataStorage.scanner.nextLine();

                for (Developer dev : DataStorage.developersRegisters) {
                    if (dev.getId() == input) {
                        developersSelect[i] = dev;
                        valid = true;
                        break;
                    }
                }

                if (!valid) System.out.println("⚠ ID inválido. Tente novamente.");
            }
        }

        System.out.print("\nDigite a data de início do projeto: ");
        String projectStartDate = DataStorage.scanner.nextLine();

        System.out.print("Digite a data de conclusão do projeto: ");
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
                if (d != null) d.setProject(project);
            }
        }

        Project[] newProjectRegisters = new Project[DataStorage.projectsRegistered.length + 1];
        for (int i = 0; i < DataStorage.projectsRegistered.length; i++) {
            newProjectRegisters[i] = DataStorage.projectsRegistered[i];
        }
        newProjectRegisters[DataStorage.projectsRegistered.length] = project;
        DataStorage.projectsRegistered = newProjectRegisters;

        System.out.println("\n✅ Projeto criado com sucesso!");
    }

    @Override
    public void showProjects() {
        System.out.println("\n=========================================");
        System.out.println("📂 PROJETOS CADASTRADOS 📂");
        System.out.println("=========================================");
        for (Project projects : DataStorage.projectsRegistered) {
            System.out.printf("Título   : %s%n", projects.getTitle());
            System.out.println("Descrição:");
            System.out.println(projects.getDescription());
            System.out.println("-----------------------------------------");
        }
    }

    @Override
    public void readProject(String title) {
        Project targetProject = null;
        for (Project project : DataStorage.projectsRegistered) {
            if (project.getTitle().equalsIgnoreCase(title)) targetProject = project;
        }

        if (targetProject == null) {
            System.out.println("⚠ Projeto com o título \"" + title + "\" não encontrado.");
            return;
        }

        System.out.println("\n=========================================");
        System.out.println("📄 RELATÓRIO DO PROJETO: " + targetProject.getTitle());
        System.out.println("=========================================");
        System.out.println("Descrição:");
        System.out.println(targetProject.getDescription());
        System.out.println("\nEquipe:");
        for (Developer developers : targetProject.getDevelopers()) {
            System.out.printf("ID = %d, Nome = %s%n", developers.getId(), developers.getName());
        }
        System.out.printf("Data de início  : %s%n", targetProject.getStartDate());
        System.out.printf("Data de conclusão: %s%n", targetProject.getEndDate());
    }

    @Override
    public void updateProject(String title) {
        Project targetProject = null;
        for (Project project : DataStorage.projectsRegistered) {
            if (project.getTitle().equalsIgnoreCase(title)) targetProject = project;
        }

        if (targetProject == null) {
            System.out.println("⚠ Projeto com o título \"" + title + "\" não encontrado.");
            return;
        }

        while (true) {
            System.out.println("\n=========================================");
            System.out.println("✏️ EDITAR PROJETO: " + targetProject.getTitle());
            System.out.println("=========================================");
            System.out.println("1 - Título");
            System.out.println("2 - Descrição");
            System.out.println("3 - Data de Início");
            System.out.println("4 - Data de Conclusão");
            System.out.println("5 - Voltar");
            System.out.print("Escolha a opção: ");

            int option = DataStorage.scanner.nextInt();
            DataStorage.scanner.nextLine();

            String continueEdit;
            switch (option) {
                case 1 -> {
                    System.out.print("Digite o novo título: ");
                    targetProject.setTitle(DataStorage.scanner.nextLine());
                    System.out.println("✅ Título editado com sucesso!");
                }
                case 2 -> {
                    System.out.print("Digite a nova descrição: ");
                    targetProject.setDescription(DataStorage.scanner.nextLine());
                    System.out.println("✅ Descrição editada com sucesso!");
                }
                case 3 -> {
                    System.out.print("Digite a nova data de início: ");
                    targetProject.setStartDate(DataStorage.scanner.nextLine());
                    System.out.println("✅ Data de início editada com sucesso!");
                }
                case 4 -> {
                    System.out.print("Digite a nova data de conclusão: ");
                    targetProject.setEndDate(DataStorage.scanner.nextLine());
                    System.out.println("✅ Data de conclusão editada com sucesso!");
                }
                case 5 -> {
                    System.out.println("Voltando ao menu anterior...");
                    return;
                }
                default -> System.out.println("⚠ Opção inválida. Tente novamente.");
            }

            System.out.print("Deseja continuar editando? (SIM/NAO): ");
            continueEdit = DataStorage.scanner.nextLine().toUpperCase();
            if (continueEdit.equals("NAO")) {
                System.out.println("Voltando ao menu anterior...");
                return;
            }
        }
    }

    @Override
    public void deleteProject(String title) {
        Project targetProject = null;
        int index = -1;

        for (int i = 0; i < DataStorage.projectsRegistered.length; i++) {
            if (DataStorage.projectsRegistered[i].getTitle().equalsIgnoreCase(title)) {
                targetProject = DataStorage.projectsRegistered[i];
                index = i;
                break;
            }
        }

        if (targetProject == null) {
            System.out.println("⚠ Projeto com o título \"" + title + "\" não encontrado.");
            return;
        }

        Project[] newArray = new Project[DataStorage.projectsRegistered.length - 1];
        for (int i = 0, j = 0; i < DataStorage.projectsRegistered.length; i++) {
            if (i != index) newArray[j++] = DataStorage.projectsRegistered[i];
        }

        DataStorage.projectsRegistered = newArray;
        System.out.println("✅ Projeto \"" + title + "\" removido com sucesso!");
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
            System.out.println("⚠ Projeto com o título \"" + title + "\" não encontrado.");
            return;
        }

        while (true) {
            System.out.println("\n=========================================");
            System.out.println("👥 GERENCIAR EQUIPE DO PROJETO: " + targetProject.getTitle());
            System.out.println("=========================================");
            System.out.println("1 - Ver equipe do projeto");
            System.out.println("2 - Adicionar developer ao projeto");
            System.out.println("3 - Remover developer do projeto");
            System.out.println("4 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            int option = DataStorage.scanner.nextInt();
            DataStorage.scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.println("\n------ EQUIPE ATUAL DO PROJETO ------");
                    Developer[] currentTeam = targetProject.getDevelopers();
                    if (currentTeam != null && currentTeam.length > 0) {
                        for (Developer teamMember : currentTeam) {
                            System.out.printf("ID = %d, Nome = %s%n", teamMember.getId(), teamMember.getName());
                        }
                    } else {
                        System.out.println("Nenhum desenvolvedor no projeto.");
                    }
                }
                case 2 -> {
                    System.out.println("\n------ ADICIONAR DESENVOLVEDOR AO PROJETO ------");
                    if (DataStorage.developersRegisters.length == 0) {
                        System.out.println("Nenhum desenvolvedor cadastrado no sistema para adicionar.");
                        break;
                    }

                    System.out.println("Desenvolvedores disponíveis:");
                    for (Developer developerAvailable : DataStorage.developersRegisters) {
                        System.out.printf("ID = %d, Nome = %s%n", developerAvailable.getId(), developerAvailable.getName());
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
                        System.out.println("⚠ Desenvolvedor com ID informado não encontrado.");
                        break;
                    }

                    Developer[] oldTeam = targetProject.getDevelopers() == null ? new Developer[0] : targetProject.getDevelopers();
                    boolean alreadyInTeam = false;
                    for (Developer member : oldTeam) if (member.getId() == devToAdd.getId()) alreadyInTeam = true;

                    if (alreadyInTeam) {
                        System.out.println("⚠ Este desenvolvedor já faz parte do projeto.");
                        break;
                    }

                    Developer[] newTeam = new Developer[oldTeam.length + 1];
                    System.arraycopy(oldTeam, 0, newTeam, 0, oldTeam.length);
                    newTeam[oldTeam.length] = devToAdd;

                    targetProject.setDevelopers(newTeam);
                    DataStorage.developersRegisters[devIndex].setProject(targetProject);

                    System.out.println("✅ Novo membro adicionado ao projeto com sucesso!");
                }
                case 3 -> {
                    System.out.println("\n------ REMOVER DESENVOLVEDOR DO PROJETO ------");
                    Developer[] teamToRemoveFrom = targetProject.getDevelopers();
                    if (teamToRemoveFrom == null || teamToRemoveFrom.length == 0) {
                        System.out.println("O projeto já está vazio. Nenhum membro para remover.");
                        break;
                    }

                    System.out.println("Membros do projeto:");
                    for (Developer teamMember : teamToRemoveFrom) {
                        System.out.printf("ID = %d, Nome = %s%n", teamMember.getId(), teamMember.getName());
                    }

                    System.out.print("Digite o ID do membro que deseja remover: ");
                    int developerIdToRemove = DataStorage.scanner.nextInt();
                    DataStorage.scanner.nextLine();

                    int indexToRemove = -1;
                    for (int i = 0; i < teamToRemoveFrom.length; i++) {
                        if (teamToRemoveFrom[i].getId() == developerIdToRemove) {
                            indexToRemove = i;
                            break;
                        }
                    }

                    if (indexToRemove == -1) {
                        System.out.println("⚠ Membro com o ID informado não encontrado no projeto.");
                        break;
                    }

                    Developer[] newTeamRemove = new Developer[teamToRemoveFrom.length - 1];
                    for (int i = 0, j = 0; i < teamToRemoveFrom.length; i++) {
                        if (i != indexToRemove) newTeamRemove[j++] = teamToRemoveFrom[i];
                    }

                    targetProject.setDevelopers(newTeamRemove);

                    for (Developer dev : DataStorage.developersRegisters) {
                        if (dev.getId() == developerIdToRemove) dev.setProject(null);
                    }

                    System.out.println("✅ Membro removido do projeto com sucesso!");
                }
                case 4 -> {
                    System.out.println("Voltando ao menu anterior...");
                    return;
                }
                default -> System.out.println("⚠ Opção inválida. Tente novamente.");
            }
        }
    }
}
