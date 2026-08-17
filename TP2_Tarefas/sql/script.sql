-- Criação do banco de dados (pule esse comando se já existir do outro exercício)
CREATE DATABASE db_fatec;

-- Conectar ao banco (no psql)
  \c db_fatec

-- Criação da tabela tarefa
CREATE TABLE tarefa (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    concluida BOOLEAN NOT NULL DEFAULT FALSE
);
