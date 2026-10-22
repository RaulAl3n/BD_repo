INSERT INTO produto (nome, preco, estoque, id_categoria)
VALUES ('Webcam', 300.00, -2, 1);

/*
  Problema: estoque recebeu valor negativo.
  Restrição: CHECK (estoque >= 0)
  Correção: 
    INSERT INTO produto (nome, preco, estoque, id_categoria)
    VALUES ('Webcam', 300.00, 2, 1);

  Saída: Success. No rows returned
*/