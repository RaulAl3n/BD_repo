SELECT categoria.nome AS nome_cat,
       produto.preco AS preco_prod,
       produto.nome AS nome_prod
FROM produto
RIGHT JOIN categoria
  ON produto.id_categoria = categoria.id_categoria
WHERE produto.id_categoria IS NULL;