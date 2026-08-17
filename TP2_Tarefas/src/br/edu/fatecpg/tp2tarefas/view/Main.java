package br.edu.fatecpg.tp2tarefas.view;

import br.edu.fatecpg.tp2tarefas.controller.TarefaDAO;
import br.edu.fatecpg.tp2tarefas.model.Tarefa;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TarefaDAO tarefaDAO = new TarefaDAO();
        int opcao;

        do {
            System.out.println("\n===== GESTÃO DE TAREFAS =====");
            System.out.println("1. Criar tarefa");
            System.out.println("2. Listar tarefas");
            System.out.println("3. Editar tarefa");
            System.out.println("4. Excluir tarefa");
            System.out.println("5. Marcar/Desmarcar tarefa como concluída");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro(sc);

            switch (opcao) {
                case 1:
                    criarTarefa(sc, tarefaDAO);
                    break;

                case 2:
                    listarTarefas(tarefaDAO);
                    break;

                case 3:
                    editarTarefa(sc, tarefaDAO);
                    break;

                case 4:
                    excluirTarefa(sc, tarefaDAO);
                    break;

                case 5:
                    alterarStatus(sc, tarefaDAO);
                    break;

                case 0:
                    System.out.println("Encerrando aplicação...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);

        sc.close();
    }

    private static void criarTarefa(Scanner sc, TarefaDAO tarefaDAO) {
        System.out.print("Título da tarefa: ");
        String titulo = sc.nextLine();

        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        Tarefa tarefa = new Tarefa(titulo, categoria);
        tarefaDAO.inserir(tarefa);
    }

    private static void listarTarefas(TarefaDAO tarefaDAO) {
        List<Tarefa> tarefas = tarefaDAO.listar();

        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            System.out.println("\n--- Tarefas cadastradas ---");
            tarefas.forEach(System.out::println);
        }
    }

    private static void editarTarefa(Scanner sc, TarefaDAO tarefaDAO) {
        System.out.print("ID da tarefa a editar: ");
        int id = lerInteiro(sc);

        System.out.print("Novo título: ");
        String titulo = sc.nextLine();

        System.out.print("Nova categoria: ");
        String categoria = sc.nextLine();

        Tarefa tarefa = new Tarefa(id, titulo, categoria, false);
        tarefaDAO.atualizar(tarefa);
    }

    private static void excluirTarefa(Scanner sc, TarefaDAO tarefaDAO) {
        System.out.print("ID da tarefa a excluir: ");
        int id = lerInteiro(sc);

        tarefaDAO.deletar(id);
    }

    private static void alterarStatus(Scanner sc, TarefaDAO tarefaDAO) {
        System.out.print("ID da tarefa: ");
        int id = lerInteiro(sc);

        System.out.print("Marcar como concluída? (1-Sim / 0-Não): ");
        int resposta = lerInteiro(sc);

        tarefaDAO.marcarConcluida(id, resposta == 1);
    }


    private static int lerInteiro(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido, digite um número: ");
            }
        }
    }
}
