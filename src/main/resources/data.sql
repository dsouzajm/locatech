CREATE TABLE veiculos(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(225),
    modelo VARCHAR(225),
    placa VARCHAR(225),
    ano VARCHAR(225),
    cor VARCHAR(225),
    valor_diaria DECIMAL(10,2)
);

CREATE TABLE pessoas(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(225),
    cpf VARCHAR(225),
    telefone VARCHAR(225),
    email VARCHAR(225)
);

CREATE TABLE alugueis(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pessoa_id BIGINT NOT NULL,
    veiculo_id BIGINT NOT NULL,
    data_inicio DATE,
    data_fim DATE,
    valor_total DECIMAL(10,2),
    FOREIGN KEY (pessoa_id) REFERENCES pessoas(id),
    FOREIGN KEY (veiculo_id) REFERENCES veiculos(id)
);

INSERT INTO veiculos (marca, modelo, placa, ano, cor, valor_diaria) VALUES
    ('Chevrolet', 'Celta', 'ABC-1234', 2010, 'preto', 100.00);

INSERT INTO pessoas (nome, cpf, telefone, email) VALUES
    ('Ciclano', '11122233344', '11 1234-1234', 'ciclano@gmail.com');

INSERT INTO alugueis (pessoa_id, veiculo_id, data_inicio, data_fim, valor_total) VALUES
    (1, 1, '2024-10-01', '2024-10-15', 1500.00);