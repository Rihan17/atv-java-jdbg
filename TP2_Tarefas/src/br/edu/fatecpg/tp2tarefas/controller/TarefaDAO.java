package br.edu.fatecpg.tp2tarefas.controller;

import br.edu.fatecpg.tp2tarefas.db.DB;
import br.edu.fatecpg.tp2tarefas.model.Tarefa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    // CREATE
    public void inserir(Tarefa tarefa) {
        String query = "INSERT INTO tarefa (titulo, categoria, concluida) VALUES (?, ?, ?)";

        try (Connection connection = DB.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getCategoria());
            stmt.setBoolean(3, tarefa.isConcluida());

            stmt.execute();
            System.out.println("Tarefa cadastrada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir tarefa: " + e.getMessage());
        }
    }

    // READ (lista todas)
    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();
        String query = "SELECT * FROM tarefa ORDER BY id";

        try (Connection connection = DB.connect();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tarefas.add(new Tarefa(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("categoria"),
                        rs.getBoolean("concluida")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar tarefas: " + e.getMessage());
        }

        return tarefas;
    }

    // UPDATE (editar título e categoria)
    public void atualizar(Tarefa tarefa) {
        String query = "UPDATE tarefa SET titulo = ?, categoria = ? WHERE id = ?";

        try (Connection connection = DB.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getCategoria());
            stmt.setInt(3, tarefa.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Tarefa atualizada com sucesso!");
            } else {
                System.out.println("Nenhuma tarefa encontrada com o ID " + tarefa.getId());
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    // UPDATE (marcar/desmarcar como concluída)
    public void marcarConcluida(int id, boolean concluida) {
        String query = "UPDATE tarefa SET concluida = ? WHERE id = ?";

        try (Connection connection = DB.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setBoolean(1, concluida);
            stmt.setInt(2, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println(concluida
                        ? "Tarefa marcada como concluída!"
                        : "Tarefa marcada como não concluída!");
            } else {
                System.out.println("Nenhuma tarefa encontrada com o ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar status da tarefa: " + e.getMessage());
        }
    }

    // DELETE
    public void deletar(int id) {
        String query = "DELETE FROM tarefa WHERE id = ?";

        try (Connection connection = DB.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Tarefa excluída com sucesso!");
            } else {
                System.out.println("Nenhuma tarefa encontrada com o ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao excluir tarefa: " + e.getMessage());
        }
    }
}
