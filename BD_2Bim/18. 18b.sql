INSERT INTO produto (preco, estoque, id_categoria)
VALUES (250.00, 5, 1);

/*
  Problema: produto nao recebeu o nome, que veio como nulo.
  Restrição: NOT NULL
  Correção: 
    INSERT INTO produto (nome, preco, estoque, id_categoria)
    VALUES ('Gof of War 4 PS5', 250.00, 5, 1);

  Saída: Success. No rows returned
*/