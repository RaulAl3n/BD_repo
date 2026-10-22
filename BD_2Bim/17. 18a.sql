INSERT INTO produto (nome, preco, estoque, id_categoria)
VALUES ('Teclado', -150.00, 10, 1);

/*
  Problema: preco esta recebendo um valor negativo.
  Restrição: CHECK (preco >= 0)
  Correção: 
    INSERT INTO produto (nome, preco, estoque, id_categoria)
    VALUES ('Teclado', 150.00, 10, 1);

  Saída: Success. No rows returned
*/