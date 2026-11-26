CREATE TABLE produto_agregacao (
    id_produto BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    preco NUMERIC(10,2) NOT NULL CHECK (preco >= 0),
    estoque INTEGER NOT NULL DEFAULT 0 CHECK (estoque >= 0)
);

INSERT INTO produto_agregacao
(nome, categoria, preco, estoque)
VALUES
('Notebook', 'Informática', 3500.00, 5),
('Mouse', 'Informática', 80.00, 20),
('Teclado', 'Informática', 150.00, 15),
('Monitor', 'Informática', 1200.00, 8),
('Cadeira', 'Móveis', 600.00, 10),
('Mesa', 'Móveis', 900.00, 4),
('Armário', 'Móveis', 1500.00, 3),
('Caneta', 'Papelaria', 5.00, 100),
('Caderno', 'Papelaria', 25.00, 50),
('Borracha', 'Papelaria', 3.00, 80);