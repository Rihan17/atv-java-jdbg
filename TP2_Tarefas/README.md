# TP2 - Gestão de Tarefas com JDBC e PostgreSQL

Aplicação console em Java com menu switch-case e persistência em PostgreSQL.
Cobre criar, listar, editar, excluir e marcar tarefas como concluídas, com
categorização — sem os filtros do desafio extra (por categoria/status).

## Estrutura

```
src/br/edu/fatecpg/tp2tarefas/
 ├── db/DB.java                 -> conexão com o PostgreSQL
 ├── model/Tarefa.java          -> classe modelo (id, titulo, categoria, concluida)
 ├── controller/TarefaDAO.java  -> inserir, listar, atualizar, marcarConcluida, deletar
 └── view/Main.java             -> menu console (switch-case)
sql/script.sql                  -> criação da tabela tarefa
```

## Como rodar

1. **Banco de dados**
   - Se ainda não criou o `db_fatec` no exercício de Curso, crie primeiro.
   - Execute o `sql/script.sql` para criar a tabela `tarefa`:
     ```
     \c db_fatec
     CREATE TABLE tarefa (
         id SERIAL PRIMARY KEY,
         titulo VARCHAR(255) NOT NULL,
         categoria VARCHAR(100) NOT NULL,
         concluida BOOLEAN NOT NULL DEFAULT FALSE
     );
     ```

2. **Credenciais**
   - Ajuste `user`/`password` no `DB.java` se necessário.

3. **Driver JDBC**
   - Mesmo `.jar` do PostgreSQL Connector usado no exercício de Curso.

4. **Executar**
   - Rode a classe `Main.java`:
     ```
     ===== GESTÃO DE TAREFAS =====
     1. Criar tarefa
     2. Listar tarefas
     3. Editar tarefa
     4. Excluir tarefa
     5. Marcar/Desmarcar tarefa como concluída
     0. Sair
     ```

## Observações

- Segue o mesmo padrão do exercício de Curso: `PreparedStatement`,
  `try-with-resources`, DAO separado da view.
- A opção 5 permite marcar **e** desmarcar uma tarefa como concluída
  (responde 1 ou 0 quando perguntado).
- Os filtros por categoria/status (o "Desafio" opcional dos slides) não
  foram implementados aqui, conforme combinado.
