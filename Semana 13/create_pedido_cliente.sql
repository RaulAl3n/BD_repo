CREATE TABLE cliente (
  id_cliente BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  aniversario VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL

);

CREATE TABLE pedido (
  id_pedido BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

  id_produto BIGINT,
  id_cliente BIGINT,

  CONSTRAINT fk_produto_pedido
        FOREIGN KEY (id_produto)
        REFERENCES produto(id_produto),
  
  CONSTRAINT fk_cliente_pedido
        FOREIGN KEY (id_cliente)
        REFERENCES cliente(id_cliente)

);

INSERT INTO cliente (nome, aniversario, email)
VALUES
('Raul', '11/01', 'ra145090@uem.br'),
('Joao', '01/10', 'johndoe@gg.br'),
('Gabriel', '23/06', 'gab123@gmail.com');

INSERT INTO pedido (id_produto, id_cliente)
VALUES
(1, 1),
(2, 1),
(4, 2),
(3, 3)