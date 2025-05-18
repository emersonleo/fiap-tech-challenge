CREATE TABLE usuarios
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(255),
    email    VARCHAR(255),
    login    VARCHAR(255),
    senha    VARCHAR(255),
    endereco VARCHAR(255),
    data_ultima_alteracao_registro DATE,
    data_criacao_registro DATE
);