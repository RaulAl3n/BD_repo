CREATE TABLE categoria (
    id_categoria BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE produto (
    id_produto BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco NUMERIC(10,2) NOT NULL CHECK (preco >= 0),
    estoque INTEGER NOT NULL DEFAULT 0 CHECK (estoque >= 0),
    id_categoria BIGINT NOT NULL REFERENCES categoria(id_categoria)
);

INSERT INTO categoria (nome)
SELECT DISTINCT categoria FROM produto_agregacao;
INSERT INTO categoria (nome) VALUES ('Eletrodomésticos');  -- vazia

INSERT INTO produto (nome, preco, estoque, id_categoria)
SELECT pa.nome, pa.preco, pa.estoque, c.id_categoria
FROM produto_agregacao pa
JOIN categoria c ON c.nome = pa.categoria;