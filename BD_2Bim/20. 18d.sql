INSERT INTO produto (nome, preco, estoque, id_categoria)
VALUES ('Headset', 450.00, 5, 9999);

/*
  Problema: id_categoria recebeu um id que nao existe.
  Restrição: fk_id_categoria
  Correção: 
    INSERT INTO produto (nome, preco, estoque, id_categoria)
    VALUES ('Headset', 450.00, 5, 1);

  Saída: Success. No rows returned
*/